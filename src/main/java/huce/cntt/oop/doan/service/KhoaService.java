package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;

public class KhoaService {
    private final DataAccess access = DataAccess.getInstance();
    
    public List<String> layTenTatCaKhoa() {
        PreparedStatement statement = access.getStatement("SELECT ten_khoa FROM khoa");
        List<String> tenCacKhoa = new ArrayList<String>();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tenCacKhoa.add(resultSet.getString("ten_khoa"));
            }
            return tenCacKhoa;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
