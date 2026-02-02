
const express = require('express');
const app = express();
const PORT = 8081; 

app.get('/api/data', (req, res) => {
    setTimeout(() => {
        console.log("--> Slow Server: Trả về sau 3s");
      
        res.json({ message: "Server Cham da tra loi (OK)" }); 
    }, 3000);
});
app.listen(PORT, () => console.log(`Server SLOW đang chạy port ${PORT}...`));