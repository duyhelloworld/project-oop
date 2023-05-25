package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;

public interface ISinhVienService {
    public List<SinhVien> layTatCaSinhVien();

    public SinhVien timKiemSinhVienTheoMaSo(Integer maso);

    public List<SinhVien> timKiemSinhVienTheoTen(String ten);

    public SinhVien timKiemSinhVienTheoEmail(String ten);

    public Integer themMoiSinhVien(SinhVien sinhVien) throws SQLException;

    public void xoaSinhVienTheoMaSo(List<Integer> mssvs)  throws SQLException, IllegalArgumentException;

    public void capNhatThongTinSinhVien(SinhVien sinhVien) throws SQLException, IllegalArgumentException;
}
