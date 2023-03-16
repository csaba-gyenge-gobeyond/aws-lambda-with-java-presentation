const fs = require("fs");
const https = require('https');
const stdin = fs.readFileSync(0).toString();
const event = JSON.parse(stdin);

const randomName = () => {
  return new Promise((resolve, reject) => {
    https.get('https://randomuser.me/api/?format=json', (resp) => {
      let data = '';
      resp.on('data', (chunk) => data += chunk);
      resp.on('end', () => resolve(JSON.parse(data)));
    }).on("error", (err) => {
      reject(err);
    });
  })
}

randomName()
  .then(data => data.results[0].name)
  .then(name => `${name.first} ${name.last}`)
  .then(randomName => JSON.stringify({
    message: `Hello ${event.name}! My name is ${randomName}. Nice to meet you!`
  }))
  .then((response) => process.stdout.write(response))
