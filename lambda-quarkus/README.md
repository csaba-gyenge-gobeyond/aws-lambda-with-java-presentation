## Demo 4.1 - AWS Lambda with Quarkus and AWS Java Runtime 

1. Generating skeleton
    ```sh
    mvn archetype:generate \
           -DarchetypeGroupId=io.quarkus \
           -DarchetypeArtifactId=quarkus-amazon-lambda-archetype \
           -DarchetypeVersion=2.16.6.Final
    ```
1. Modify our code accordingly
1. Build the project 
    ```sh
    mvn install
    ```
1. Take a look into the `target` folder, you should see the following files.
    * `function.zip` - lambda deployment file 
    * `manage.sh` - wrapper around aws lambda cli calls (check function name and handler name)
    * `sam.jvm.yaml` - (optional) for use with sam cli and local testing
    * `sam.native.yaml` - (optional) for use with sam cli and native local testing
    * `bootstrap-example.sh` - (native) example bootstrap script for native deployments
   
   Also check the content of `function.zip` and note that there are generated Proxy and Bean classes. 
1. Deploy the function with `manage.sh`
    ```sh
    export LAMBDA_ROLE_ARN=arn:aws:iam::070590460840:role/service-role/athena-query-role-eqdq690p
    ./target/manage.sh create
    ```
1. Call the function with `manage.sh`
    ```sh
    ./target/manage.sh invoke
    ```

## Demo 4.2 - AWS Lambda with Quarkus and GraalVM Native Image

1. Build the project with native profile in docker container. It will take about 3 minutes. 
    ```sh
    mvn install -Dnative -DskipTests -Dquarkus.native.container-build=true
    ```
1. Note the size
   ```sh
   $ ls -lah target | grep "M "
   -rw-r--r--   1 csabagyenge  staff    12M Apr  5 14:09 function.zip
   -rwxr-xr-x   1 csabagyenge  staff    36M Apr  5 14:09 lambda-quarkus-1.0-SNAPSHOT-runner
   ```
1. Deploy the function with `manage.sh`
    ```sh
    export LAMBDA_ROLE_ARN=arn:aws:iam::070590460840:role/service-role/athena-query-role-eqdq690p
    ./target/manage.sh native create
    ```
1. Invoke the function with `manage.sh`
    ```sh
    ./target/manage.sh native invoke
    ```
