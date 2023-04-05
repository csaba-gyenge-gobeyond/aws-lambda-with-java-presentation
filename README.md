# Demo 1.1

```sh
cat payload.json | node function.js
```

# Demo 1.2

Modify the `function.js` to be a real nodejs function.

```sh
cat payload.json | node -e 'require("./function").sayHello(JSON.parse(require("fs").readFileSync(0))).then(response => process.stdout.write(response))'
```

And deploy to AWS Lambda.

```sh
zip -r function.zip function.js
```

```sh
aws lambda create-function --function-name sayHello --runtime nodejs18.x --handler function.sayHello --zip-file fileb://function.zip --role arn:aws:iam::070590460840:role/service-role/athena-query-role-eqdq690p
```

Give it a try

```sh
aws lambda invoke --function-name sayHello --cli-binary-format raw-in-base64-out --payload file://payload.json response.json
cat response.json
```

# Demo 2

Create `bootstrap.sh`. It gets miscellaneous environment variables from the execution environment, such as `LAMBDA_TASK_ROOT` and `_HANDLER`.

* `LAMBDA_TASK_ROOT` is the directory where the function is deployed.
* `_HANDLER` is the function name in format `<module-name>.<function-name>`, in our case it will be `function.sayHello`.

Modify `function.sh` to be a real shell function.

Create `function.zip` and deploy to AWS Lambda.

```sh
rm -rf function.zip
chmod 755 bootstrap
zip -r function.zip function.sh bootstrap
```

```sh
aws lambda delete-function --function-name sayHelloCustomRuntime
aws lambda create-function --function-name sayHelloCustomRuntime --runtime provided --handler function.sayHello --zip-file fileb://function.zip --role arn:aws:iam::070590460840:role/service-role/athena-query-role-eqdq690p
```

Give it a try

```sh
aws lambda invoke --function-name sayHelloCustomRuntime --cli-binary-format raw-in-base64-out --payload file://payload.json response.json --query 'LogResult' --log-type Tail --output text | base64 --decode
cat response.json
```
