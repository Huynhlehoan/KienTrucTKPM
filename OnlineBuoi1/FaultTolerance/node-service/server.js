// server.js
const express = require('express');
const app = express();
const PORT = 8081; // Chạy ở cổng 8081

let isErrorMode = false; // Biến để bật/tắt chế độ lỗi

app.get('/api/data', (req, res) => {
    console.log(`NodeJS: Nhận request lúc ${new Date().toISOString()}`);

    // 1. Giả lập lỗi 500 (Dùng để test Circuit Breaker)
    if (isErrorMode) {
        return res.status(500).send("Lỗi rồi bạn ơi!");
    }

    // 2. Trả về thành công
    res.json({ message: "Hello từ Service B (NodeJS)" });
});

// API phụ để bạn bật/tắt lỗi từ xa (để demo cho thầy cô xem)
app.get('/toggle-error', (req, res) => {
    isErrorMode = !isErrorMode;
    res.send(`Chế độ lỗi đang là: ${isErrorMode}`);
});

app.get('/', (req, res) => {
    res.send("Service B (NodeJS) đang chạy ngon lành!");
});
app.listen(PORT, () => {
    console.log(`Service B đang chạy tại: http://localhost:${PORT}`);
});