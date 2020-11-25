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


import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.edu.nisith.pojo.TransactionRecordFormat;
import org.edu.nisith.serialization.TransactionDeserializationSchema;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Properties;

/**
 * Created by nisith11 on 24/11/2020.
 */
public class FlinkJsonKafkaConsumer {


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

    DataStream<TransactionRecordFormat> stream = env
            .addSource(new FlinkKafkaConsumer<>("zqra3s7v-test", new TransactionDeserializationSchema(), props))
            .name("TransactionRecordFormat Source");

    stream.print();
    stream.map(new MapFunction<TransactionRecordFormat, String>() {
      @Override
      public String map(TransactionRecordFormat value)
              throws Exception {
        return value.getAmount();
      }
    }).print();
    env.execute();
  }

}
