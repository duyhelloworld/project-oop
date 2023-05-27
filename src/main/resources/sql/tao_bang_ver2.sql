-- Active: 1683203836476@@127.0.0.1@3306
CREATE DATABASE DoAnOOP;
USE DoAnOOP;
# Thuc the
CREATE TABLE Khoa (
    ma_khoa INT PRIMARY KEY AUTO_INCREMENT,
    ten_khoa VARCHAR(120),
    UNIQUE(ma_khoa, ten_khoa)
);

CREATE TABLE MonHoc (
    ma_mon_hoc INT PRIMARY KEY AUTO_INCREMENT,
    ten_mon_hoc VARCHAR(20),
    so_tin_chi TINYINT,
    bat_buoc BIT,
    ma_mon_tien_quyet INT,
    ma_khoa INT,
    mo_ta TEXT,
    UNIQUE (ma_mon_hoc, ten_mon_hoc),
    Foreign Key (ma_khoa) REFERENCES khoa(ma_khoa)
);

CREATE TABLE LopMonHoc (
    ma_lop_mon_hoc INT PRIMARY KEY AUTO_INCREMENT,
    ten_lop_mon_hoc CHAR(5) UNIQUE,
    ma_mon_hoc INT,
    Foreign Key (ma_mon_hoc) REFERENCES MonHoc(ma_mon_hoc)
);

CREATE TABLE SinhVien (
    mssv INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    gioi_tinh BIT,
    ngay_sinh DATE,
    dia_chi_hien_tai VARCHAR(200),
    que_quan VARCHAR(200), 
    email VARCHAR(30), 
    so_dien_thoai CHAR(10),
    ngay_vao_truong DATE,
    CONSTRAINT UK_SinhVien UNIQUE(email, so_dien_thoai)
);


CREATE TABLE GiangVien (
    ma_gv INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    gioi_tinh BIT,
    ngay_sinh DATE,
    dia_chi_hien_tai VARCHAR(200),
    que_quan VARCHAR(200),
    email VARCHAR(40),
    so_dien_thoai VARCHAR(10),
    mat_khau VARCHAR(20),
    CONSTRAINT UK_GiangVien UNIQUE(email, so_dien_thoai)
);

CREATE TABLE LopMonHoc_GiangVien (
    ma_gv INT,
    ma_lop_mon_hoc INT,
    PRIMARY KEY (ma_gv, ma_lop_mon_hoc),
    Foreign Key (ma_gv) REFERENCES GiangVien(ma_gv),
    Foreign Key (ma_lop_mon_hoc) REFERENCES LopMonHoc(ma_lop_mon_hoc)
);

CREATE TABLE LopQuanLi (
    ma_lop_quan_li INT AUTO_INCREMENT,
    ma_gv INT,
    ten_lop_quan_li VARCHAR(20),
    ma_khoa INT,
    constraint uk_lopquanli UNIQUE(ten_lop_quan_li),
    PRIMARY KEY(ma_lop_quan_li, ma_gv),
    Foreign Key (ma_khoa) REFERENCES khoa(ma_khoa)
);

CREATE TABLE LopQuanLi_SinhVien (
        ma_lop_quan_li INT,
        mssv INT,
        PRIMARY KEY(ma_lop_quan_li, mssv),
        Foreign Key (ma_lop_quan_li) REFERENCES LopQuanLi(ma_lop_quan_li),
        Foreign Key (mssv) REFERENCES SinhVien(mssv)
    );

CREATE TABLE NhanVienDaoTao (
        ma_nvdt INT PRIMARY KEY AUTO_INCREMENT,
        ho_ten VARCHAR(100),
        gioi_tinh BIT,
        ngay_sinh DATE,
        dia_chi_hien_tai VARCHAR(200),
        que_quan VARCHAR(200),
        email VARCHAR(40),
        so_dien_thoai VARCHAR(10),
        mat_khau VARCHAR(20),
        CONSTRAINT UK_GiangVien UNIQUE(email, so_dien_thoai)
    );

CREATE TABLE DiemSinhVien (
    ma_lop_mon_hoc INT,
    mssv INT,
    diem_chuyen_can INT,
    diem_giua_ki FLOAT,
    diem_cuoi_ki FLOAT,
    ghi_chu TEXT,
    PRIMARY KEY (mssv, ma_lop_mon_hoc),
    Foreign Key (ma_lop_mon_hoc) REFERENCES LopMonHoc(ma_lop_mon_hoc),
    Foreign Key (mssv) REFERENCES SinhVien(mssv) 
);