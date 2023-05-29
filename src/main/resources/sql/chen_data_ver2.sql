-- Active: 1670603812210@@127.0.0.1@3306@qlsinhvien
use doanoop;

INSERT INTO khoa (ten_khoa) VALUES
    ("Công Nghệ Thông Tin"),
    ("Xây Dựng"),
    ("Kinh Tế"),
    ("Khoa Học Máy Tính");

INSERT INTO `MonHoc` (ten_mon_hoc, so_tin_chi, bat_buoc, ma_mon_tien_quyet, ma_khoa, mo_ta)
 VALUES
    ("Ngon ngu C++", 3, TRUE, NULL, 2, "lập trình với ngôn ngữ c++"),
    ("Triet hoc Mac-lenin", 3, TRUE, NULL, 1, "triết học Mác - Lênin"),
    ("Chinh Tri-Xa Hoi", 2, TRUE, 2, 1, "Chính trị xã hội hiện đại"),
    ("Toan roi rac", 2, FALSE, 0, 2, "Toán rời rạc");


INSERT INTO `LopMonHoc` (ten_lop_mon_hoc, ma_mon_hoc)
VALUES
    ("66IT1", 1),
    ("66KT2", 2),
    ("66KT2", 3),
    ("66IT1", 4),
    ("66IT2", 4);

INSERT INTO GiangVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, mat_khau) VALUES
    ('Nguyễn Văn An', 1, '1990-01-01', '1, 3, Quang Trung, Hà Đông', 'Trung Lương, Bình Lục, Hà Nam', 'nguyenvanan@huce.edu.vn', '0123456789', '00010001'),
    ('Nguyễn Thị Bình', 0, '1991-02-02', '1, Thành Công, Quang Trung, Ha Đông', 'Phố Hàng, Hoàn Kiếm, Hà Nội', 'nguyenthibinh@huce.edu.vn', '0987654321', '00010002'),
    ('Trần Văn Cường', 1, '1992-03-03', '1, Nguyễn Trãi, Thanh Xuân', 'Đại La, Hai Bà Trưng, Hà Nội', 'tranvancuong@huce.edu.vn', '0369841527', '00010003'),
    ('Lê Thị Dung', 0, '1993-04-04', '2, Hoàng Diệu, Ba Đình', 'Kim Ngưu, Hai Bà Trưng, Hà Nội', 'lethidung@huce.edu.vn', '0765214896', '00010004'),
    ('Phạm Minh Đức', 1, '1994-05-05', '5, Cầu Giấy, Cầu Giấy', 'Mỹ Đình, Nam Từ Liêm, Hà Nội', 'phamminhduc@huce.edu.vn', '0321567890', '00010005'),
    ('Hoàng Thị Hoa', 0, '1995-06-06', '9, Nguyễn Chí Thanh, Đống Đa', 'Thái Bình, Thái Bình', 'hoangthihoa@huce.edu.vn', '0932154876', '00010006'),
    ('Đặng Văn Khoa', 1, '1996-07-07', '12, Trường Chinh, Thanh Xuân', 'Sơn Đông, Hoài Đức, Hà Nội', 'dangvankhoa@huce.edu.vn', '0852145632', '00010007'),
    ('Mai Thị Lan', 0, '1997-08-08', '8, Lê Duẩn, Hoàn Kiếm', 'Quảng Ninh, Quảng Ninh', 'maithilan@huce.edu.vn', '0945632154', '00010008'),
    ('Vũ Văn Mạnh', 1, '1998-09-09', '4, Hai Bà Trưng, Hai Bà Trưng', 'Nha Trang, Khánh Hòa', 'vuvanmanh@huce.edu.vn', '0362547895', '00010009'),
    ('Nguyễn Thị Nga', 0, '1999-10-10', '15, Hoàng Quốc Việt, Cầu Giấy', 'Hưng Yên, Hưng Yên', 'nguyenthinga@huce.edu.vn', '0978654123', '00010010');

