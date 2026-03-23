# Dùng image Node.js bản nhẹ (alpine)
FROM node:18-alpine

# Set thư mục làm việc trong container
WORKDIR /app

# Copy file cấu hình thư viện vào trước
COPY package.json .

# Cài đặt Express
RUN npm install

# Copy toàn bộ code (file app.js) vào container
COPY . .

# Mở cổng 3000
EXPOSE 3000

# Lệnh chạy app khi container khởi động
CMD ["npm", "start"]