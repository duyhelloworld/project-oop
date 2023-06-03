package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;


public interface ILopService {
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa);

    public void capNhatLopQuanLi(SinhVien SinhVien) throws Exception;

    public int laySoLopMonHocDangHoc(Integer mssv) throws Exception;

    public void xoaSinhVienKhoiLopQuanLi(Integer mssv) throws Exception;

    public void xoaSinhVienKhoiLopMonHoc(Integer mssv) throws Exception;

    public Integer checkKhoa(String tenLopQuanLi, String tenKhoa);
}
