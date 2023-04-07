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

public class QuanLiSinhVien {

    private DataAccess access = DataAccess.getInstance();

    public List<SinhVien> layTatCaSinhVien() {
        PreparedStatement statement = access.getStatement("SELECT * FROM SinhVien");
        List<SinhVien> kq = new ArrayList<SinhVien>();

        try {
            ResultSet result = statement.executeQuery();
            SinhVien temp = new SinhVien();
            while (result.next()) {
                temp.setMaso(result.getInt("mssv"));
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
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

    public SinhVien laySinhVienTheoMaSo(Integer maso){
        SinhVien kq = new SinhVien();
        PreparedStatement statement = access.getStatement("SELECT * FROM SinhVien WHERE mssv = ?");
        
        try {
            statement.setInt(1, maso);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                kq.setMaso(maso);
                kq.setHoTen(new HoTen(result.getString("ho_ten")));
                kq.setGioiTinh(result.getBoolean("gioi_tinh"));
                kq.setNgaySinh(
                        result.getDate("ngay_sinh").toLocalDate());
                kq.setQueQuan(new DiaChi(result.getString("que_quan")));
                kq.setDiaChiHienTai(new DiaChi(result.getString("dia_chi_hien_tai")));
                kq.setSoDienThoai(result.getString("so_dien_thoai"));
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<SinhVien> laySinhVienTheoTen(String ten){
        List<SinhVien> kq = new ArrayList<SinhVien>();
        PreparedStatement statement = access.getStatement("SELECT * FROM SinhVien WHERE ho_ten LIKE ?");

        try {
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            SinhVien temp = new SinhVien();
            while (result.next()) {
                temp.setMaso(result.getInt("mssv"));
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
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

    public Boolean capNhatThongTin(Integer mssv, SinhVien sinhVienTruyenVao) {
        SinhVien sinhVienLayRa = laySinhVienTheoMaSo(mssv);
        
        try {
            if (sinhVienLayRa == null) {
                throw new IllegalArgumentException("Not found mssv " + mssv);
            }

            PreparedStatement statement = access.getStatement("UPDATE SinhVien SET ho_ten = ?, ngay_sinh = ?, gioi_tinh = ?, que_quan = ?, dia_chi_hien_tai = ?, so_dien_thoai = ? WHERE mssv = ?");
            if (sinhVienTruyenVao.getHoTen() != null) {
                statement.setString(1, sinhVienTruyenVao.getHoTen().toString());
            } else {
                statement.setString(1, sinhVienLayRa.getHoTen().toString());
            }

            if (sinhVienTruyenVao.getNgaySinh() != null) {
                statement.setDate(2, sinhVienTruyenVao.getNgaySinhVoiKieuSQL());
            } else {
                statement.setDate(2, sinhVienLayRa.getNgaySinhVoiKieuSQL());
            }

            if (sinhVienTruyenVao.getGioiTinh() != null) {
                statement.setBoolean(3,  sinhVienTruyenVao.getGioiTinh());
            } else {
                statement.setBoolean(3, sinhVienLayRa.getGioiTinh());
            }

            if (sinhVienTruyenVao.getQueQuan() != null) {
                statement.setString(4, sinhVienTruyenVao.getQueQuan().formatToSaveDataBase());
            } else {
                statement.setString(4, sinhVienLayRa.getQueQuan().formatToSaveDataBase());
            }

            if (sinhVienTruyenVao.getDiaChiHienTai() != null) {
                statement.setString(5, sinhVienTruyenVao.getDiaChiHienTai().formatToSaveDataBase());
            } else {
                statement.setString(5, sinhVienLayRa.getDiaChiHienTai().formatToSaveDataBase());
            }

            if (sinhVienTruyenVao.getSoDienThoai()!= null) {
                statement.setString(6, sinhVienTruyenVao.getSoDienThoai());
            } else {
                statement.setString(6, sinhVienLayRa.getSoDienThoai());
            }
            statement.setInt(7, mssv);
            int rowAffected = statement.executeUpdate();
            access.closeConnection(statement);

            return rowAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    


}
