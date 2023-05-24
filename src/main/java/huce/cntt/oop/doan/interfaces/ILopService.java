package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.dto.DTOSinhVien;

public interface ILopService {
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa);

    public void themSinhVienVaoLopQuanLi(Integer mssv, Integer maLopQuanLi) throws SQLException;

    public void capNhatLopQuanLi(DTOSinhVien dtoSinhVien) throws SQLException, IllegalArgumentException;
}
