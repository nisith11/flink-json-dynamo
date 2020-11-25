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
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.edu.nisith.pojo.TransactionRecordFormat;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.MapperFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;


/**
 * Created by nisith11 on 24/11/2020.
 */
public class FlinkJsonStreamDriver {

  public static void main(String[] args) throws Exception {

    // create execution environment
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    DataStream<String> stream = env.readFile(new TextInputFormat(new Path(args[0])), args[0], FileProcessingMode.PROCESS_CONTINUOUSLY, 1000)
            .uid("importFile").name("Import from file").setParallelism(1).rebalance();
    DataStream<TransactionRecordFormat> JsonInStream  = stream.map(new MapFunction<String,TransactionRecordFormat>() {
      @Override
      public TransactionRecordFormat map(String value)
              throws Exception {
        ObjectMapper mapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
          return mapper.readValue(value,TransactionRecordFormat.class) ;
        } catch (UnrecognizedPropertyException e) {
          System.out.println("UnrecognizedPropertyException :=> "+ e.getMessage());
        } catch (Throwable e) {
          System.out.println("Some other exception :=> "+ e.getMessage());
        }
        return null;
      }
    }).filter(new FilterFunction<TransactionRecordFormat>() {
      @Override
      public boolean filter(TransactionRecordFormat value) throws Exception {
        return value != null;
      }
    });
    JsonInStream.addSink(new DynamoDBSink());
   env.execute();
  }
}
