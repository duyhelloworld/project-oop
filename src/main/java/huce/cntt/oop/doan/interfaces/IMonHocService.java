package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.MonHoc;

public interface IMonHocService {
    public List<MonHoc> layTatCaMonHoc();

    public List<MonHoc> layMonHocTheoMaSo(Integer maso);

    public List<MonHoc> timKiemMonHocTheoTen(String ten);

    public List<MonHoc> timKiemMonHocTheoKhoa(String ten);
    
    public void capNhatThongTinMonHoc(Integer maMon,  MonHoc monHocTruyenVao);
    
    public boolean xoaMonHoc(Integer maMon) throws SQLException;
}
