package com.helloworld.serrvice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.helloworld.dataconnection.DataAccess;
import com.helloworld.entities.HocSinh;

public class QuanLiHocSinh {

    private DataAccess access = DataAccess.getInstance();

    private PreparedStatement statement = access.getStatement("");

    public List<HocSinh> lay_tat_ca_hoc_sinh() {
        access.changeQuery(statement, "SELECT * FROM HocSinh");
        try {
            ResultSet result = statement.executeQuery();
            HocSinh hs = new HocSinh();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
