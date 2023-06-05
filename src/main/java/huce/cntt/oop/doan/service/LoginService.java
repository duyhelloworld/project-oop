package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.exception.ChuyenHoTenException;
import huce.cntt.oop.doan.entities.exception.ChuyenSoException;
import huce.cntt.oop.doan.entities.exception.EmailException;
import huce.cntt.oop.doan.entities.properties.HoTen;
import huce.cntt.oop.doan.interfaces.ILoginService;

public class LoginService implements ILoginService {

    private DataAccess access = DataAccess.getInstance();

    private LoginService() {}
    private static LoginService instance;

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    @Override
    public GiangVien checkLogin(Integer maGV, String password) {
        PreparedStatement statement = access.getStatement("SELECT ma_gv, ho_ten, email, so_dien_thoai FROM GiangVien WHERE ma_gv = " + maGV + " AND mat_khau = ?");
        try {
            GiangVien giangVien = new GiangVien();
            statement.setString(1, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                giangVien.setMaSo(result.getInt("ma_gv"));
                giangVien.setHoTen(new HoTen(result.getString("ho_ten")));
                giangVien.setEmail(result.getString("email"));
                giangVien.setSoDienThoai(result.getString("so_dien_thoai"));
                return giangVien;
            }
        } catch (EmailException | ChuyenSoException | ChuyenHoTenException |SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean checkAdmin(Integer maSo) {
        PreparedStatement statement = access.getStatement("SELECT MIN(ma_gv) FROM GiangVien");
        try {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == maSo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
