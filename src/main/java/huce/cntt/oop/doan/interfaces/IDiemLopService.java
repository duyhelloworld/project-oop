package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.DiemLopHP;

public interface IDiemLopService {
    public List<DiemLopHP> layTatCaDiem();

    public List<DiemLopHP> layDiemLopHPTheoMaMon(Integer maMon);

    public List<DiemLopHP> layDanhSachLopHPTheoMon(String tenMon);
    
    public boolean xoaDiemLopHP(Integer MSSV) throws SQLException;

    public List<DiemLopHP> layDiemLopHPTheoTenMon(String tenMon, String tenLop);

    public List<DiemLopHP> capNhatDiemLopHP(Integer mssv, Float diem);

    public boolean xoa(Integer maLop, Integer mssv) throws SQLException;
}