package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huce.cntt.oop.doan.dataconnection.DataAccess;

public class QuanLiSinhVienTheoLop {
    private DataAccess access = DataAccess.getInstance();

    public List<Map<String, String>> laySinhVienTheoLopQuanLi(String tenLop) {
        String query = "SELECT SinhVien.mssv, ho_ten, SinhVien.ten_lop AS lop_quan_li, TinhDiem.ten_lop AS lop_mon_hoc, MonHoc.ten AS ten_mon_hoc, khoa, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki"
                + " FROM (`SinhVien` INNER JOIN `LopQuanLi` ON SinhVien.ten_lop = LopQuanLi.ten_lop)"
                + " INNER JOIN (`MonHoc` INNER JOIN `LopMonHoc` On LopMonHoc.ma_mon = MonHoc.ma_mon)"
                + " INNER JOIN `TinhDiem` On TinhDiem.mssv = SinhVien.mssv"
                + " WHERE SinhVien.ten_lop = ?"
                + " ORDER BY SinhVien.mssv";
        List<Map<String, String>> finalTable = new ArrayList<Map<String, String>>();

        try {
            PreparedStatement statement = access.getStatement(query);
            statement.setString(1, tenLop);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("mssv", Integer.toString(resultSet.getInt("mssv")));
                row.put("ho_ten", resultSet.getString("ho_ten"));
                row.put("lop_quan_li", resultSet.getString("lop_quan_li"));
                row.put("lop_mon_hoc", resultSet.getString("lop_mon_hoc"));
                row.put("ten_mon_hoc", resultSet.getString("ten_mon_hoc"));
                row.put("khoa", resultSet.getString("khoa"));
                row.put("so_buoi_diem_danh", Integer.toString(resultSet.getInt("so_buoi_diem_danh")));
                row.put("diem_giua_ki", Float.toString(resultSet.getFloat("diem_giua_ki")));
                row.put("diem_cuoi_ki", Float.toString(resultSet.getFloat("diem_cuoi_ki")));

                finalTable.add(row);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalTable;
    }

    public List<Map<String, String>> laySinhVienTheoLopMonHoc(String tenLop) {
        String query = "SELECT SinhVien.mssv, ho_ten, SinhVien.ten_lop AS lop_quan_li, LopMonHoc.ten_lop AS lop_mon_hoc, LopQuanLi.khoa, so_buoi_diem_danh, diem_giua_ki, diem_cuoi_ki"
                + " FROM ((`SinhVien`  INNER JOIN "
                        + "(`TinhDiem` INNER JOIN `LopMonHoc` ON TinhDiem.ten_lop = LopMonHoc.ten_lop)"
                        + " ON SinhVien.mssv = TinhDiem.mssv)"
                    + ") INNER JOIN `LopQuanLi` ON `LopQuanLi`.ten_lop = SinhVien.ten_lop"
                + " WHERE LopMonHoc.ten_lop = ?"
                + " ORDER BY SinhVien.mssv;";
        List<Map<String, String>> finalTable = new ArrayList<Map<String, String>>();

        try {
            PreparedStatement statement = access.getStatement(query);
            statement.setString(1, tenLop);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("mssv", Integer.toString(resultSet.getInt("mssv")));
                row.put("ho_ten", resultSet.getString("ho_ten"));
                row.put("lop_quan_li", resultSet.getString("lop_quan_li"));
                row.put("lop_mon_hoc", resultSet.getString("lop_mon_hoc"));
                row.put("khoa", resultSet.getString("khoa"));
                row.put("so_buoi_diem_danh", Integer.toString(resultSet.getInt("so_buoi_diem_danh")));
                row.put("diem_giua_ki", Float.toString(resultSet.getFloat("diem_giua_ki")));
                row.put("diem_cuoi_ki", Float.toString(resultSet.getFloat("diem_cuoi_ki")));

                finalTable.add(row);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalTable;
    }
}
