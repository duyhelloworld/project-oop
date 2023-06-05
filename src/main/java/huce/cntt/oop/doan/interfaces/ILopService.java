package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.KhoaLopException;
import huce.cntt.oop.doan.entities.exception.XoaException;


public interface ILopService {
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa);

    public void capNhatLopQuanLi(SinhVien SinhVien) throws CapNhatException;

    public int laySoLopMonHocDangHoc(Integer mssv);

    public void xoaSinhVienKhoiLopQuanLi(Integer mssv) throws XoaException;

    public void xoaSinhVienKhoiLopMonHoc(Integer mssv) throws XoaException;

    public Integer checkKhoa(String tenLopQuanLi, String tenKhoa) throws KhoaLopException;
}
