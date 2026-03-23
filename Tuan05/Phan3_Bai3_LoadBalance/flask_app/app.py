from flask import Flask
import socket

app = Flask(__name__)

@app.route('/')
def hello():
    # Lấy ID của container đang chạy
    container_id = socket.gethostname()
    return f"<h1>Hello từ Flask!</h1><h2>Tao là container có ID: <span style='color:red'>{container_id}</span></h2>"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)