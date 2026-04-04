CREATE DATABASE IF NOT EXISTS demo_partition;
USE demo_partition;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    PRIMARY KEY (id, gender)
)
PARTITION BY LIST COLUMNS(gender) (
    PARTITION table_user_01 VALUES IN ('NAM'),
    PARTITION table_user_02 VALUES IN ('NU')
);

-- Kiểm tra phân vùng của Nam
SELECT * FROM users PARTITION (table_user_01);

-- Kiểm tra phân vùng của Nữ
SELECT * FROM users PARTITION (table_user_02);