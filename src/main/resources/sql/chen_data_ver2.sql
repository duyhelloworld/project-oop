-- Active: 1670603812210@@127.0.0.1@3306@doanoop
use doanoop;
INSERT INTO Khoa (ma_khoa, ten_khoa)
VALUES
    (1, 'Khoa Tin học xây dựng'),
    (2, 'Khoa Xây dựng công trình'),
    (3, 'Khoa khoa học máy tính'),
    (4, 'Khoa Quản lý xây dựng'),
    (5, 'Khoa Công nghệ thông tin');
INSERT INTO MonHoc (ma_mon_hoc, ten_mon_hoc, so_tin_chi, bat_buoc, mon_tien_quyet, mo_ta)
VALUES
    (1,'Toán rời rạc', 3, 1, NULL, 'Môn học cung cấp kiến thức về toán rời rạc.'),
    (2,'Lập trình C++', 4, 1, NULL, 'Môn học giảng dạy về lập trình C++.'),
    (3,'Kỹ thuật lập trình', 3, 1, NULL, 'Môn học tập trung vào kỹ thuật lập trình.'),
    (4,'Hệ điều hành', 4, 1, NULL, 'Môn học về hệ điều hành và quản lý tài nguyên.'),
    (5,'Mạng máy tính', 3, 1, NULL, 'Môn học giúp hiểu về cấu trúc và hoạt động của mạng máy tính.'),
    (6,'Cơ sở dữ liệu', 4, 1, NULL, 'Môn học tập trung vào cơ sở dữ liệu và quản lý dữ liệu.'),
    (7,'Lập trình web', 3, 1, NULL, 'Môn học giảng dạy về phát triển ứng dụng web.'),
    (8,'Kỹ thuật truyền thông', 4, 1, NULL, 'Môn học giúp hiểu về các phương pháp truyền thông hiện đại.'),
    (9,'Trí tuệ nhân tạo', 3, 1, NULL, 'Môn học tập trung vào lĩnh vực trí tuệ nhân tạo.'),
    (10,'Học máy', 4, 1, 'Trí tuệ nhân tạo', 'Môn học nâng cao về học máy và thuật toán machine learning.');
INSERT INTO Khoa_MonHoc (ma_mon_hoc, ma_khoa)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),
    (6, 1),
    (7, 1),
    (8, 1),
    (9, 1),
    (10, 1),
    (1, 3),
    (2, 3),
    (3, 3),
    (4, 3),
    (5, 3),
    (6, 3),
    (7, 3),
    (8, 3),
    (9, 3),
    (10, 3),
    (1, 5),
    (2, 5),
    (3, 5),
    (4, 5),
    (5, 5),
    (6, 5),
    (7, 5),
    (8, 5),
    (9, 5),
    (10, 5);
