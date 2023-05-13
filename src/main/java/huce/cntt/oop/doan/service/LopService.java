package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.interfaces.ILopService;

public class LopService implements ILopService {

    private final DataAccess access = DataAccess.getInstance();

    @Override
    public List<String> layTenTatCaLopQuanLiTheoKhoa(String tenKhoa) {
        PreparedStatement statement = access.getStatement("SELECT ten_lop_quanli FROM LopQuanLi INNER JOIN khoa ON lopquanli.ma_khoa = khoa.ma_khoa WHERE khoa.ten_khoa = ?");
        List<String> tenCacLopQuanLi = new ArrayList<String>();
        ResultSet resultSet;
        try {
            statement.setString(1, tenKhoa);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tenCacLopQuanLi.add(resultSet.getString("ten_lop_quanli"));
            }
            return tenCacLopQuanLi;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
