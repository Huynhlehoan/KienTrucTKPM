#!/bin/bash
WORK_DIR=$(pwd)


TARGET_FOLDER="HuynhLeHoan_22682941_Tuan01"

# Nơi lưu file backup (Lưu vào thư mục Minh Chung)
DEST_DIR="$WORK_DIR/Minh Chung"
BACKUP_FILE="backup_$(date +%Y%m%d_%H%M%S).tar.gz"

# Kiểm tra lần cuối
if [ ! -d "$WORK_DIR/$TARGET_FOLDER" ]; then
    echo " Lỗi: Không thấy thư mục $TARGET_FOLDER"
    exit 1
fi

# Tạo thư mục đích nếu chưa có
mkdir -p "$DEST_DIR"

echo "Dang backup..."
# Nén
tar -czf "$DEST_DIR/$BACKUP_FILE" -C "$WORK_DIR" "$TARGET_FOLDER"

if [ $? -eq 0 ]; then
    echo " Backup thanh cong!"
    echo "File luu tai: $DEST_DIR/$BACKUP_FILE"
else
    echo " Backup that bai"
fi
