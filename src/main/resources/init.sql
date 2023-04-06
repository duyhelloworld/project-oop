-- Active: 1680760032658@@127.0.0.1@3306
CREATE DATABASE DoAnOOP;
USE DoAnOOP;

CREATE TABLE SinhVien (
    mssv INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(20),
    -- Bắt buộc đủ họ, tên & tên đệm --
    ngay_sinh DATE,
    -- format YYYY/MM/DD truy vấn sẽ format thành dd/mm/yyyy
    gioi_tinh BIT,
    que_quan VARCHAR(200),
    dia_chi_hien_tai VARCHAR(200),
    -- xã ..., phường ..., huyện ...
    email VARCHAR(40),
    so_dien_thoai VARCHAR(10),
    -- Not null của email, sđt sẽ đc ép ở phần frontend
    CONSTRAINT UK_mail_sdt UNIQUE (email, so_dien_thoai)
);

CREATE TABLE MonHoc (
    ma_mon_hoc INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(20),
    so_tin_chi INT,
    mo_ta_mh TEXT
);

CREATE TABLE Lop (
    ma_lop INT PRIMARY KEY AUTO_INCREMENT,
    ten_lop CHAR(5)
);

CREATE TABLE DiemThi (
    ma_mon_hoc INT,
    ma_lop INT,
    mssv INT,
    so_buoi_diem_danh FLOAT,
    --điểm danh 10/30 buổi --> lưu 0.333
    diem_giua_ki FLOAT,
    diem_cuoi_ki FLOAT,
    Foreign Key (ma_mon_hoc) REFERENCES MonHoc(ma_mon_hoc),
    Foreign Key (ma_lop) REFERENCES Lop(ma_lop),
    Foreign Key (mssv) REFERENCES SinhVien(mssv)
);

INSERT INTO `DoAnOOP`.`SinhVien` (ho_ten, ngay_sinh, gioi_tinh, que_quan, dia_chi_hien_tai, email, so_dien_thoai)
VALUES("Pham Duc Duy", "03/12/09", 1, "Trung Luong, Binh Luc, Ha Nam", "3, 8, Be Van Dan, Quang Trung, Ha Dong", "duy0000001@huce.edu.vn", "0987653210");

INSERT INTO `DoAnOOP`.`SinhVien` (ho_ten, ngay_sinh, gioi_tinh, que_quan, dia_chi_hien_tai, email, so_dien_thoai)
VALUES (
    "Nguyen Van Hiep",
    "03/01/12",
    1,
    "Van Trung, Van Giang, Hung Yen",
    "10, 7, Le Hong Phong, Cau Giay",
    "hiep0000002@huce.edu.vn",
    "0987653203");
INSERT INTO `DoAnOOP`.`SinhVien` (ho_ten, ngay_sinh, gioi_tinh, que_quan, dia_chi_hien_tai, email, so_dien_thoai)
VALUES (
    "Nguyen Thi Hang",
    "03/01/12",
    0,
    "Van Hoa, Thanh Quang, Thach That",
    "9, Hoang Van Thu, Hoan Kiem",
    "hang0000003@huce.edu.vn",
    "0987653130");

    SELECT * FROM SinhVien WHERE ho_ten LIKE '%Hang%'; 