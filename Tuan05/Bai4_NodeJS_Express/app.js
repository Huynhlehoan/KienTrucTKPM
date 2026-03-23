const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
  res.send('<h1>Hello Docker! Đây là Bài 4 chạy bằng Express Framework.</h1>');
});

app.listen(port, () => {
  console.log(`Server đang chạy ở port ${port}`);
});