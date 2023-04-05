1. Deploy lambda function to AWS Lambda
    
    ```sh
    mvn install
   
    aws lambda delete-function --function-name sayHelloJava 
    aws lambda create-function --function-name sayHelloJava --runtime java11 --handler aero.gobeyond.ll.lambda.sayhello.SayHelloLambdaHandler --zip-file fileb://target/say-hello-lambda-1.0-SNAPSHOT.jar --role arn:aws:iam::070590460840:role/service-role/athena-query-role-eqdq690p --timeout 30 
    ```

1. Invoke

    ```sh
   aws lambda invoke --function-name sayHelloJava --cli-binary-format raw-in-base64-out --payload file://../payload.json response.json  --query 'LogResult' --log-type Tail --output text | base64 --decode
   ```
