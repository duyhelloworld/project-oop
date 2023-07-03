package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.Khoa;
import huce.cntt.oop.doan.entities.exception.XoaException;
import huce.cntt.oop.doan.interfaces.IKhoaService;

public class KhoaService implements IKhoaService {
    private KhoaService() {}
    private static KhoaService khoaService;

    public static KhoaService getInstance() {
        if (khoaService == null) {
            khoaService = new KhoaService();
        }
        return khoaService;
    }
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

    @Override
    public List<Khoa> layTatCaCacKhoa() {
        PreparedStatement statement = access.getStatement("SELECT * FROM khoa");
        List<Khoa> khoas = new ArrayList<Khoa>();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery();
            Khoa k = new Khoa();
            while (resultSet.next()) {
                k.setMaKhoa(resultSet.getInt("ma_khoa"));
                k.setTenKhoa(resultSet.getString("ten_khoa"));
                khoas.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khoas;
    }

    @Override
    public String layKhoaTheoMa(Integer ma_khoa) {
        PreparedStatement statement = access.getStatement("SELECT ten_khoa FROM khoa WHERE ma_khoa = ?");
        ResultSet resultSet;
        String kq = "";
        try {
            statement.setInt(1, ma_khoa);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                kq = resultSet.getString("ten_khoa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public void xoaMonKhoa(int maMon) throws XoaException {
        PreparedStatement statement = access.getStatement("DELETE FROM khoa_monhoc WHERE ma_mon_hoc = " + maMon);
        try {
            int rowAffected = statement.executeUpdate();
            if (rowAffected == 0) {
                throw new XoaException("Lỗi khi xóa môn học có mã + " + maMon + " khỏi khoa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new XoaException("Lỗi khi xóa môn học có mã + " + maMon + " khỏi khoa!");
        }
    }
}
