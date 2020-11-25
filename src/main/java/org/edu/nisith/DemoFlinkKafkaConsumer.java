/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.edu.nisith;


import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * Created by nisith11 on 24/11/2020.
 */
public class DemoFlinkKafkaConsumer {


  public static void main(String[] args) throws Exception {
    // create execution environment
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
    String jaasCfg = String.format(jaasTemplate,args[0],args[1]);


    Properties props = new Properties();
    props.setProperty("bootstrap.servers", args[2]);
    props.setProperty("group.id", "zqra3s7v-consumer");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("auto.offset.reset", "earliest");
    props.put("session.timeout.ms", "30000");
    props.put("security.protocol", "SASL_SSL");
    props.put("sasl.mechanism", "SCRAM-SHA-256");
    props.put("sasl.jaas.config", jaasCfg);

    DataStream<String> stream = env
            .addSource(new FlinkKafkaConsumer<>(args[3], new SimpleStringSchema(), props));

    DataStreamSink<Tuple2<String, Integer>> result = stream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
      @Override
      public void flatMap(String value, Collector<Tuple2<String, Integer>> out)
              throws Exception {
        for(String word: value.split(" ")){
          out.collect(Tuple2.of(word, 1));
        }
      }
    })
    .filter(new FilterFunction<Tuple2<String, Integer>>() {
      @Override
      public boolean filter(Tuple2<String, Integer> value) throws Exception {
        return !value.f0.equals("Hello");
      }
    })
    . keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
      @Override
      public String getKey(Tuple2<String, Integer> value) throws Exception {
        return value.f0;
      }
    })
            .timeWindow(Time.seconds(1))
            .sum(1).print();

  /*  .reduce(new ReduceFunction<Tuple2<String, Integer>>() {
      @Override
      public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2)
              throws Exception {
        return Tuple2.of(value1.f0, value1.f1+value2.f1);
      }
    })*/;
    env.execute();
  }


}
