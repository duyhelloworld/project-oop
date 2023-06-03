package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.interfaces.ILopService;

public class LopService implements ILopService {
    private LopService() {}
    private static LopService service;

    public static LopService getInstance(){
        if (service == null) {
           service = new LopService();
        }
        return service;
    }

    private final DataAccess access = DataAccess.getInstance();

    @Override
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa) {
        List<String> kq = new ArrayList<>();
        try {
            PreparedStatement statement = access.getStatement("SELECT " + 
            "ten_lop_quan_li FROM LopQuanLi " +
            "INNER JOIN khoa ON lopquanli.ma_khoa = khoa.ma_khoa " +
            "WHERE ten_khoa = ?");
            statement.setString(1, tenKhoa);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                kq.add(resultSet.getString("ten_lop_quan_li"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public void capNhatLopQuanLi(SinhVien SinhVien) throws Exception {
        PreparedStatement statement = access.getStatement("UPDATE SinhVien SET ma_lop_quan_li = " + SinhVien.getMaLopQuanLi() + " WHERE mssv = " + SinhVien.getMaSo());
        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1) {
            throw new SQLException("Có 1 số lỗi xảy ra với hệ thống. Hãy quay lại sau!");
        }
        access.closeConnection(statement);
    }

    @Override
    public void xoaSinhVienKhoiLopQuanLi(Integer mssv) throws Exception {
        PreparedStatement statement = access.getStatement("UPDATE SinhVien SET ma_lop_quan_li = NULL WHERE mssv = " + mssv);
        int rowAffected = statement.executeUpdate();
        if (rowAffected < 1) {
            throw new IllegalArgumentException("Không tồn tại sinh viên mang mã số này");
        } 
        access.closeConnection(statement);
    }

    @Override
    public int laySoLopMonHocDangHoc(Integer mssv) throws Exception {
        PreparedStatement statement = access.getStatement("SELECT COUNT(*) FROM diemsinhvien WHERE mssv = " + mssv);
        int count = 0;
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        access.closeConnection(statement);
        return count;
    }

    @Override
    public void xoaSinhVienKhoiLopMonHoc(Integer mssv) throws Exception {
        PreparedStatement statement = access.getStatement("DELETE FROM diemsinhvien WHERE mssv = " + mssv);
        int rowAffected = statement.executeUpdate();
        if (rowAffected < 1) {
            throw new SQLException("Không tồn tại sinh viên mang mã số này");
        }
        access.closeConnection(statement);
    }

    @Override
    public Integer checkKhoa(String tenLopQuanLi, String tenKhoa) {
        PreparedStatement statement = access.getStatement("SELECT " +
        "ma_lop_quan_li " +
        "FROM lopquanli " + 
        "INNER JOIN KHOA ON lopquanli.ma_khoa = khoa.ma_khoa " + 
        "WHERE ten_lop_quan_li = '" + tenLopQuanLi + "'' AND ten_khoa = '" + tenKhoa + "'");

        try {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }    
}
