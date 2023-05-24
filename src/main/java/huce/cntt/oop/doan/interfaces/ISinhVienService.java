package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.dto.DTOSinhVien;

public interface ISinhVienService {
    public List<DTOSinhVien> layTatCaSinhVien();

    public DTOSinhVien timKiemSinhVienTheoMaSo(Integer maso);

    public List<DTOSinhVien> timKiemSinhVienTheoTen(String ten);

    public DTOSinhVien timKiemSinhVienTheoEmail(String ten);

    public Integer themMoiSinhVien(SinhVien sinhVien) throws SQLException;

    public void xoaSinhVienTheoMaSo(List<Integer> mssvs)  throws SQLException, IllegalArgumentException;

    public void capNhatThongTinSinhVien(SinhVien sinhVien) throws SQLException, IllegalArgumentException;
}
