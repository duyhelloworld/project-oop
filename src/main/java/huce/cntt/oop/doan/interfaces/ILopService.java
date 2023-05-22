package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ILopService {
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa);

    public void themSinhVienVaoLopQuanLi(Integer mssv, Integer maLopQuanLi) throws SQLException;
}
