-- Active: 1683203836476@@127.0.0.1@3306@doanoop

--                          XEM

SELECT * FROM Khoa 
INNER JOIN khoa_monhoc ON khoa.ma_khoa = khoa_monhoc.ma_khoa
INNER JOIN monhoc ON khoa_monhoc.ma_mon_hoc = monhoc.ma_mon_hoc;
SELECT * FROM monhoc 
INNER JOIN khoa_monhoc ON khoa_monhoc.ma_mon_hoc = monhoc.ma_mon_hoc
INNER JOIN khoa ON khoa.ma_khoa = khoa_monhoc.ma_khoa;

-- Quản lí danh sách môn  số tín, bắt /tc, môn tiên quyết, khoa
SELECT 
ma_monhoc, ten_monhoc, so_tin_chi, bat_buoc, la_tien_quyet_cua, ten_khoa, khoa.ma_khoa, mo_ta
FROM MonHoc
INNER JOIN khoa ON monhoc.ma_khoa = khoa.ma_khoa;

SELECT ten_lop_quan_li FROM LopQuanLi
INNER JOIN khoa ON lopquanli.ma_khoa = khoa.ma_khoa 
WHERE ten_khoa = "Công nghê thông tin";
-- Quản lí danh sách sinh viên
-- UPDATE SinhVien SET ma_lop_quan_li = NULL WHERE mssv = 1;
SELECT 
* 
FROM `SinhVien`;

SELECT
-- *
sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa, sinhvien.ma_lop_quan_li, khoa.ma_khoa
FROM `SinhVien`
INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li
INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa
WHERE ho_ten LIKE "%An%";

SELECT 
sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa, sinhvien.ma_lop_quan_li, khoa.ma_khoa
FROM `SinhVien`
INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li
INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa
ORDER BY sinhvien.mssv;

INSERT INTO SinhVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ma_lop_quan_li)
VALUE (?, ?, ?, ?, ?, ?, ?, ?);

SELECT 
monhoc.ma_mon_hoc, ten_mon_hoc, ten_lop_mon_hoc, so_tin_chi, diem_chuyen_can, diem_giua_ki, diem_cuoi_ki, ghi_chu
FROM diemsinhvien
INNER JOIN lopmonhoc ON LopMonHoc.ma_lop_mon_hoc = diemsinhvien.ma_lop_mon_hoc
INNER JOIN monhoc ON LopMonHoc.ma_mon_hoc = monhoc.ma_mon_hoc
-- WHERE diemsinhvien.mssv = 2
ORDER BY monhoc.ma_mon_hoc;

-- Danh sách giảng viên
SELECT * FROM giangvien;
-- DELETE FROM LopQuanLi_SinhVien WHERE mssv = 13;
SELECT COUNT(*) FROM diemsinhvien WHERE mssv = 1;

SELECT 
ma_lop_quan_li
FROM lopquanli 
INNER JOIN KHOA ON lopquanli.ma_khoa = khoa.ma_khoa
WHERE ten_lop_quan_li = "66IT5" AND ten_khoa = "Công nghệ thông tin";
SELECT 
sinhvien.mssv, ho_ten, khoa.ten_khoa, ten_monhoc, ten_lop_monhoc, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki, ghi_chu
FROM sinhvien
INNER JOIN diemsinhvien ON sinhvien.mssv = diemsinhvien.mssv
INNER JOIN lopmonhoc ON diemsinhvien.ma_lop_monhoc = lopmonhoc.ma_lop_monhoc
INNER JOIN monhoc ON lopmonhoc.ma_monhoc = monhoc.ma_monhoc
INNER JOIN khoa ON monhoc.ma_khoa = khoa.ma_khoa
ORDER BY monhoc.ma_monhoc;


SELECT ten_lop_quanli 
FROM LopQuanLi 
INNER JOIN khoa ON lopquanli.ma_khoa = khoa.ma_khoa
WHERE khoa.ten_khoa = "Công Nghệ Thông Tin";

-- SELECT 
-- *
-- FROM SinhVien
-- INNER JOIN diemsinhvien ON sinhvien.mssv = diemsinhvien.mssv
-- INNER JOIN LopMonHoc ON LopMonHoc.ma_lop_mon_hoc = diemsinhvien.ma_lop_mon_hoc
-- INNER JOIN MonHoc ON LopMonHoc.ma_mon_hoc = MonHoc.ma_mon_hoc
-- ORDER BY monhoc.ma_mon_hoc;
