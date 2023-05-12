-- Active: 1683203836476@@127.0.0.1@3306@doanoop2

--                          XEM



-- Quản lí danh sách môn + số tín, bắt /tc, môn tiên quyết, khoa
SELECT * FROM MonHoc;



-- Quản lí danh sách sinh viên
SELECT * FROM `SinhVien`;



-- Danh sách giảng viên
SELECT * FROM giangvien;




-- Lấy sinh viên theo lớp quản lí
SELECT 
sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, que_quan, email, so_dien_thoai, ten_lop_quanli, khoa
FROM sinhvien 
INNER JOIN lopquanli_sinhvien ON sinhvien.mssv = lopquanli_sinhvien.mssv
INNER JOIN lopquanli ON lopquanli_sinhvien.ma_lop_quanli = lopquanli.ma_lop_quanli;

-- Lấy 66IT5

-- SELECT 
-- sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, que_quan, email, so_dien_thoai, ten_lop_quanli, khoa
-- FROM sinhvien 
-- INNER JOIN lopquanli_sinhvien ON sinhvien.mssv = lopquanli_sinhvien.mssv
-- INNER JOIN lopquanli ON lopquanli_sinhvien.ma_lop_quanli = lopquanli.ma_lop_quanli
-- WHERE lopquanli.ten_lop_quanli = "66IT5";






-- Quản lí bảng điểm tất cả các lớp môn học của những sinh viên theo lớp quản lí
SELECT 
-- *
sinhvien.mssv, ho_ten, ten_lop_monhoc, ten_monhoc, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki, ghi_chu
FROM sinhvien
INNER JOIN diemsinhvien ON sinhvien.mssv = diemsinhvien.mssv

INNER JOIN lopmonhoc ON diemsinhvien.ma_lop_monhoc = lopmonhoc.ma_lop_monhoc
INNER JOIN monhoc ON lopmonhoc.ma_monhoc = monhoc.ma_monhoc

INNER JOIN lopquanli_sinhvien ON sinhvien.mssv = lopquanli_sinhvien.mssv
INNER JOIN lopquanli ON lopquanli_sinhvien.ma_lop_quanli = lopquanli.ma_lop_quanli
-- WHERE lopquanli.ten_lop_quanli = "66IT5"
ORDER BY lopquanli.ma_lop_quanli;






-- Quản lí các lớp môn học
SELECT 
--*
lopmonhoc.ma_lop_monhoc, ten_lop_monhoc, lopmonhoc.ma_monhoc, ten_monhoc, khoa, ho_ten, so_dien_thoai, email, mo_ta
FROM lopmonhoc
INNER JOIN monhoc ON lopmonhoc.ma_monhoc = monhoc.ma_monhoc
INNER JOIN lopmonhoc_giangvien ON lopmonhoc.ma_lop_monhoc = lopmonhoc_giangvien.ma_lop_monhoc
INNER JOIN giangvien ON lopmonhoc_giangvien.ma_gv = giangvien.ma_gv;


-- Xem sinh viên cùng 1 lớp môn học
SELECT 
-- *
sinhvien.mssv, ho_ten, ngay_sinh, que_quan, email, ngay_vao_truong, ten_lop_monhoc, ten_monhoc
FROM sinhvien
INNER JOIN diemsinhvien ON sinhvien.mssv = diemsinhvien.mssv
INNER JOIN lopmonhoc ON diemsinhvien.ma_lop_monhoc = lopmonhoc.ma_lop_monhoc
INNER JOIN monhoc ON lopmonhoc.ma_monhoc = monhoc.ma_monhoc
WHERE ten_lop_monhoc = "66IT1"
ORDER BY sinhvien.mssv;







-- Quản lí điểm các sinh viên cùng lớp quản lí
SELECT 
-- *
sinhvien.mssv, ho_ten, ten_lop_monhoc, ten_monhoc, ten_lop_quanli, lopquanli.khoa
FROM sinhvien
INNER JOIN diemsinhvien ON sinhvien.mssv = diemsinhvien.mssv

INNER JOIN lopmonhoc ON diemsinhvien.ma_lop_monhoc = lopmonhoc.ma_lop_monhoc
INNER JOIN monhoc ON lopmonhoc.ma_monhoc = monhoc.ma_monhoc

INNER JOIN lopquanli_sinhvien ON sinhvien.mssv = lopquanli_sinhvien.mssv
INNER JOIN lopquanli ON lopquanli_sinhvien.ma_lop_quanli = lopquanli.ma_lop_quanli
ORDER BY sinhvien.mssv;


-- Quản lí xem bên trong 1 lớp môn học
SELECT 
-- *
sinhvien.mssv, ho_ten, ten_lop_quanli, lopquanli.khoa, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki, ghi_chu
FROM sinhvien
INNER JOIN diemsinhvien ON sinhvien.mssv = diemsinhvien.mssv

INNER JOIN lopmonhoc ON diemsinhvien.ma_lop_monhoc = lopmonhoc.ma_lop_monhoc
INNER JOIN monhoc ON lopmonhoc.ma_monhoc = monhoc.ma_monhoc

INNER JOIN lopquanli_sinhvien ON sinhvien.mssv = lopquanli_sinhvien.mssv
INNER JOIN lopquanli ON lopquanli_sinhvien.ma_lop_quanli = lopquanli.ma_lop_quanli
-- WHERE lopmonhoc.ma_lop_monhoc = 2
ORDER BY sinhvien.mssv;
















--                          THÊM

INSERT INTO SinhVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong)
VALUES 
    ('Nguyễn Văn An', 1, '2000-01-01', '123 ABC Street, Ho Chi Minh City', 'Hanoi', 'nguyenvanan@example.com', '0123456789', '2021-09-01');

-- Chỉ thêm sinh viên xong mới được thêm "điểm sinh viên"
INSERT INTO diemsinhvien VALUES();


INSERT INTO monhoc VALUES();
INSERT INTO lopmonhoc VALUES();
INSERT INTO giangvien VALUES();





--                          SỬA

--                          XOÁ