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

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created by nisith11 on 24/11/2020.
 */
public class WriteToKafka {

  public static void main(String[] args) throws Exception {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


    String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
    String jaasCfg = String.format(jaasTemplate,args[0],args[1]);

    String deserializer = StringDeserializer.class.getName();
    Properties props = new Properties();
    props.setProperty("bootstrap.servers", args[2]);    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("auto.offset.reset", "earliest");
    props.put("session.timeout.ms", "30000");
    props.put("key.deserializer", deserializer);
    props.put("value.deserializer", deserializer);
    props.put("security.protocol", "SASL_SSL");
    props.put("sasl.mechanism", "SCRAM-SHA-256");
    props.put("sasl.jaas.config", jaasCfg);


    DataStream<String> stream = env.addSource(new StringGenerator());
    stream.addSink(new FlinkKafkaProducer<>(args[3], new SimpleStringSchema(), props));

    env.execute();
  }

  /**
   * Simple Class to generate data
   */
  public static class StringGenerator implements SourceFunction<String> {
    private static final long serialVersionUID = 119007289730474249L;
    boolean running = true;
    long i = 0;
    @Override
    public void run(SourceContext<String> ctx) throws Exception {
      while(running) {
        ctx.collect("Pepper Piper Peeper Piper over and over Pepper Piper "+ (i++));
        Thread.sleep(5000);
      }
    }
    @Override
    public void cancel() {
      running = false;
    }
  }


}
