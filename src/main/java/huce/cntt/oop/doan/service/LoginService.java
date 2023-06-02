package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import huce.cntt.oop.doan.dataconnection.DataAccess;
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
    public Boolean checkLogin(Integer maGV, String password) {
        PreparedStatement statement = access.getStatement("SELECT ma_gv, mat_khau FROM GiangVien WHERE ma_gv = " + maGV + " AND mat_khau = ?");
        try {
            statement.setString(1, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
