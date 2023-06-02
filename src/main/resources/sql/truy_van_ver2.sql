-- Active: 1670603812210@@127.0.0.1@3306@doanoop
use doanoop;

SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta 
                    FROM monhoc mh 
                    INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc
                    INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa;

DELETE FROM MonHoc where ma_mon_hoc = 1;

--tìm kiếm môn học theo mã
SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta 
                    FROM monhoc mh 
                    INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc
                    INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa 
                    WHERE mh.ma_mon_hoc = 4;

--tìm kiếm môn học theo tên
SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta 
                    FROM monhoc mh 
                    INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc
                    INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa 
                    WHERE mh.ten_mon_hoc LIKE 'Lập trình C++';

--tìm kiếm các lớp môn học theo tên
SELECT sv.mssv, sv.ho_ten, lql.ten_lop_quan_li, dsv.diem_chuyen_can, dsv.diem_giua_ki, ROUND((dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2, 2) as diem_qt, dsv.diem_cuoi_ki, Round((0.3*(dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2  + 0.7*dsv.diem_cuoi_ki), 2) as diem_tong_ket,
CASE
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 8.5 THEN 'A'
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 8.0 THEN 'B+'
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 7.0 THEN 'B'
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 6.5 THEN 'C+'
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 5.5 THEN 'C'
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 5.0 THEN 'D+'
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 4.0 THEN 'D'
        ELSE 'F'
    END AS diem_chu,
    CASE
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 8.5 THEN 4.0
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 8.0 THEN 3.5
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 7.0 THEN 3.0
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 6.5 THEN 2.5
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 5.5 THEN 2.0
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 5.0 THEN 1.5
        WHEN ((0.3 * (dsv.diem_chuyen_can + dsv.diem_giua_ki) / 2) + (0.7 * dsv.diem_cuoi_ki)) >= 4.0 THEN 1.0
        ELSE 0.0
    END AS diem_he_so_4 
FROM monhoc mh
INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc
INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc
INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv
INNER JOIN lopquanli lql ON lql.ma_lop_quan_li = sv.ma_lop_quan_li
WHERE mh.ten_mon_hoc = 'Lập trình C++' AND lmh.ten_lop_mon_hoc = '66IT4';

SELECT lmh.ten_lop_mon_hoc
FROM monhoc mh
INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc
WHERE mh.ten_mon_hoc = 'Lập trình C++';