INSERT INTO GiangVien (ma_gv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, mat_khau)
VALUES
    (1, 'Nguyễn Văn A', 1, '1990-01-01', 'Số 10, Đường Hoàng Diệu, Quận Ba Đình, Hà Nội', 'Thành phố Hà Nội', 'nguyenvana@example.com', '1234567890', 'password1'),
    (2, 'Trần Thị B', 0, '1992-05-10', 'Số 25, Đường Trần Phú, Quận Hai Bà Trưng, Hà Nội', 'Thành phố Hà Nội', 'tranthib@example.com', '2345678901', 'password2'),
    (3, 'Lê Văn C', 1, '1985-11-15', 'Số 50, Đường Lý Thường Kiệt, Quận Hoàn Kiếm, Hà Nội', 'Thành phố Hà Nội', 'levanc@example.com', '3456789012', 'password3'),
    (4, 'Nguyễn Thị D', 0, '1991-09-20', 'Số 8, Đường Nguyễn Văn Cừ, Quận Long Biên, Hà Nội', 'Thành phố Hà Nội', 'nguyenthid@example.com', '4567890123', 'password4'),
    (5, 'Phạm Văn E', 1, '1988-07-05', 'Số 15, Đường Hoàng Mai, Quận Hoàng Mai, Hà Nội', 'Thành phố Hà Nội', 'phamvane@example.com', '5678901234', 'password5'),
    (6, 'Đinh Thị F', 0, '1993-03-25', 'Số 40, Đường Trường Chinh, Quận Thanh Xuân, Hà Nội', 'Thành phố Hà Nội', 'dinhthif@example.com', '6789012345', 'password6'),
    (7, 'Hồ Văn G', 1, '1990-06-15', 'Số 18, Đường Nguyễn Trãi, Quận Nam Từ Liêm, Hà Nội', 'Thành phố Hà Nội', 'hovang@example.com', '7890123456', 'password7'),
    (8, 'Nguyễn Thị H', 0, '1987-12-08', 'Số 30, Đường Tây Sơn, Quận Đống Đa, Hà Nội', 'Thành phố Hà Nội', 'nguyenthih@example.com', '8901234567', 'password8'),
    (9, 'Vũ Văn I', 1, '1989-04-30', 'Số 12, Đường Nguyễn Khuyến, Quận Cầu Giấy, Hà Nội', 'Thành phố Hà Nội', 'vuvani@example.com', '9012345678', 'password9'),
    (10, 'Lý Thị K', 0, '1995-02-18', 'Số 20, Đường Đặng Văn Ngữ, Quận Đống Đa, Hà Nội', 'Thành phố Hà Nội', 'lythik@example.com', '0123456789', 'password10'),
    (11, 'Trần Văn L', 1, '1994-08-22', 'Số 35, Đường Xuân Thủy, Quận Cầu Giấy, Hà Nội', 'Thành phố Hà Nội', 'tranvanl@example.com', '3456789012', 'password11'),
    (12, 'Phan Văn M', 1, '1990-03-15', 'Số 5, Đường Trường Sơn, Quận Tây Hồ, Hà Nội', 'Thành phố Hà Nội', 'phanvanm@example.com', '4567890123', 'password12'),
    (13, 'Hoàng Thị N', 0, '1992-11-30', 'Số 22, Đường Nguyễn Công Hoan, Quận Ba Đình, Hà Nội', 'Thành phố Hà Nội', 'hoangthin@example.com', '5678901234', 'password13'),
    (14, 'Bùi Văn O', 1, '1988-07-25', 'Số 17, Đường Láng Hạ, Quận Đống Đa, Hà Nội', 'Thành phố Hà Nội', 'buivano@example.com', '6789012345', 'password14'),
    (15, 'Nguyễn Thị P', 0, '1993-04-12', 'Số 28, Đường Trần Duy Hưng, Quận Cầu Giấy, Hà Nội', 'Thành phố Hà Nội', 'nguyenthip@example.com', '7890123456', 'password15'),
    (16, 'Trương Văn Q', 1, '1989-09-08', 'Số 14, Đường Hồ Tùng Mậu, Quận Nam Từ Liêm, Hà Nội', 'Thành phố Hà Nội', 'truongvanq@example.com', '8901234567', 'password16'),
    (17, 'Lê Thị R', 0, '1987-05-20', 'Số 32, Đường Vạn Phúc, Quận Ba Đình, Hà Nội', 'Thành phố Hà Nội', 'lethir@example.com', '9012345678', 'password17'),
    (18, 'Ngô Văn S', 1, '1991-02-14', 'Số 7, Đường Bưởi, Quận Tây Hồ, Hà Nội', 'Thành phố Hà Nội', 'ngovans@example.com', '0123456789', 'password18'),
    (19, 'Mai Thị T', 0, '1994-10-05', 'Số 19, Đường Trần Phú, Quận Hoàn Kiếm, Hà Nội', 'Thành phố Hà Nội', 'maithit@example.com', '1234567890', 'password19'),
    (20, 'Phùng Văn U', 1, '1986-06-28', 'Số 16, Đường Hàng Than, Quận Hoàn Kiếm, Hà Nội', 'Thành phố Hà Nội', 'phungvanu@example.com', '2345678901', 'password20');
