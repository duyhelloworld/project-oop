package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.DatabaseException;
import huce.cntt.oop.doan.entities.exception.ThemException;
import huce.cntt.oop.doan.entities.exception.XoaException;

public interface ISinhVienService {
    public List<SinhVien> layTatCaSinhVien() throws DatabaseException;

    public Integer tongSoSinhVien() throws DatabaseException;

    public SinhVien timKiemSinhVienTheoMaSo(Integer maso) throws DatabaseException;

    public List<SinhVien> timKiemSinhVienTheoTen(String ten) throws DatabaseException;

    public SinhVien timKiemSinhVienTheoEmail(String ten) throws DatabaseException;

    public Integer themMoiSinhVien(SinhVien sinhVien) throws ThemException;

    public boolean xoaSinhVienTheoMaSo(Integer mssvs)  throws XoaException;

    public void capNhatThongTinSinhVien(SinhVien sinhVien) throws CapNhatException, DatabaseException;
}
