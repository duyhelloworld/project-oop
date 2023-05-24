package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.dto.DTOSinhVien;
import huce.cntt.oop.doan.interfaces.ILopService;

public class LopService implements ILopService {

    private final DataAccess access = DataAccess.getInstance();

    @Override
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa) {
        List<String> kq = new ArrayList<>();
        try {
            PreparedStatement statement = access.getStatement("SELECT ten_lop_quan_li FROM LopQuanLi INNER JOIN khoa ON lopquanli.ma_khoa = khoa.ma_khoa WHERE khoa.ten_khoa = ?");
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
    public void themSinhVienVaoLopQuanLi(Integer mssv, Integer maLopQuanLi) throws SQLException {
        PreparedStatement statement = access.getStatement("INSERT INTO `lopquanli_sinhvien` (ma_lop_quanli, mssv) VALUES (?, ?)");
            statement.setInt(1, maLopQuanLi);
            statement.setInt(2, mssv);

            int soDongAnhHuong = statement.executeUpdate();
            if (soDongAnhHuong != 1) {
                throw new SQLException("Có lỗi trong lúc chèn");
            }
    }

    @Override
    public void capNhatLopQuanLi(DTOSinhVien dtoSinhVien) throws SQLException {
        PreparedStatement statement = access.getStatement("UPDATE Lopquanli_SinhVien SET ma_lop_quan_li = " + dtoSinhVien.getMaLopQuanLi() + " WHERE mssv = " + dtoSinhVien.getMaSo());
        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1) {
            throw new SQLException("Có 1 số lỗi xảy ra với hệ thống. Hãy quay lại sau!");
        }
        access.closeConnection(statement);
    }
}