INSERT INTO lopmonhoc(ma_lop_mon_hoc, ten_lop_mon_hoc, ma_mon_hoc, ma_gv)
VALUES
;
INSERT INTO SinhVien (mssv, ho_ten, gioi_tinh, ma_lop_quan_ly, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong)
VALUES
    (1, 'Trần Thị B', 0, 1, '2000-02-02', 'Số 20, Đường Trần Phú, Quận Hai Bà Trưng, Hà Nội', 'Hà Nội', 'tranthib@example.com', '2345678901', '2021-09-01'),
    (2, 'Lê Văn C', 1, 2, '2000-03-03', 'Số 30, Đường Lý Thường Kiệt, Quận Hoàn Kiếm, Hà Nội', 'Hà Nội', 'levanc@example.com', '3456789012', '2021-09-01'),
    (3, 'Nguyễn Văn A', 1, 3, '2000-01-01', 'Số 10, Đường Hoàng Diệu, Quận Ba Đình, Hà Nội', 'Hà Nội', 'nguyenvana@example.com', '1234567890', '2021-09-01'),
    (4, 'Nguyễn Thị D', 0, 4, '2000-04-04', 'Số 40, Đường Nguyễn Văn Cừ, Quận Long Biên, Hà Nội', 'Hà Nội', 'nguyenthid@example.com', '4567890123', '2021-09-01'),
    (5, 'Phạm Văn E', 1, 5, '2000-05-05', 'Số 50, Đường Hoàng Mai, Quận Hoàng Mai, Hà Nội', 'Hà Nội', 'phamvane@example.com', '5678901234', '2021-09-01'),
    (6, 'Lê Thị R', 0, 1, '2000-05-17', 'Số 170, Đường Vạn Phúc, Quận Ba Đình, Hà Nội', 'Hà Nội', 'lethir@example.com', '9012345678', '2021-09-01'),
    (7, 'Vũ Văn I', 1, 2, '2000-09-09', 'Số 90, Đường Nguyễn Khuyến, Quận Cầu Giấy, Hà Nội', 'Hà Nội', 'vuvani@example.com', '9012345678', '2021-09-01'),
    (8, 'Hồ Văn G', 1, 3, '2000-07-07', 'Số 70, Đường Nguyễn Trãi, Quận Nam Từ Liêm, Hà Nội', 'Hà Nội', 'hovang@example.com', '7890123456', '2021-09-01'),
    (9, 'Đinh Thị F', 0, 4, '2000-06-06', 'Số 60, Đường Trường Chinh, Quận Thanh Xuân, Hà Nội', 'Hà Nội', 'dinhthif@example.com', '6789012345', '2021-09-01'),
    (10, 'Nguyễn Thị H', 0, 5, '2000-08-08', 'Số 80, Đường Tây Sơn, Quận Đống Đa, Hà Nội', 'Hà Nội', 'nguyenthih@example.com', '8901234567', '2021-09-01'),
    (11, 'Nguyễn Thị P', 0, 1, '2000-03-15', 'Số 150, Đường Trần Duy Hưng, Quận Cầu Giấy, Hà Nội', 'Hà Nội', 'nguyenthip@example.com', '7890123456', '2021-09-01'),
    (12, 'Hoàng Thị N', 0, 2, '2000-01-13', 'Số 130, Đường Nguyễn Công Hoan, Quận Ba Đình, Hà Nội', 'Hà Nội', 'hoangthin@example.com', '5678901234', '2021-09-01'),
    (13, 'Bùi Văn O', 1, 3, '2000-02-14', 'Số 140, Đường Láng Hạ, Quận Đống Đa, Hà Nội', 'Hà Nội', 'buivano@example.com', '6789012345', '2021-09-01'),
    (14, 'Trần Văn L', 1, 4, '2000-11-11', 'Số 110, Đường Xuân Thủy, Quận Cầu Giấy, Hà Nội', 'Hà Nội', 'tranvanl@example.com', '3456789012', '2021-09-01'),
    (15, 'Lý Thị K', 0, 5, '2000-10-10', 'Số 100, Đường Đặng Văn Ngữ, Quận Đống Đa, Hà Nội', 'Hà Nội', 'lythik@example.com', '0123456789', '2021-09-01'),
    (16, 'Mai Thị T', 0, 1, '2000-07-19', 'Số 190, Đường Trần Phú, Quận Hoàn Kiếm, Hà Nội', 'Hà Nội', 'maithit@example.com', '1234567890', '2021-09-01'),
    (17, 'Phan Văn M', 1, 2, '2000-12-12', 'Số 120, Đường Trường Sơn, Quận Tây Hồ, Hà Nội', 'Hà Nội', 'phanvanm@example.com', '4567890123', '2021-09-01'),
    (18, 'Ngô Văn S', 1, 3, '2000-06-18', 'Số 180, Đường Bưởi, Quận Tây Hồ, Hà Nội', 'Hà Nội', 'ngovans@example.com', '0123456789', '2021-09-01'),
    (19, 'Trương Văn Q', 1, 4, '2000-04-16', 'Số 160, Đường Hồ Tùng Mậu, Quận Nam Từ Liêm, Hà Nội', 'Hà Nội', 'truongvanq@example.com', '8901234567', '2021-09-01'),
    (20, 'Phùng Văn U', 1, 5, '2000-08-20', 'Số 200, Đường Hàng Than, Quận Hoàn Kiếm, Hà Nội', 'Hà Nội', 'phungvanu@example.com', '2345678901', '2021-09-01'),
    (21, 'Trần Văn W', 1, 1, '2000-09-21', 'Số 210, Đường Hào Nam, Quận Đống Đa, Hà Nội', 'Hà Nội', 'tranvanw@example.com', '3456789012', '2021-09-01'),
    (22, 'Lê Thị X', 0, 2, '2000-10-22', 'Số 220, Đường Nguyễn Văn Huyên, Quận Cầu Giấy, Hà Nội', 'Hà Nội', 'lethix@example.com', '4567890123', '2021-09-01'),
    (23, 'Nguyễn Văn Y', 1, 3, '2000-11-23', 'Số 230, Đường Lê Đức Thọ, Quận Nam Từ Liêm, Hà Nội', 'Hà Nội', 'nguyenvany@example.com', '5678901234', '2021-09-01'),
    (24, 'Phạm Thị Z', 0, 4, '2000-12-24', 'Số 240, Đường Bùi Thị Xuân, Quận Hai Bà Trưng, Hà Nội', 'Hà Nội', 'phamthiz@example.com', '6789012345', '2021-09-01'),
    (25, 'Đinh Văn AA', 1, 5, '2000-01-25', 'Số 250, Đường Nguyễn Lương Bằng, Quận Ba Đình, Hà Nội', 'Hà Nội', 'dinhvanaa@example.com', '7890123456', '2021-09-01'),
    (26, 'Hồ Thị BB', 0, 1, '2000-02-26', 'Số 260, Đường Tôn Đức Thắng, Quận Hoàn Kiếm, Hà Nội', 'Hà Nội', 'hothibb@example.com', '8901234567', '2021-09-01'),
    (27, 'Nguyễn Văn CC', 1, 2, '2000-03-27', 'Số 270, Đường Đê La Thành, Quận Đống Đa, Hà Nội', 'Hà Nội', 'nguyenvancc@example.com', '9012345678', '2021-09-01'),
    (28, 'Vũ Thị DD', 0, 3, '2000-04-28', 'Số 280, Đường Phạm Hùng, Quận Cầu Giấy, Hà Nội', 'Hà Nội', 'vuthidd@example.com', '0123456789', '2021-09-01'),
    (29, 'Lê Văn EE', 1, 4, '2000-05-29', 'Số 290, Đường Tây Kết, Quận Nam Từ Liêm, Hà Nội', 'Hà Nội', 'levanee@example.com', '1234567890', '2021-09-01'),
    (30, 'Trương Thị FF', 0, 5, '2000-06-30', 'Số 300, Đường Đặng Thai Mai, Quận Hoàng Mai, Hà Nội', 'Hà Nội', 'truongthiff@example.com', '2345678901', '2021-09-01');
INSERT INTO lopquanli(ma_lop_quan_li, ma_gv, ma_khoa, ten_lop_quan_li)
VALUES
    (1, , , ),
    (2, , , ),
    (3, , , ),
    (4, , , ),
    (5, , , );