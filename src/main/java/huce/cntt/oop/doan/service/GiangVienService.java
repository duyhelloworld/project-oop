package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.interfaces.IAdminService;

public class GiangVienService implements IAdminService {

    private DataAccess access = DataAccess.getInstance();

    private GiangVienService() {}
    private static GiangVienService instance;

    public static GiangVienService getInstance() {
        if (instance == null) {
            instance = new GiangVienService();
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
        } catch (Exception e) {
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
                return rs.getInt(0) == maSo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
