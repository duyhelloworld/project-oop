package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.ThemException;
import huce.cntt.oop.doan.entities.exception.XoaException;

public interface ISinhVienService {
    public List<SinhVien> layTatCaSinhVien();

    public Integer tongSoSinhVien();

    public SinhVien timKiemSinhVienTheoMaSo(Integer maso);

    public List<SinhVien> timKiemSinhVienTheoTen(String ten);

    public SinhVien timKiemSinhVienTheoEmail(String ten);

    public Integer themMoiSinhVien(SinhVien sinhVien) throws ThemException;

    public boolean xoaSinhVienTheoMaSo(Integer mssvs)  throws XoaException;

    public void capNhatThongTinSinhVien(SinhVien sinhVien) throws CapNhatException;
}
