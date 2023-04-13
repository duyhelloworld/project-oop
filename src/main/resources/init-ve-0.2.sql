-- Active: 1680760032658@@127.0.0.1@3306@DoAnOOP2

CREATE DATABASE DoAnOOP2;
USE DoAnOOP2;
CREATE TABLE MonHoc (
        ma_mon INT PRIMARY KEY AUTO_INCREMENT,
        ten VARCHAR(20),
        so_tin_chi TINYINT,
        bat_buoc BIT,
        tien_quyet BIT,
        so_buoi TINYINT
    );

CREATE TABLE
    LopMonHoc (
        ten_lop CHAR(5) PRIMARY KEY,
        ma_mon INT,
        Foreign Key (ma_mon) REFERENCES MonHoc(ma_mon)
    );

CREATE TABLE
    LopQuanLi (
        ten_lop CHAR(5) PRIMARY KEY,
        si_so INT,
        khoa VARCHAR(100)
    );
CREATE TABLE SinhVien (
    mssv INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    ten_lop CHAR(5),
    Foreign Key (ten_lop) REFERENCES LopQuanLi(ten_lop)
);
CREATE TABLE TinhDiem (
    ten_lop CHAR(5),
    mssv INT,
    so_buoi_diem_danh INT,
    diem_giua_ki FLOAT,
    diem_cuoi_ki FLOAT,
    Foreign Key (ten_lop) REFERENCES LopMonHoc(ten_lop),
    Foreign Key (mssv) REFERENCES SinhVien(mssv) 
);

INSERT INTO `MonHoc` VALUES(101, "Ngon ngu C++", 3, 1, 0, 40);
INSERT INTO `MonHoc` VALUES(102, "Chinh Tri-Xa Hoi", 2, 1, 1, 30);
INSERT INTO `MonHoc` VALUES(103, "Triet hoc Mac-lenin", 3, 1, 1, 28);
INSERT INTO `MonHoc` VALUES(104, "Toan roi rac", 2, 1, 0, 42);

INSERT INTO `LopMonHoc` VALUES("66ML1", 103);
INSERT INTO `LopMonHoc` VALUES("66LT2", 101);
INSERT INTO `LopMonHoc` VALUES("66ML2", 103);

INSERT INTO `LopQuanLi` VALUES("66IT1", 40, "CNTT");
INSERT INTO `LopQuanLi` VALUES("66IT2", 45, "CNTT");
INSERT INTO `LopQuanLi` VALUES("66IT3", 50, "CNTT");
INSERT INTO `LopQuanLi` VALUES("66KT3", 56, "KT");
INSERT INTO `LopQuanLi` VALUES("66IT5", 54, "CNTT");

INSERT INTO `SinhVien`
VALUES(1, "Pham Duc Duy", "66IT5");
INSERT INTO `SinhVien`
VALUES(2, "Nguyen Van Hiep ", "66IT5");
INSERT INTO `SinhVien`
VALUES(3, "Hoang Thuy Linh ", "66KT3");
INSERT INTO `SinhVien`
VALUES(4, "Nguyen Thi Hang ", "66IT3");

INSERT INTO `TinhDiem` VALUES("66ML1", 1, 9.0, 29, 9.0);
INSERT INTO `TinhDiem` VALUES("66ML1", 2, 7.6, 29, 8.0);
INSERT INTO `TinhDiem` VALUES("66LT2", 2, 5.0, 30, 6.0);
INSERT INTO `TinhDiem` VALUES("66ML2", 1, 4.0, 20, 7.0);


-- Quản lí danh sách môn + số tín, bắt /tc, môn tiên quyết
SELECT * FROM `MonHoc`;

-- Quản lí danh sách sinh viên
SELECT * FROM `SinhVien`;

-- Quản lí bảng điểm từng môn theo lớp quản lí

SELECT
SinhVien.mssv, ho_ten, SinhVien.ten_lop AS lop_quan_li, TinhDiem.ten_lop AS lop_mon_hoc, MonHoc.ten, khoa, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki
FROM
    (`SinhVien` 
INNER JOIN `LopQuanLi` 
ON SinhVien.ten_lop = LopQuanLi.ten_lop)
INNER JOIN (`MonHoc` INNER JOIN `LopMonHoc` On LopMonHoc.ma_mon = MonHoc.ma_mon)
INNER JOIN `TinhDiem` On TinhDiem.mssv = SinhVien.mssv
WHERE SinhVien.ten_lop = "66IT5"
ORDER BY SinhVien.mssv;

-- Quản lí bảng điểm từng môn theo lớp môn học
SELECT
SinhVien.mssv, ho_ten, SinhVien.ten_lop AS lop_quan_li, LopMonHoc.ten_lop AS lop_mon_hoc, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki 
FROM (`SinhVien` 
INNER JOIN 
(`TinhDiem` INNER JOIN `LopMonHoc` ON TinhDiem.ten_lop = LopMonHoc.ten_lop)
ON SinhVien.mssv = TinhDiem.mssv)
WHERE LopMonHoc.ten_lop = "66ML1"
ORDER BY SinhVien.mssv; 