const express = require('express');
const mongoose = require('mongoose');
const app = express();

// Kết nối đến service tên là 'mongo' trong file compose
const MONGO_URL = 'mongodb://mongo:27017/mydb';

mongoose.connect(MONGO_URL)
  .then(() => console.log('Đã kết nối MongoDB thành công!'))
  .catch(err => console.error('Lỗi kết nối MongoDB:', err));

app.get('/', (req, res) => {
  res.send('<h1>Trang chủ Backend Node.js. Database đã được kết nối!</h1>');
});

app.listen(3000, () => console.log('App running on port 3000'));