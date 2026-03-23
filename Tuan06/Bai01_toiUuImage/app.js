const express = require('express');
const app = express();
app.get('/', (req, res) => res.send('Test tối ưu Image!'));
app.listen(3000, () => console.log('App chay o port 3000'));