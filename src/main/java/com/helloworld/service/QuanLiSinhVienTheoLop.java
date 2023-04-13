package com.helloworld.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.helloworld.dataconnection.DataAccess;
import com.helloworld.entities.SinhVien;
import com.helloworld.entities.properties.DiaChi;
import com.helloworld.entities.properties.HoTen;

public class QuanLiSinhVienTheoLop {
    private DataAccess access = DataAccess.getInstance();

    public List<SinhVien> laySinhVienTheoLopQuanLi(String tenLop) {
        String query = "SELECT SinhVien.mssv, ho_ten, SinhVien.ten_lop AS lop_quan_li, TinhDiem.ten_lop AS lop_mon_hoc, MonHoc.ten, khoa, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki" 
        + " FROM (`SinhVien` INNER JOIN `LopQuanLi` ON SinhVien.ten_lop = LopQuanLi.ten_lop)" 
        + " INNER JOIN (`MonHoc` INNER JOIN `LopMonHoc` On LopMonHoc.ma_mon = MonHoc.ma_mon)" 
        + " INNER JOIN `TinhDiem` On TinhDiem.mssv = SinhVien.mssv" 
        + "WHERE SinhVien.ten_lop = ?"
        + "ORDER BY SinhVien.mssv;";
        PreparedStatement statement = access.getStatement(query);
        List<SinhVien> kq = new ArrayList<SinhVien>();

        try {
            ResultSet result = statement.executeQuery();
            SinhVien temp = new SinhVien();
            while (result.next()) {
                temp.setMaso(result.getInt("mssv"));
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("ten_lop"));
                temp.setNgaySinh(
                        result.getDate("ngay_sinh").toLocalDate());
                temp.setQueQuan(new DiaChi(result.getString("que_quan")));
                temp.setDiaChiHienTai(new DiaChi(result.getString("dia_chi_hien_tai")));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                kq.add(temp);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
    
}
