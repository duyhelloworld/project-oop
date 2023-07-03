package huce.cntt.oop.doan.interfaces;

import java.sql.SQLException;
import java.util.List;

import huce.cntt.oop.doan.entities.DiemLopHP;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.DatabaseException;

public interface IDiemLopService {
    public List<DiemLopHP> layTatCaDiem();

    public List<DiemLopHP> layDiemLopHPTheoMaMon(Integer maMon);

    public List<DiemLopHP> layDanhSachLopHPTheoMon(String tenMon);
    
    public boolean xoaDiemLopHP(Integer MSSV) throws SQLException;

    public List<DiemLopHP> layDiemLopHPTheoTenMon(String tenMon, String tenLop, Integer hocKi);

    public boolean capNhatDiemLopHP(Integer mssv, Integer maLopHP, Float diem1,Float diem2,Float diem3) throws SQLException;

    public boolean xoa(Integer maLop, Integer mssv) throws SQLException;
}