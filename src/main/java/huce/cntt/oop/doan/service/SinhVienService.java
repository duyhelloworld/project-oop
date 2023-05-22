package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;
import huce.cntt.oop.doan.interfaces.ISinhVienService;

public class SinhVienService implements ISinhVienService {

    private final DataAccess access = DataAccess.getInstance();

    @Override
    public List<SinhVien> layTatCaSinhVien() {
        PreparedStatement statement = access.getStatement("SELECT " +
        "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa " + 
        "FROM `SinhVien` " +
        "INNER JOIN lopquanli_sinhvien ON lopquanli_sinhvien.mssv = sinhvien.mssv " + 
        "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = lopquanli_sinhvien.ma_lop_quan_li " + 
        "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
        "ORDER BY sinhvien.mssv");
        List<SinhVien> listSinhVien = new ArrayList<SinhVien>();
        SinhVien temp = null;

        try {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                temp = new SinhVien();
                temp.setMaSo(result.getInt("mssv"));
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(result.getDate("ngay_sinh").toLocalDate());
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong").toLocalDate());
                temp.setQueQuan(new DiaChi(result.getString("que_quan")));
                temp.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_hien_tai")));
                temp.setEmail(result.getString("email"));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                listSinhVien.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSinhVien;
    }

    @Override
    public SinhVien timKiemSinhVienTheoMaSo(Integer maso){
        SinhVien sv = new SinhVien();
        PreparedStatement statement = access.getStatement("SELECT * FROM SinhVien WHERE mssv = ?");
        
        try {
            statement.setInt(1, maso);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                sv.setMaSo(maso);
                sv.setHoTen(new HoTen(result.getString("ho_ten")));
                sv.setGioiTinh(result.getBoolean("gioi_tinh"));
                sv.setNgaySinh(
                        result.getDate("ngay_sinh").toLocalDate());
                sv.setQueQuan(new DiaChi(result.getString("que_quan")));
                sv.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_hien_tai")));
                sv.setSoDienThoai(result.getString("so_dien_thoai"));
                sv.setEmail(result.getString("email"));
                sv.setNgayVaoTruong(result.getDate("ngay_vao_truong").toLocalDate());
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.out.println("Exception :" + e.getMessage());
        }
        return sv;
    }

    @Override
    public List<SinhVien> timKiemSinhVienTheoTen(String ten){
        List<SinhVien> kq = new ArrayList<SinhVien>();
        PreparedStatement statement = access.getStatement("SELECT * FROM SinhVien WHERE ho_ten LIKE ?");

        try {
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            SinhVien temp = new SinhVien();
            while (result.next()) {
                temp.setMaSo(result.getInt("mssv"));
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(
                    result.getDate("ngay_sinh").toLocalDate());
                temp.setQueQuan(new DiaChi(result.getString("que_quan")));
                temp.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_hien_tai")));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                kq.add(temp);
                temp = null;
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return kq;
    }

    @Override
    public Integer themMoiSinhVien(SinhVien sinhVien) {
        int maSoSinhVien = 0;
        try {
            if (sinhVien.getMaSo() != null) {
                throw new IllegalArgumentException("'MSSV' đang mang giá trị!!!");
            }
            PreparedStatement statement = access.getStatement("INSERT INTO SinhVien (ho_ten, gioi_tinh, ngay_sinh, dia_chi_hien_tai, que_quan, email, so_dien_thoai, ngay_vao_truong) VALUE (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, sinhVien.getHoTen().toString());
            statement.setBoolean(2, sinhVien.getGioiTinh());
            statement.setDate(3, sinhVien.getNgaySinhVoiKieuSQL());
            statement.setString(4, sinhVien.getDiaChiThuongTru().formatToSaveDataBase());
            statement.setString(5, sinhVien.getQueQuan().formatToSaveDataBase());
            statement.setString(6, sinhVien.getEmail());
            statement.setString(7, sinhVien.getSoDienThoai());
            statement.setDate(8, sinhVien.getNgayVaoTruongVoiKieuSQL());

            int soDongAnhHuong = statement.executeUpdate();

            if (soDongAnhHuong == 1) {
                System.out.println("inserted successfully!");
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    maSoSinhVien = resultSet.getInt(1);
                }
            } else {
                throw new IllegalStateException("Sinh viên này chưa chèn thành công!");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.out.println("Exception :" + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("We meet some problem when inserting!");
            System.out.println("Exception :" + e.getMessage());
        }
        return maSoSinhVien;
    }

    @Override
    public void capNhatThongTinSinhVien(Integer mssv, SinhVien sinhVienTruyenVao) {
        SinhVien sinhVienLayRaTuDatabase = timKiemSinhVienTheoMaSo(mssv);
        
        try {
            if (sinhVienLayRaTuDatabase == null) {
                throw new IllegalArgumentException("Not found mssv " + mssv);
            }

            PreparedStatement statement = access.getStatement("UPDATE SinhVien SET ho_ten = ?, ngay_sinh = ?, gioi_tinh = ?, que_quan = ?, dia_chi_hien_tai = ?, so_dien_thoai = ? WHERE mssv = ?");
            if (sinhVienTruyenVao.getHoTen() != sinhVienTruyenVao.getHoTen()) {
                statement.setString(1, sinhVienTruyenVao.getHoTen().toString());
            } else {
                statement.setString(1, sinhVienLayRaTuDatabase.getHoTen().toString());
            }

            if (sinhVienTruyenVao.getNgaySinh() != sinhVienTruyenVao.getNgaySinh()) {
                statement.setDate(2, sinhVienTruyenVao.getNgaySinhVoiKieuSQL());
            } else {
                statement.setDate(2, sinhVienLayRaTuDatabase.getNgaySinhVoiKieuSQL());
            }

            if (sinhVienTruyenVao.getGioiTinh() != sinhVienTruyenVao.getGioiTinh()) {
                statement.setBoolean(3,  sinhVienTruyenVao.getGioiTinh());
            } else {
                statement.setBoolean(3, sinhVienLayRaTuDatabase.getGioiTinh());
            }

            if (sinhVienTruyenVao.getQueQuan() != sinhVienLayRaTuDatabase.getQueQuan()) {
                statement.setString(4, sinhVienTruyenVao.getQueQuan().formatToSaveDataBase());
            } else {
                statement.setString(4, sinhVienLayRaTuDatabase.getQueQuan().formatToSaveDataBase());
            }

            if (sinhVienTruyenVao.getDiaChiThuongTru() != sinhVienLayRaTuDatabase.getDiaChiThuongTru()) {
                statement.setString(5, sinhVienTruyenVao.getDiaChiThuongTru().formatToSaveDataBase());
            } else {
                statement.setString(5, sinhVienLayRaTuDatabase.getDiaChiThuongTru().formatToSaveDataBase());
            }

            if (sinhVienTruyenVao.getSoDienThoai()!= sinhVienLayRaTuDatabase.getSoDienThoai()) {
                statement.setString(6, sinhVienTruyenVao.getSoDienThoai());
            } else {
                statement.setString(6, sinhVienLayRaTuDatabase.getSoDienThoai());
            }
            statement.setInt(7, mssv);
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SinhVien timKiemSinhVienTheoEmail(String email) {
        SinhVien kq = new SinhVien();

        if (!email.contains("@")) {
            email = email.concat("@huce.edu.vn");
        }

        try {
            PreparedStatement statement = access.getStatement("SELECT * FROM SinhVien WHERE email = ?");
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                kq.setMaSo(result.getInt("mssv"));
                kq.setHoTen(new HoTen(result.getString("ho_ten")));
                kq.setGioiTinh(result.getBoolean("gioi_tinh"));
                kq.setNgaySinh(
                    result.getDate("ngay_sinh").toLocalDate());
                kq.setQueQuan(new DiaChi(result.getString("que_quan")));
                kq.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_hien_tai")));
                kq.setSoDienThoai(result.getString("so_dien_thoai"));
                kq.setNgayVaoTruong(result.getDate("ngay_vao_truong").toLocalDate());
                kq.setEmail(email);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return kq;
    }

    @Override
    public void xoaSinhVienTheoMaSo(List<Integer> mssvs) {
        
    }
}
