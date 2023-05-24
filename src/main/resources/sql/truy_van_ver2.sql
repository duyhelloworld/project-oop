-- Active: 1683203836476@@127.0.0.1@3306@doanoop

--                          XEM

SELECT * FROM MonHoc INNER JOIN khoa ON khoa.ma_khoa = monhoc.ma_khoa;

-- Quản lí danh sách môn  số tín, bắt /tc, môn tiên quyết, khoa
SELECT 
ma_monhoc, ten_monhoc, so_tin_chi, bat_buoc, la_tien_quyet_cua, ten_khoa, khoa.ma_khoa, mo_ta
FROM MonHoc
INNER JOIN khoa ON monhoc.ma_khoa = khoa.ma_khoa;

-- Quản lí danh sách sinh viên
SELECT 
* 
FROM `SinhVien`;

SELECT 
sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quanli, ten_khoa
FROM `SinhVien`
INNER JOIN lopquanli_sinhvien ON lopquanli_sinhvien.mssv = sinhvien.mssv
INNER JOIN lopquanli ON lopquanli.ma_lop_quanli = lopquanli_sinhvien.ma_lop_quanli
INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa
ORDER BY sinhvien.mssv;

-- INSERT INTO sinhvien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong)
-- VALUES(?, ?, ?, ?, );

SELECT sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa, khoa.ma_khoa, lopquanli.ma_lop_quan_li
FROM `SinhVien`
INNER JOIN lopquanli_sinhvien ON lopquanli_sinhvien.mssv = sinhvien.mssv   
INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = lopquanli_sinhvien.ma_lop_quan_li   
INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa  
ORDER BY sinhvien.mssv;

-- Danh sách giảng viên
SELECT * FROM giangvien;

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
WHERE khoa.ten_khoa = Công Nghệ Thông Tin;