INSERT INTO SinhVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong)
    VALUES
    ('Hoàng Quang Sinh', 1, '1990-01-01', '1, 3, Quang Trung, Hà Đông', 'Trung Lương, Bình Lục, Hà Nam', 'nguyenvanan@huce.edu.vn', '0123456789', "2021-01-01"),
    ('Nguyễn Văn An', 0, '1992-05-10', '1, Thành Công, Quang Trung, Hà Đông', 'Mễ Trì, Nam Từ Liêm, Hà Nội', 'nguyenvanan1@huce.edu.vn', '0987654321', "2022-01-03"),
    ('Lê Thị Hương', 1, '1991-07-15', '1, Nguyễn Trãi, Thanh Xuân', 'Ngọc Lâm, Long Biên, Hà Nội', 'lethihuong@huce.edu.vn', '0123412345', "2022-09-01"),
    ('Trần Văn Quân', 0, '1990-09-20', '2, Hoàng Diệu, Ba Đình', 'Phương Lưu, Cẩm Xuyên, Hà Tĩnh', 'tranvanquan@huce.edu.vn', '0567891234', "2022-01-02"),
    ('Phạm Thị Mai', 1, '1993-03-05', '5, Trường Chinh, Hai Bà Trưng', 'Tứ Kỳ, Hải Dương, Hải Dương', 'phamthimai@huce.edu.vn', '0932145678', "2023-01-03"),
    ('Đỗ Quốc Bảo', 0, '1992-11-18', '7, Lê Duẩn, Hoàn Kiếm', 'Tân Trụ, Tân Trụ, Long An', 'doquocbao@huce.edu.vn', '0978654321', "2021-01-01"),
    ('Nguyễn Thị Hạnh', 1, '1990-06-30', '10, Lý Thường Kiệt, Đống Đa', 'Thạch Kim, Thạch Thất, Hà Nội', 'nguyenthihanh@huce.edu.vn', '0123678901', "2021-12-29"),
    ('Trương Minh Đức', 0, '1991-04-25', '15, Huỳnh Thúc Kháng, Đống Đa', 'Nghĩa Đàn, Nghĩa Đàn, Nghệ An', 'truongminhduc@huce.edu.vn', '0987123456', "2022-05-13"),
    ('Lê Thị Ngọc', 1, '1993-08-12', '20, Phạm Hùng, Cầu Giấy', 'Giang Tiên, Đại Lộc, Quảng Nam', 'lethingoc@huce.edu.vn', '0956789123', "2022-09-18"),
    ('Vũ Văn Tuấn', 0, '1991-02-15', '25, Trần Phú, Hà Đông', 'Thanh Liễu, Thanh Liễu, Hà Nam', 'vuvantuan@huce.edu.vn', '0912345678', "2021-10-08");


INSERT INTO `LopQuanLi` (ma_gv, ten_lop_quan_li, ma_khoa)
VALUES
    (1, "66IT5", 2),
    (2, "66IT4", 2),
    (4, "67KT3", 3),
    (5, "64IT1", 2);

INSERT INTO `lopquanli_sinhvien` (ma_lop_quan_li, mssv) 
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

INSERT INTO nhanviendaotao (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, mat_khau)
VALUES ('Do An OOP', 1, '1995-01-01', '12345 ABC Street, Ha Noi City', 'Ha Noi', 'doanoop@huce.edu.vn', '0123456789', "88888888");

INSERT INTO lopmonhoc_giangvien (ma_gv, ma_lop_mon_hoc)
VALUES (1, 1),
        (1, 2),
        (1, 4),
        (3, 3),
        (3, 5);

INSERT INTO diemsinhvien (ma_lop_mon_hoc,mssv, diem_chuyen_can, diem_giua_ki, diem_cuoi_ki, ghi_chu)
VALUES (1, 1, 9.5, 7.8, 9.0, "hoc lai lan 1"),
        (1, 2, 7.5, 6.5, 6.9, ""),
        (2, 1, 8.0, 4.5, 4.0, ""),
        (2, 4, 6.0, 1.0, 1.0, "vi pham quy che"),
        (2, 5, 4.4, 7.5, 9.0, ""),
        (2, 6, 7.6, 8.5, 2.5, "");