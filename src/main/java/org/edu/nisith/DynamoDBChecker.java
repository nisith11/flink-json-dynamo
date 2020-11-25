package org.edu.nisith;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.edu.nisith.pojo.TransactionRecordFormat;
import com.amazonaws.regions.Regions;

/**
 * Created by nisith11 on 24/11/2020.
 */
public class DynamoDBChecker {




    public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        TransactionRecordFormat tr = new TransactionRecordFormat();
        tr.setMessageKey("1");

        try {
            TransactionRecordFormat result = mapper.load(tr);
            if (result != null) {
                System.out.println(
                        "Transaction amount" + result.getAmount());
            } else {
                System.out.println("No matching transaction was found");
            }
        } catch (Exception e) {
            System.err.println("Unable to retrieve data: ");
            System.err.println(e.getMessage());
        }


    }
}