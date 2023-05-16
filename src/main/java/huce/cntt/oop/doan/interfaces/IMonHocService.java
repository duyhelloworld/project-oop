package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.entities.SinhVien;

public interface IMonHocService {
    public List<MonHoc> layTatCaMonHoc();

    public MonHoc layMonHocTheoMaSo(Integer maso);

    public List<SinhVien> timSinhVienTheoLopQuanLi(Integer ma_lop_quan_li);

    public List<SinhVien> timSinhVienTheoLopMonHoc(Integer ma_lop_mon_hoc);

    public List<MonHoc> timKiemMonHocTheoTen(String ten);

    public void capNhatThongTinMonHoc(Integer maMon,  MonHoc monHocTruyenVao);
}
