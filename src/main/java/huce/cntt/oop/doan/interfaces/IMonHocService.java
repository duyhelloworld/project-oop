package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.XoaException;

public interface IMonHocService {
    public List<MonHoc> layTatCaMonHoc();

    public List<MonHoc> layMonHocTheoMaSo(Integer maso);

    public List<MonHoc> timKiemMonHocTheoTen(String ten);

    public List<MonHoc> timKiemMonHocTheoKhoa(String ten);
    
    public void capNhatThongTinMonHoc(MonHoc monHocTruyenVao) throws CapNhatException;
    
    public boolean xoaMonHoc(Integer maMon) throws XoaException;
}
