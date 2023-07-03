package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.KhoaLopException;
import huce.cntt.oop.doan.entities.exception.XoaException;


public interface ILopService {
    public List<String> layTenCacLopQuanLiTheoKhoa(String tenKhoa);

    public int laySoLopMonHocDangHoc(Integer mssv);
    
    public List<Integer> layCacLopMonHocPhuThuoc(Integer maMon);
    // trả về 1 list các mã lớp môn học
    public void xoaLopMonHocKhoiDiemSinhVien(Integer maLopMonHoc) throws XoaException;
    
    public void xoaMonHocKhoiLopMonHoc(Integer maMon) throws XoaException;
    
    public List<String> layTenCacLopMonHoc(Integer maMon);

    public void xoaSinhVienKhoiLopMonHoc(Integer mssv) throws XoaException;
    
    public Integer soHocSinhHocOLopMonHoc(Integer maLopMonHoc);

    public Integer checkKhoa(String tenLopQuanLi, String tenKhoa) throws KhoaLopException;

    public void capNhatLopQuanLi(SinhVien SinhVien) throws CapNhatException;
}
