package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;

public interface ISinhVienService {
    public List<SinhVien> layTatCaSinhVien();

    public SinhVien laySinhVienTheoMaSo(Integer maso);

    public List<SinhVien> timKiemSinhVienTheoTen(String ten);

    public void themMoiSinhVien(SinhVien sinhVien);

    public Boolean capNhatThongTinSinhVien(Integer mssv, SinhVien sinhVienTruyenVao);
}
