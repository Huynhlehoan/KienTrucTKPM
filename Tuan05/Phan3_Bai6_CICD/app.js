const http = require('http');
http.createServer((req, res) => res.end('Moi truong chay ngot ngau!')).listen(3000);
console.log('App dang chay...');