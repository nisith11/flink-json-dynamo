# Apache Flink Learning

There are multiple classes in this codebase, below are the details.

# FlinkJsonStreamDriver

This class is responsible for reading a local or hdfs folder in real time for json files
and then push each json to as a record to the Dynamo Db

Assumptions:
Currently the code is tested to read json files from local folder but I believe the same code with little modifications may be used to read from HDFS and S3 folders as well

# To create the jar execute the below steps
1- download the code from git
2- to create the jar go to the flink-json-dynamo folder and execute  mvn clean install

For Local Flink server Installation follow the below link.
https://ci.apache.org/projects/flink/flink-docs-release-1.11/try-flink/local_installation.html 

# Create table in Dyanamo DB with Name -- Transactions
check the class TransactionRecordFormat for the input json schema as well as output dynamo table schema

# Once installed execute the below steps to submit the job.

1- export your AWS credentails to access the Dynamo DB in your AWS.
follow the link for more details https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html

export AWS_ACCESS_KEY_ID=<Your Access key>
export AWS_SECRET_ACCESS_KEY=<Your Secret Access Key>
  
2- execute the below command to submit the job 

./bin/flink run -c org.edu.nisith.FlinkJsonStreamDriver <Full Path for your Jar File>flink-json-dynamo-1.0-SNAPSHOT.jar file://<Full path of the folder for Json files to arrive> 
  
Once the job starts
copy a sample json into the folder and check the dynamo db for an entry.



