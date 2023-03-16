const https = require('https');

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

exports.sayHello = (event) => {
  return randomName()
    .then(data => data.results[0].name)
    .then(name => `${name.first} ${name.last}`)
    .then(randomName => JSON.stringify({
      message: `Hello ${event.name}! My name is ${randomName}. Nice to meet you!`
    }))
}
