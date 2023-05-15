package huce.cntt.oop.doan.interfaces;

import java.util.List;

public interface ILopService {
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa);

    public void themSinhVienVaoLopQuanLi(Integer mssv, Integer maLopQuanLi);
}
