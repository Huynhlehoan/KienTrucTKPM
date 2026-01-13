#!/bin/bash

# Lấy đường dẫn hiện tại
WORK_DIR=$(pwd)

# File log sẽ nằm trong thư mục Minh Chung
LOG_FILE="$WORK_DIR/Minh Chung/app.log"
MAX_SIZE=1024 # 1KB (để test cho dễ)

# 1. Tạo file log giả nếu chưa có (để script không bị lỗi)
if [ ! -f "$LOG_FILE" ]; then
    echo "Log file created at $(date)" > "$LOG_FILE"
fi

# 2. Kiểm tra kích thước
FILE_SIZE=$(wc -c < "$LOG_FILE")
echo "Kich thuoc hien tai: $FILE_SIZE bytes"

if [ $FILE_SIZE -gt $MAX_SIZE ]; then
    echo " File qua lon. Tien hanh rotate..."
    TIMESTAMP=$(date +%Y%m%d%H%M%S)
    # Đổi tên file cũ
    mv "$LOG_FILE" "$LOG_FILE.$TIMESTAMP.old"
    # Tạo file mới
    touch "$LOG_FILE"
    echo " Rotate thanh cong!"
else
    echo " File van nho, chua can rotate."
fi
