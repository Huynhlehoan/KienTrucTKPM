const express = require('express');
const mysql = require('mysql2');
const app = express();


// host không phải là 'localhost' nữa, mà phải là tên service của MySQL trong file compose
const pool = mysql.createPool({
  host: 'db', 
  user: 'root',
  password: 'rootpassword',
  database: 'testdb'
});

app.get('/', (req, res) => {
  // Thực hiện 1 câu query test đơn giản
  pool.query('SELECT 1 + 1 AS solution', (error, results) => {
    if (error) {
      return res.status(500).send('Lỗi kết nối DB: ' + error.message);
    }
    res.send(`<h1>Kết nối MySQL thành công!</h1> <p>Kết quả test DB: 1 + 1 = ${results[0].solution}</p>`);
  });
});

app.listen(3000, () => console.log('App running on port 3000'));