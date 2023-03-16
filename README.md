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
