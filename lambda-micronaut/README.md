# Demo 4.2 - AWS Lambda w/ Micronaut

1. Build Micronaut lambda using native image

    ```sh
    ./mvnw package -Dpackaging=docker-native -Dmicronaut.runtime=lambda -Pgraalvm
    ```

1. Deploy lambda function to AWS Lambda

    ```sh
    aws lambda delete-function --function-name sayHelloMicronaut 
    aws lambda create-function --function-name sayHelloMicronaut \
                               --runtime provided \
                               --handler dummy \
                               --zip-file fileb://target/function.zip \
                               --memory-size 256 \
                               --timeout 15 \
                               --role arn:aws:iam::070590460840:role/service-role/athena-query-role-eqdq690p 
    ```

1. Invoke the function
   
   ```sh
   aws lambda invoke --function-name sayHelloMicronaut --cli-binary-format raw-in-base64-out --payload file://../payload.json response.json  --query 'LogResult' --log-type Tail --output text | base64 --decode
   ```
