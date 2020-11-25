package org.edu.nisith;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.flink.configuration.Configuration;
import org.edu.nisith.pojo.TransactionRecordFormat;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
/**
 * Created by nisith11 on 24/11/2020.
 */
public class DynamoDBSink extends RichSinkFunction<TransactionRecordFormat> {

        private AmazonDynamoDB client;
        private DynamoDBMapper mapper;

        @Override
        public void invoke(TransactionRecordFormat value) throws Exception {

            try {
                System.out.println("value getting saved -> "+value.getMessageKey());
                mapper.save(value);
                System.out.println("value saved -> "+value.getMessageKey());
            } catch (Exception e) {
                System.err.println("Unable to retrieve data: ");
                System.err.println(e.getMessage());
            }
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            client = AmazonDynamoDBClientBuilder.standard()
                        .withRegion(Regions.US_EAST_1)
                        .build();

            mapper = new DynamoDBMapper(client);
           }

}
