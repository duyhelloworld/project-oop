package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.ChuyenDiaChiException;
import huce.cntt.oop.doan.entities.exception.ChuyenHoTenException;
import huce.cntt.oop.doan.entities.exception.ChuyenSoException;
import huce.cntt.oop.doan.entities.exception.DatabaseException;
import huce.cntt.oop.doan.entities.exception.EmailException;
import huce.cntt.oop.doan.entities.exception.NgayGioException;
import huce.cntt.oop.doan.entities.exception.ThemException;
import huce.cntt.oop.doan.entities.exception.XoaException;
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
    public List<SinhVien> layTatCaSinhVien() throws DatabaseException {
        PreparedStatement statement = access.getStatement("SELECT " +
        "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa " +
        "FROM `SinhVien` " + 
        "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
        "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
        "ORDER BY sinhvien.mssv");
        List<SinhVien> listSinhVien = new ArrayList<SinhVien>();
        
        try {
            SinhVien temp = null;    
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                temp = new SinhVien();
                temp.setMaSo(result.getInt("mssv"));
                temp.setHoTen(result.getString("ho_ten"));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(result.getDate("ngay_sinh"));
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong"));
                temp.setQueQuan(result.getString("que_quan"));
                temp.setDiaChiThuongTru(result.getString("dia_chi_thuong_tru"));
                temp.setEmail(result.getString("email"));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                listSinhVien.add(temp);
            }
        } catch (ChuyenDiaChiException | NgayGioException | EmailException | ChuyenSoException | ChuyenHoTenException |SQLException e) {
            throw new DatabaseException("Lỗi dữ liệu trong database!!!");
        }
        return listSinhVien;
    }

    @Override
    public SinhVien timKiemSinhVienTheoMaSo(Integer maso) throws DatabaseException {
        PreparedStatement statement = access.getStatement("SELECT " +
        "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa " +
        "FROM `SinhVien` " + 
        "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
        "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
        "WHERE sinhvien.mssv = ?");
        
        try {
            statement.setInt(1, maso);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                SinhVien temp = new SinhVien();
                temp.setMaSo(maso);
                temp.setHoTen(result.getString("ho_ten"));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(result.getDate("ngay_sinh"));
                temp.setQueQuan(result.getString("que_quan"));
                temp.setDiaChiThuongTru(result.getString("dia_chi_thuong_tru"));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setEmail(result.getString("email"));
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong"));
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                return temp;
            }
        } catch (ChuyenDiaChiException | NgayGioException | EmailException | ChuyenSoException | ChuyenHoTenException |SQLException e) {
            throw new DatabaseException("Lỗi dữ liệu trong database!!!");
        }
        return null;
    }

    @Override
    public List<SinhVien> timKiemSinhVienTheoTen(String ten) throws DatabaseException {
        List<SinhVien> kq = new ArrayList<SinhVien>();

        try {
            PreparedStatement statement = access.getStatement("SELECT " +
            "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa " +
            "FROM `SinhVien` " + 
            "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
            "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
            "WHERE ho_ten LIKE ?");
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            if (!result.first()) {
                return null;
            }
            while (result.next()) {
                SinhVien temp = new SinhVien();
                temp.setMaSo(result.getInt("mssv"));
                temp.setHoTen(result.getString("ho_ten"));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(result.getDate("ngay_sinh"));
                temp.setQueQuan(result.getString("que_quan"));
                temp.setDiaChiThuongTru(result.getString("dia_chi_thuong_tru"));
                temp.setEmail(result.getString("email"));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong"));
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                kq.add(temp);
            }
            return kq;
        }  catch (ChuyenDiaChiException | NgayGioException | EmailException | ChuyenSoException | ChuyenHoTenException |SQLException e) {
            throw new DatabaseException("Lỗi dữ liệu trong database!!!");
        }
    }

    @Override
    public SinhVien timKiemSinhVienTheoEmail(String email) throws DatabaseException {
        try {
            PreparedStatement statement = access.getStatement("SELECT " +
            "sinhvien.mssv, ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, ngay_vao_truong, ten_lop_quan_li, ten_khoa " +
            "FROM `SinhVien` " + 
            "INNER JOIN lopquanli ON lopquanli.ma_lop_quan_li = sinhvien.ma_lop_quan_li " +
            "INNER JOIN khoa ON khoa.ma_khoa = lopquanli.ma_khoa " +
            "WHERE email LIKE ?");
            statement.setString(1, "%" + email + "%");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                SinhVien temp = new SinhVien();
                temp.setMaSo(result.getInt("mssv"));
                temp.setHoTen(result.getString("ho_ten"));
                temp.setGioiTinh(result.getBoolean("gioi_tinh"));
                temp.setNgaySinh(
                    result.getDate("ngay_sinh"));
                temp.setQueQuan(result.getString("que_quan"));
                temp.setDiaChiThuongTru(result.getString("dia_chi_thuong_tru"));
                temp.setSoDienThoai(result.getString("so_dien_thoai"));
                temp.setNgayVaoTruong(result.getDate("ngay_vao_truong"));
                temp.setTenLopQuanLi(result.getString("ten_lop_quan_li"));
                temp.setKhoa(result.getString("ten_khoa"));
                temp.setEmail(email);
                return temp;
            }
        } catch (ChuyenDiaChiException | NgayGioException | EmailException | ChuyenSoException | ChuyenHoTenException |SQLException e) {
            throw new DatabaseException("Lỗi dữ liệu trong database!!!");
        }
        return null;
    }


    @Override
    public Integer themMoiSinhVien(SinhVien sinhVien) throws ThemException {
        int maSoSinhVien = 0;
        PreparedStatement statement = access.getStatement("INSERT INTO SinhVien " 
        + "(ho_ten, gioi_tinh, ngay_sinh, dia_chi_thuong_tru, que_quan, email, so_dien_thoai, " + 
        "ngay_vao_truong, ma_lop_quan_li) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        try {
            statement.setString(1, sinhVien.getHoTen().toString());
            statement.setBoolean(2, sinhVien.getGioiTinh());
            statement.setDate(3, sinhVien.getNgaySinhVoiKieuSQL());
            statement.setString(4, sinhVien.getDiaChiThuongTru());
            statement.setString(5, sinhVien.getQueQuan());
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
                throw new ThemException("Lỗi hệ thống khi thêm mới sinh viên !");
            }
        } catch (SQLException e) {
            if (contraintException(e)) {
                throw new ThemException("Trùng thông tin sinh viên tại email, số điện thoại.\nHãy kiểm tra lại");
            }
            throw new ThemException("Có lỗi khi thêm sinh viên có mã số " + maSoSinhVien);
        }
        return maSoSinhVien;
    }

    @Override
    public void capNhatThongTinSinhVien(SinhVien sinhVien) throws CapNhatException, DatabaseException{
        int mssv = sinhVien.getMaSo();
        
        try {
            SinhVien sinhVienTrongDb = timKiemSinhVienTheoMaSo(mssv);
        
            if (sinhVienTrongDb == null) {
                throw new CapNhatException("Lỗi : Không tìm thấy sinh viên với " + mssv);
            }
            PreparedStatement statement = access.getStatement("UPDATE SinhVien SET ho_ten = ?, " +
            "ngay_sinh = ?, gioi_tinh = ?, que_quan = ?, dia_chi_thuong_tru = ?, so_dien_thoai = ?, " + 
            "email = ?, ngay_vao_truong = ?, ma_lop_quan_li = ? WHERE mssv = ?");
            statement.setString(1, sinhVien.getHoTen().toString());
            statement.setDate(2, sinhVien.getNgaySinhVoiKieuSQL());
            statement.setBoolean(3,  sinhVien.getGioiTinh());
            statement.setString(4, sinhVien.getQueQuan());
            statement.setString(5, sinhVien.getDiaChiThuongTru());
            statement.setString(6, sinhVien.getSoDienThoai());
            statement.setString(7, sinhVien.getEmail());
            statement.setDate(8, sinhVien.getNgayVaoTruongVoiKieuSQL());
            statement.setInt(9, sinhVien.getMaLopQuanLi());
            statement.setInt(10, mssv);

            int rowAffected = statement.executeUpdate();
            if (rowAffected != 1) {
                throw new CapNhatException("Cập nhật không thành công do lỗi hệ thống!");
            }
        } catch (SQLException e) {
            if (contraintException(e)) {
                throw new CapNhatException("Trùng thông tin sinh viên tại email, số điện thoại.\nHãy kiểm tra lại");
            }
            throw new CapNhatException("Có lỗi khi cập nhật sinh viên có mã số " + mssv);
        }
    }

    @Override
    public boolean xoaSinhVienTheoMaSo(Integer mssv) throws XoaException {
        String query = "DELETE FROM SinhVien WHERE mssv = " + mssv;
        PreparedStatement statement = access.getStatement(query);
        
        try {
            int rowAffected = statement.executeUpdate();
            if (rowAffected != 1) {
                throw new XoaException("Xóa không thành công do lỗi hệ thống!");
            }
        } catch (SQLException e) {
            throw new XoaException("Có lỗi khi thêm sinh viên có mã số " + mssv);
        }
        return true;
    }

    @Override
    public Integer tongSoSinhVien() throws DatabaseException {
        try {
            String query = "SELECT COUNT(mssv) FROM SinhVien";
            PreparedStatement statement = access.getStatement(query);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Lỗi khi lấy tổng số sinh viên!");
        }
        return 0;
    }

    private boolean contraintException(SQLException e) {
        return e.getSQLState().startsWith("23");
    }
}
