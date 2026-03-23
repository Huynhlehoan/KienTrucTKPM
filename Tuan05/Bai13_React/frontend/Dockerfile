# --- GIAI ĐOẠN 1: Dùng Node.js để Build source code ---
FROM node:18-alpine AS builder
WORKDIR /app
# Copy file thư viện vào trước để tải
COPY package.json ./
RUN npm install
# Copy toàn bộ code thật vào
COPY . ./
# Lệnh này đẻ ra thư mục chứa code tĩnh tên là /app/build
RUN npm run build 

# --- GIAI ĐOẠN 2: Dùng Nginx để hiển thị ---
FROM nginx:alpine
# Lệnh thần thánh: Copy đúng cái thư mục build từ Giai đoạn 1 sang Nginx, vứt sạch sẽ đống code Node.js lằng nhằng đi
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]