package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.DiemLopHocPhan;

public interface IDiemLopService {
    public List<DiemLopHocPhan> layTatCaDiem();

    public List<DiemLopHocPhan> layDiemLopHPTheoMaMon(Integer maMon);

    public List<DiemLopHocPhan> layDanhSachLopHPTheoMon(String tenMon);
    
    public boolean xoaDiemLopHP(Integer MSSV) throws SQLException;

    public List<DiemLopHocPhan> layDiemLopHPTheoTenMon(String tenMon, String tenLop);

    public List<DiemLopHocPhan> capNhatDiemLopHP(Integer mssv, Float diem);

    public boolean xoa(Integer maLop, Integer mssv) throws SQLException;
}