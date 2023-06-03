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
    private SinhVienService() {}
    private static SinhVienService service;

    public static SinhVienService getInstance(){
        if (service == null) {
           service = new SinhVienService();
        }
        return service;
    }


    private final DataAccess access = DataAccess.getInstance();

    @Override
    public List<SinhVien> layTatCaSinhVien() {
        PreparedStatement statement = access.getStatement("SELECT " +
        "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa, sinhvien.ma_lop_quan_li, khoa.ma_khoa " +
        "FROM `SinhVien` " + 
        "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
        "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
        "ORDER BY sinhvien.mssv");
        List<SinhVien> listSinhVien = new ArrayList<SinhVien>();
        SinhVien temp = null;    

        try (ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                temp = new SinhVien();
                temp.setMaSo(result.getInt("mssv"));
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(result.getDate("ngay_sinh").toLocalDate());
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong").toLocalDate());
                temp.setQueQuan(new DiaChi(result.getString("que_quan")));
                temp.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_thuong_tru")));
                temp.setEmail(result.getString("email"));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                temp.setMaLopQuanLi(result.getInt("ma_lop_quan_li"));
                temp.setMaKhoa(result.getInt("ma_khoa"));
                listSinhVien.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSinhVien;
    }

    @Override
    public SinhVien timKiemSinhVienTheoMaSo(Integer maso) {
        SinhVien temp = new SinhVien();
        PreparedStatement statement = access.getStatement("SELECT " +
        "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa, sinhvien.ma_lop_quan_li, khoa.ma_khoa " +
        "FROM `SinhVien` " + 
        "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
        "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
        "WHERE sinhvien.mssv = ?");
        
        try {
            statement.setInt(1, maso);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                temp.setMaSo(maso);
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(result.getDate("ngay_sinh").toLocalDate());
                temp.setQueQuan(new DiaChi(result.getString("que_quan")));
                temp.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_thuong_tru")));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setEmail(result.getString("email"));
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong").toLocalDate());
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                temp.setMaLopQuanLi(result.getInt("ma_lop_quan_li"));
                temp.setMaKhoa(result.getInt("ma_khoa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public List<SinhVien> timKiemSinhVienTheoTen(String ten) {
        List<SinhVien> kq = new ArrayList<SinhVien>();
        
        try {
            PreparedStatement statement = access.getStatement("SELECT " +
            "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa, sinhvien.ma_lop_quan_li, khoa.ma_khoa " +
            "FROM `SinhVien` " + 
            "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
            "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
            "WHERE ho_ten LIKE ?");
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                SinhVien temp = new SinhVien();
                temp.setMaSo(result.getInt("mssv"));
                temp.setHoTen(new HoTen(result.getString("ho_ten")));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(result.getDate("ngay_sinh").toLocalDate());
                temp.setQueQuan(new DiaChi(result.getString("que_quan")));
                temp.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_thuong_tru")));
                temp.setEmail(result.getString("email"));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong").toLocalDate());
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                temp.setMaLopQuanLi(result.getInt("ma_lop_quan_li"));
                temp.setMaKhoa(result.getInt("ma_khoa"));
                kq.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return kq;
    }

    @Override
    public SinhVien timKiemSinhVienTheoEmail(String email) {
        SinhVien kq = new SinhVien();

        try {
            PreparedStatement statement = access.getStatement("SELECT " +
            "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa, sinhvien.ma_lop_quan_li, khoa.ma_khoa " +
            "FROM `SinhVien` " + 
            "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
            "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
            "WHERE email LIKE ?");
            statement.setString(1, "%" + email + "%");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                kq.setMaSo(result.getInt("mssv"));
                kq.setHoTen(new HoTen(result.getString("ho_ten")));
                kq.setGioiTinh(result.getBoolean("gioi_tinh"));
                kq.setNgaySinh(
                    result.getDate("ngay_sinh").toLocalDate());
                kq.setQueQuan(new DiaChi(result.getString("que_quan")));
                kq.setDiaChiThuongTru(new DiaChi(result.getString("dia_chi_thuong_tru")));
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
    public Integer themMoiSinhVien(SinhVien sinhVien) throws SQLException {
        int maSoSinhVien = 0;
        PreparedStatement statement = access.getStatement("INSERT INTO SinhVien " 
        + "(ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, " + 
        "ngay_vao_truong, ma_lop_quan_li) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, sinhVien.getHoTen().toString());
        statement.setBoolean(2, sinhVien.getGioiTinh());
        statement.setDate(3, sinhVien.getNgaySinhVoiKieuSQL());
        statement.setString(4, sinhVien.getDiaChiThuongTru().formatToSaveDataBase());
        statement.setString(5, sinhVien.getQueQuan().formatToSaveDataBase());
        statement.setString(6, sinhVien.getEmail());
        statement.setString(7, sinhVien.getSoDienThoai());
        statement.setDate(8, sinhVien.getNgayVaoTruongVoiKieuSQL());
        statement.setInt(9, sinhVien.getMaLopQuanLi());

        int soDongAnhHuong = statement.executeUpdate();

        if (soDongAnhHuong == 1) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                maSoSinhVien = resultSet.getInt(1);
            }
        } else {
            throw new IllegalArgumentException("Sinh viên này chưa chèn thành công!");
        }
        return maSoSinhVien;
    }

    @Override
    public void capNhatThongTinSinhVien(SinhVien sinhVien) throws SQLException, IllegalArgumentException {
        int mssv = sinhVien.getMaSo();
        SinhVien sinhVienTrongDb = timKiemSinhVienTheoMaSo(mssv);
    
        if (sinhVienTrongDb == null) {
            throw new IllegalArgumentException("Không tìm thấy " + mssv);
        }

        PreparedStatement statement = access.getStatement("UPDATE SinhVien SET ho_ten = ?, " +
        "ngay_sinh = ?, gioi_tinh = ?, que_quan = ?, dia_chi_thuong_tru = ?, so_dien_thoai = ?, " + 
        "email = ?, ngay_vao_truong = ?, ma_lop_quan_li = ? WHERE mssv = ?");
        statement.setString(1, sinhVien.getHoTen().toString());
        statement.setDate(2, sinhVien.getNgaySinhVoiKieuSQL());
        statement.setBoolean(3,  sinhVien.getGioiTinh());
        statement.setString(4, sinhVien.getQueQuan().formatToSaveDataBase());
        statement.setString(5, sinhVien.getDiaChiThuongTru().formatToSaveDataBase());
        statement.setString(6, sinhVien.getSoDienThoai());
        statement.setString(7, sinhVien.getEmail());
        statement.setDate(8, sinhVien.getNgayVaoTruongVoiKieuSQL());
        statement.setInt(9, sinhVien.getMaLopQuanLi());
        statement.setInt(10, mssv);

        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1) {
            throw new IllegalArgumentException("Có 1 số lỗi xảy ra với hệ thống. Hãy quay lại sau!");
        }
    }

    @Override
    public boolean xoaSinhVienTheoMaSo(Integer mssv) throws SQLException {
        String query = "DELETE FROM SinhVien WHERE mssv = " + mssv;
        PreparedStatement statement = access.getStatement(query);
        
        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1) {
            throw new IllegalArgumentException("Có lỗi trong quá trình xoá. Hãy thoát phiên và đăng nhập lại");
        }
        return true;
    }

    @Override
    public Integer tongSoSinhVien() {
        try {
            String query = "SELECT COUNT(mssv) FROM SinhVien";
            PreparedStatement statement = access.getStatement(query);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
