-- Active: 1683203836476@@127.0.0.1@3306@doanoop2
use doanoop2;

INSERT INTO khoa (ma_khoa, ten_khoa)
VALUES (NULL, NULL);
INSERT INTO khoa (ten_khoa) VALUES
    ("Công Nghệ Thông Tin"),
    ("Xây Dựng"),
    ("Kinh Tế"),
    ("Khoa Học Máy Tính");

INSERT INTO `MonHoc` (ten_monhoc, so_tin_chi, bat_buoc, la_tien_quyet_cua, ma_khoa, mo_ta)
 VALUES
    ("Ngon ngu C++", 3, TRUE, NULL, 2, "lập trình với ngôn ngữ c++"),
    ("Triet hoc Mac-lenin", 3, TRUE, NULL, 1, "triết học Mác - Lênin"),
    ("Chinh Tri-Xa Hoi", 2, TRUE, 2, 1, "Chính trị xã hội hiện đại"),
    ("Toan roi rac", 2, FALSE, 0, 2, "Toán rời rạc");


INSERT INTO `LopMonHoc` (ten_lop_monhoc, ma_monhoc)
VALUES
    ("66IT1", 1),
    ("66KT2", 2),
    ("66KT2", 3),
    ("66IT1", 4),
    ("66IT2", 4);

INSERT INTO GiangVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai) VALUES
    ('Nguyễn Văn A', 1, '1980-01-01', '123 Đường ABC', 'Hà Nội', 'nguyenvana@example.com', '0123456789'),
    ('Trần Thị B', 0, '1985-02-15', '456 Đường XYZ', 'Hồ Chí Minh', 'tranthib@example.com', '0987654321'),
    ('Lê Hoàng C', 1, '1990-06-10', '789 Đường DEF', 'Đà Nẵng', 'lehoangc@example.com', '0369852147'),
    ('Phạm Thanh D', 0, '1982-12-25', '321 Đường GHI', 'Hải Phòng', 'phamthanhd@example.com', '0912345678'),
    ('Vũ Thị E', 0, '1995-08-08', '987 Đường JKL', 'Cần Thơ', 'vuthie@example.com', '0765432198'),
    ('Hồ Minh F', 1, '1988-03-18', '654 Đường MNO', 'Hà Nội', 'hominhf@example.com', '0357924681'),
    ('Đặng An G', 1, '1993-07-22', '159 Đường PQR', 'Hồ Chí Minh', 'dangan@example.com', '0846239751'),
    ('Trương Kim H', 0, '1997-11-11', '753 Đường STU', 'Đà Nẵng', 'truongkimh@example.com', '0913578246'),
    ('Lương Minh I', 1, '1984-09-30', '258 Đường VWX', 'Hải Phòng', 'luongminhi@example.com', '0987412365'),
    ('Hoàng Thị K', 0, '1991-04-05', '852 Đường YZ', 'Cần Thơ', 'hoangthik@example.com', '0321654987');
INSERT INTO SinhVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong)
VALUES 
    ('Nguyễn Văn An', 1, '2000-01-01', '123 ABC Street, Ho Chi Minh City', 'Hanoi', 'nguyenvanan@example.com', '0123456789', '2021-09-01'),
    ('Trần Thị Bình', 0, '2000-02-02', '456 XYZ Street, Hanoi', 'Hanoi', 'tranthibinh@example.com', '0123456790', '2021-09-01'),
    ('Lê Hoàng Cường', 1, '2000-03-03', '789 PQR Street, Da Nang', 'Hanoi', 'lehoangcuong@example.com', '0123456791', '2021-09-01'),
    ('Phạm Thị Dung', 0, '2000-04-04', '321 MNO Street, Ho Chi Minh City', 'Hanoi', 'phamthidung@example.com', '0123456792', '2021-09-01'),
    ('Nguyễn Thanh Hải', 1, '2000-05-05', '654 STU Street, Ho Chi Minh City', 'Hanoi', 'nguyenthanhhai@example.com', '0123456793', '2021-09-01'),
    ('Vũ Thị Hoa', 0, '2000-06-06', '987 DEF Street, Ho Chi Minh City', 'Hanoi', 'vuthihoa@example.com', '0123456794', '2021-09-01'),
    ('Nguyễn Văn Nam', 1, '2000-07-07', '159 GHI Street, Hanoi', 'Hanoi', 'nguyenvannam@example.com', '0123456795', '2021-09-01'),
    ('Trần Thị Quỳnh', 0, '2000-08-08', '753 JKL Street, Ho Chi Minh City', 'Hanoi', 'tranthiquynh@example.com', '0123456796', '2021-09-01'),
    ('Lê Văn Sơn', 1, '2000-09-09', '258 UVW Street, Hanoi', 'Hanoi', 'levanson@example.com', '0123456797', '2021-09-01'),
    ('Phạm Thị Tú', 0, '2000-10-10', '852 XYZ Street, Ho Chi Minh City', 'Hanoi', 'phamthitu@example.com', '0123456798', '2021-09-01');

INSERT INTO `LopQuanLi` (ma_gv, ten_lop_quanli, ma_khoa)
VALUES
    (1, "66IT5", 2),
    (2, "66IT4", 2),
    (4, "67KT3", 3),
    (5, "64IT1", 2);

INSERT INTO `lopquanli_sinhvien` (ma_lop_quanli, mssv) 
VALUES (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 6),
    (3, 5),
    (3, 7),
    (4, 8),
    (4, 9),
    (4, 10);

INSERT INTO nhanviendaotao (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai)
VALUES ('Do An OOP', 1, '1995-01-01', '12345 ABC Street, Ha Noi City', 'Ha Noi', 'doanoop@huce.edu.vn', '0123456789');

INSERT INTO lopmonhoc_giangvien (ma_gv, ma_lop_monhoc)
VALUES (1, 1),
        (1, 2),
        (1, 4),
        (3, 3),
        (3, 5);

INSERT INTO diemsinhvien (ma_lop_monhoc,mssv, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki, ghi_chu)
VALUES (1, 1, 30, 7.8, 9.0, "hoc lai lan 1"),
        (1, 2, 30, 6.5, 6.9, ""),
        (2, 1, 29, 4.5, 4.0, ""),
        (2, 4, 25, 1.0, 1.0, "vi pham quy che"),
        (2, 5, 29, 7.5, 9.0, ""),
        (2, 6, 30, 8.5, 2.5, "");
