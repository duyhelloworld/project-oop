package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.MonHoc;

public interface IMonHocService {
    public List<MonHoc> layTatCaMonHoc();

    public MonHoc layMonHocTheoMaSo(Integer maso);

    public List<MonHoc> timKiemMonHocTheoTen(String ten);

    public void capNhatThongTinMonHoc(Integer maMon,  MonHoc monHocTruyenVao);

    public void xoaMonHoc(Integer maSo);
}
