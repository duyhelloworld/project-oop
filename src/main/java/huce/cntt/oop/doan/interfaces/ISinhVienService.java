package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;

public interface ISinhVienService {
    public List<SinhVien> layTatCaSinhVien();

    public SinhVien timKiemSinhVienTheoMaSo(Integer maso);

    public List<SinhVien> timKiemSinhVienTheoTen(String ten);

    public SinhVien timKiemSinhVienTheoEmail(String ten);

    public Integer themMoiSinhVien(SinhVien sinhVien);

    public void capNhatThongTinSinhVien(Integer mssv, SinhVien sinhVienTruyenVao);

    public void xoaSinhVienTheoMaSo(List<Integer> mssvs);
}
