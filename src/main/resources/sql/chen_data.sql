-- Active: 1683203836476@@127.0.0.1@3306@doanoop
use doanoop;

INSERT INTO khoa (ten_khoa) VALUES
    ("Công Nghệ Thông Tin"),
    ("Xây Dựng"),
    ("Kinh Tế"),
    ("Khoa Học Máy Tính");

INSERT INTO `MonHoc` (ten_mon_hoc, so_tin_chi, bat_buoc, mon_tien_quyet, mo_ta)
VALUES
    ("Tin học đại cương", 3, TRUE, NULL, "lập trình cơ bản với C, C++"),
    ("Ngôn ngữ C++", 3, TRUE, "Tin học đại cương", "lập trình với ngôn ngữ c++"),
    ("Phương pháp lập trình HĐT", 3, TRUE, "Ngôn ngữ C++", "lập trình hướng đối tượng qua Java"),
    ("Kinh tế vĩ mô", 3, TRUE, NULL, "kinh tế vĩ mô toàn cầu"),
    ("Toán học tính toán", 3, TRUE, NULL, "lập trình xử lí toán học với Python/Mathematica"),
    ("Triết học Mác-Lenin", 3, TRUE, NULL, "triết học Mác - Lênin"),
    ("Chủ Nghĩa XHKH", 2, TRUE, "Triết học Mác-Lenin", "Chủ nghĩa xã hội khoa học"),
    ("Lập trình linux", 2, FALSE, NULL, "làm quen với nhân linux và các distro từ nó"),
    ("Phần mềm nguồn mở", 1, FALSE, NULL, "tận dụng cộng đồng mã nguồn mở trên toàn thế giới"),
    ("Toán Rời Rạc", 2, TRUE, "Toán học tính toán", "Toán rời rạc");

INSERT INTO GiangVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, mat_khau) VALUES
    ('Nguyễn Văn An', 1, '1990-01-01', '1, 3, Quang Trung, Hà Đông', 'Trung Lương, Bình Lục, Hà Nam', 'nguyenvanan@huce.edu.vn', '0123456789', '11111111'),
    ('Nguyễn Thị Bình', 0, '1991-02-02', '1, Thành Công, Quang Trung, Ha Đông', 'Phố Hàng, Hoàn Kiếm, Hà Nội', 'nguyenthibinh@huce.edu.vn', '0987654321', '22222222'),
    ('Trần Văn Cường', 1, '1992-03-03', '1, Nguyễn Trãi, Thanh Xuân', 'Đại La, Hai Bà Trưng, Hà Nội', 'tranvancuong@huce.edu.vn', '0369841527', '33333333'),
    ('Lê Thị Dung', 0, '1993-04-04', '2, Hoàng Diệu, Ba Đình', 'Kim Ngưu, Hai Bà Trưng, Hà Nội', 'lethidung@huce.edu.vn', '0765214896', '44444444'),
    ('Hoàng Thị Hoa', 0, '1995-06-06', '9, Nguyễn Chí Thanh, Đống Đa', 'Thái Bình, Thái Bình', 'hoangthihoa@huce.edu.vn', '0932154876', '55555555'),
    ('Đặng Văn Khoa', 1, '1996-07-07', '12, Trường Chinh, Thanh Xuân', 'Sơn Đông, Hoài Đức, Hà Nội', 'dangvankhoa@huce.edu.vn', '0852145632', '00010007'),
    ('Mai Thị Lan', 0, '1997-08-08', '8, Lê Duẩn, Hoàn Kiếm', 'Quảng Ninh, Quảng Ninh', 'maithilan@huce.edu.vn', '0945632154', '00010008'),
    ('Nguyễn Thị Nga', 0, '1999-10-10', '15, Hoàng Quốc Việt, Cầu Giấy', 'Hưng Yên, Hưng Yên', 'nguyenthinga@huce.edu.vn', '0978654123', '00010010');
INSERT INTO `LopMonHoc` (ten_lop_mon_hoc, ma_mon_hoc, ma_gv)
VALUES
    ("66IT1", 1, 1),
    ("66IT3", 1, 1),
    ("66IT3", 2, 2),
    ("66KT1", 4, 2),
    ("66KT2", 4, 3),
    ("66KT2", 6, 3),
    ("66KT2", 7, 4),
    ("66KT3", 6, 4),
    ("66KT5", 7, 5),
    ("66XD1", 6, 5),
    ("66XD2", 7, 6),
    ("66XD5", 7, 6),
    ("66IT5", 1, 7),
    ("66IT5", 2, 7),
    ("66IT5", 3, 8),
    ("66IT5", 5, 8),
    ("66IT5", 7, 8),
    ("66IT5", 9, 8),
    ("66IT5", 10, 1);

