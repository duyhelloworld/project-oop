-- Active: 1683203836476@@127.0.0.1@3306
CREATE DATABASE DoAnOOP2;
USE DoAnOOP2;
# Thuc the
CREATE TABLE Khoa (
    ma_khoa INT PRIMARY KEY AUTO_INCREMENT,
    ten_khoa VARCHAR(120)
);

CREATE TABLE MonHoc (
        ma_monhoc INT PRIMARY KEY AUTO_INCREMENT,
        ten_monhoc VARCHAR(20),
        so_tin_chi TINYINT,
        bat_buoc BIT,
        la_tien_quyet_cua INT UNIQUE,
        ma_khoa INT,
        mo_ta TEXT,
        Foreign Key (ma_khoa) REFERENCES khoa(ma_khoa)
    );

CREATE TABLE
    LopMonHoc (
        ma_lop_monhoc INT PRIMARY KEY AUTO_INCREMENT,
        ten_lop_monhoc CHAR(5),
        ma_monhoc INT,
        Foreign Key (ma_monhoc) REFERENCES MonHoc(ma_monhoc)
    );

CREATE TABLE SinhVien (
    mssv INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    gioi_tinh BIT,
    ngay_sinh DATE,
    dia_chi_hien_tai VARCHAR(200),
    que_quan VARCHAR(200), 
    email VARCHAR(30), 
    so_dien_thoai VARCHAR(10),
    ngay_vao_truong DATE,
    CONSTRAINT UK_SinhVien UNIQUE(email, so_dien_thoai)
);


CREATE TABLE
    GiangVien (
        ma_gv INT PRIMARY KEY AUTO_INCREMENT,
        ho_ten VARCHAR(100),
        gioi_tinh BIT,
        ngay_sinh DATE,
        dia_chi_hien_tai VARCHAR(200),
        que_quan VARCHAR(200),
        email VARCHAR(40),
        so_dien_thoai VARCHAR(10),
        CONSTRAINT UK_GiangVien UNIQUE(email, so_dien_thoai)
    );

CREATE TABLE
    LopMonHoc_GiangVien (
    ma_gv INT,
    ma_lop_monhoc INT,
    PRIMARY KEY (ma_gv, ma_lop_monhoc),
    Foreign Key (ma_gv) REFERENCES GiangVien(ma_gv),
    Foreign Key (ma_lop_monhoc) REFERENCES LopMonHoc(ma_lop_monhoc)
);


CREATE TABLE LopQuanLi (
    ma_lop_quanli INT AUTO_INCREMENT,
    ma_gv INT,
    ten_lop_quanli VARCHAR(20),
    ma_khoa INT,
    constraint uk_lopquanli UNIQUE(ten_lop_quanli),
    PRIMARY KEY(ma_lop_quanli, ma_gv),
    Foreign Key (ma_khoa) REFERENCES khoa(ma_khoa)
);

CREATE TABLE
    LopQuanLi_SinhVien (
        ma_lop_quanli INT,
        mssv INT,
        PRIMARY KEY(ma_lop_quanli, mssv),
        Foreign Key (ma_lop_quanli) REFERENCES LopQuanLi(ma_lop_quanli),
        Foreign Key (mssv) REFERENCES SinhVien(mssv)
    );

CREATE TABLE
    NhanVienDaoTao (
        mssv INT PRIMARY KEY AUTO_INCREMENT,
        ho_ten VARCHAR(100),
        gioi_tinh BIT,
        ngay_sinh DATE,
        dia_chi_hien_tai VARCHAR(200),
        que_quan VARCHAR(200),
        email VARCHAR(40),
        so_dien_thoai VARCHAR(10),
        CONSTRAINT UK_GiangVien UNIQUE(email, so_dien_thoai)
    );

CREATE TABLE DiemSinhVien (
    ma_lop_monhoc INT,
    mssv INT,
    so_buoi_diem_danh INT,
    diem_giua_ki FLOAT,
    diem_cuoi_ki FLOAT,
    ghi_chu TEXT,
    PRIMARY KEY (mssv, ma_lop_monhoc),
    Foreign Key (ma_lop_monhoc) REFERENCES LopMonHoc(ma_lop_monhoc),
    Foreign Key (mssv) REFERENCES SinhVien(mssv) 
);
