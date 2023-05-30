package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;


public interface ILopService {
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa);

    public void themSinhVienVaoLopQuanLi(Integer mssv, Integer maLopQuanLi) throws SQLException;

    public void capNhatLopQuanLi(SinhVien SinhVien) throws SQLException, IllegalArgumentException;

    public int laySoLopMonHocDangHoc(Integer mssv) throws SQLException;

    public void xoaSinhVienKhoiLopQuanLi(Integer mssv) throws SQLException;

    public void xoaSinhVienKhoiLopMonHoc(Integer mssv) throws SQLException;
}
