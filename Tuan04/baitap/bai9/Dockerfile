FROM redis:latest
COPY redis.conf /usr/local/etc/redis/redis.conf
# Chạy Redis server bằng file cấu hình vừa chép vào
CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]