INSERT INTO `LopQuanLi` (ma_gv, ten_lop_quan_li, ma_khoa)
VALUES
    (3, "66IT1", 1),
    (1, "66IT3", 1),
    (6, "66IT5", 1),
    (5, "66KT1", 3),
    (8, "66KT2", 3),
    (7, "66KT5", 3),
    (2, "66XD1", 2),
    (4, "66XD5", 2);

INSERT INTO SinhVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ma_lop_quan_li)
    VALUES
    ('Hoàng Quang Sinh', 1, '1990-01-01', '1, 3, Quang Trung, Hà Đông', 'Trung Lương, Bình Lục, Hà Nam', 'hoangquangsinh@huce.edu.vn', '0123456789', "2021-01-01", 1),
    ('Nguyễn Văn An', 1, '1992-05-10', '1, Thành Công, Quang Trung, Hà Đông', 'Mễ Trì, Nam Từ Liêm, Hà Nội', 'nguyenvanan1@huce.edu.vn', '0987654321', "2022-01-03", 1),
    ('Lê Thị Hương', 0, '1991-07-15', '1, Nguyễn Trãi, Thanh Xuân', 'Ngọc Lâm, Long Biên, Hà Nội', 'lethihuong@huce.edu.vn', '0123412345', "2022-09-01", 2),
    ('Trần Văn Quân', 1, '1990-09-20', '2, Hoàng Diệu, Ba Đình', 'Phương Lưu, Cẩm Xuyên, Hà Tĩnh', 'tranvanquan@huce.edu.vn', '0567891234', "2022-01-02", 2),
    ('Phạm Thị Mai', 0, '1993-03-05', '5, Trường Chinh, Hai Bà Trưng', 'Tứ Kỳ, Hải Dương, Hải Dương', 'phamthimai@huce.edu.vn', '0932145678', "2023-01-03", 2),
    ('Đỗ Quốc Bảo', 1, '1992-11-18', '7, Lê Duẩn, Hoàn Kiếm', 'Tân Trụ, Tân Trụ, Long An', 'doquocbao@huce.edu.vn', '0978654321', "2021-01-01", 3),
    ('Nguyễn Thị Hạnh', 0, '1990-06-30', '10, Lý Thường Kiệt, Đống Đa', 'Thạch Kim, Thạch Thất, Hà Nội', 'nguyenthihanh@huce.edu.vn', '0123678901', "2021-12-29", 3),
    ('Trương Minh Đức', 1, '1991-04-25', '15, Huỳnh Thúc Kháng, Đống Đa', 'Nghĩa Đàn, Nghĩa Đàn, Nghệ An', 'truongminhduc@huce.edu.vn', '0987123456', "2022-05-13", 4),
    ('Lê Thị Ngọc', 0, '1993-08-12', '20, Phạm Hùng, Cầu Giấy', 'Giang Tiên, Đại Lộc, Quảng Nam', 'lethingoc@huce.edu.vn', '0956789123', "2022-09-18", 4),
    ('Vũ Văn Tuấn', 1, '1991-02-15', '25, Trần Phú, Hà Đông', 'Thanh Liễu, Thanh Liễu, Hà Nam', 'vuvantuan@huce.edu.vn', '0912345678', "2021-10-08", 4);

INSERT INTO diemsinhvien (mssv, ma_lop_mon_hoc, diem_chuyen_can, diem_giua_ki, diem_cuoi_ki, hoc_ki, ghi_chu)
VALUES  
        (10, 1, 5, 5, 5, 1, ""),
        (10, 2, 10, 10, 10, 1, ""),
        (10, 3, 10, 10, 10, 2, ""),
        (10, 4, 10, 10, 10, 3, ""),
        (10, 5, 5, 5, 5, 2, ""),
        (10, 2, 10, 10, 10, 1, ""),
        (10, 3, 5, 5, 5, 2, "");

        -- (1, 1, 0, 0, 0, 2, "hoc lai lan 1"),
        -- (2, 2, 0, 0, 0, 1, ""),
        -- (2, 3, 0, 0, 0, 1, ""),
        -- (2, 4, 0, 0, 0, 2, "vi pham quy che"),
        -- (2, 5, 0, 0, 0, 3, ""),
        -- (5, 6, 0, 0, 0, 3, ""),
        -- (7, 7, 0, 0, 0, 4, ""),
        -- (10, 8, 0, 0, 0, 5, ""),
        -- (4, 9, 0, 0, 0, 5, ""),
        -- (8, 10, 0, 0, 0, 5, "");


INSERT INTO khoa_monhoc (ma_khoa, ma_mon_hoc) 
VALUES  (1, 1),
        (1, 2),
        (1, 3),
        (1, 5),
        (1, 6),
        (1, 7),
        (1, 8),
        (1, 9),
        (1, 10),
        (2, 6),
        (2, 7),
        (3, 4),
        (3, 6),
        (3, 7),
        (4, 1),
        (4, 2),
        (4, 3),
        (4, 5),
        (4, 6),
        (4, 7),
        (4, 8),
        (4, 10);