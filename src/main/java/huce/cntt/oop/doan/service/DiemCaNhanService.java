package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.DiemCaNhan;
import huce.cntt.oop.doan.interfaces.IDiemCaNhanService;

public class DiemCaNhanService implements IDiemCaNhanService {
    private DataAccess access = DataAccess.getInstance();
    private List<DiemCaNhan> listDiemCaNhan;

    private DiemCaNhanService() {}
    private static DiemCaNhanService service;
    public static DiemCaNhanService getInstance() {
        if (service == null) {
            service = new DiemCaNhanService();
        }
        return service;
    }

    @Override
    public List<DiemCaNhan> layTatCaDiemCaNhan() {
        String query = "SELECT " + 
        "monhoc.ma_mon_hoc, ten_mon_hoc, ten_lop_mon_hoc, so_tin_chi, diem_chuyen_can, diem_giua_ki, diem_cuoi_ki " +
        "FROM diemsinhvien " + 
        "INNER JOIN lopmonhoc ON LopMonHoc.ma_lop_mon_hoc = diemsinhvien.ma_lop_mon_hoc " +
        "INNER JOIN monhoc ON LopMonHoc.ma_mon_hoc = monhoc.ma_mon_hoc " + 
        "ORDER BY monhoc.ma_mon_hoc";
        PreparedStatement statement = access.getStatement(query);
        listDiemCaNhan = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DiemCaNhan diemCaNhan = new DiemCaNhan();
                diemCaNhan.setMaMon(result.getInt("ma_mon_hoc"));
                diemCaNhan.setTenMon(result.getString("ten_mon_hoc"));
                diemCaNhan.setTenLopMon(result.getString("ten_lop_mon_hoc"));
                diemCaNhan.setSoTinChi(result.getInt("so_tin_chi"));
                diemCaNhan.setDiemChuyenCan(result.getFloat("diem_chuyen_can"));
                diemCaNhan.setDiemGiuaKi(result.getFloat("diem_giua_ki"));
                diemCaNhan.setDiemCuoiKi(result.getFloat("diem_cuoi_ki"));
                listDiemCaNhan.add(diemCaNhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDiemCaNhan;
    }

    @Override
    public List<DiemCaNhan> layDiemCaNhanTheoMaSo(Integer maSo) {
        String query = "SELECT " + 
        "monhoc.ma_mon_hoc, ten_mon_hoc, ten_lop_mon_hoc, so_tin_chi, diem_chuyen_can, diem_giua_ki, diem_cuoi_ki " +
        "FROM diemsinhvien " + 
        "INNER JOIN lopmonhoc ON LopMonHoc.ma_lop_mon_hoc = diemsinhvien.ma_lop_mon_hoc " +
        "INNER JOIN monhoc ON LopMonHoc.ma_mon_hoc = monhoc.ma_mon_hoc " + 
        "WHERE diemsinhvien.mssv = " + maSo + " " +
        "ORDER BY monhoc.ma_mon_hoc";
        PreparedStatement statement = access.getStatement(query);
        listDiemCaNhan = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DiemCaNhan diemCaNhan = new DiemCaNhan();
                diemCaNhan.setMaMon(result.getInt("ma_mon_hoc"));
                diemCaNhan.setTenMon(result.getString("ten_mon_hoc"));
                diemCaNhan.setTenLopMon(result.getString("ten_lop_mon_hoc"));
                diemCaNhan.setSoTinChi(result.getInt("so_tin_chi"));
                diemCaNhan.setDiemChuyenCan(result.getFloat("diem_chuyen_can"));
                diemCaNhan.setDiemGiuaKi(result.getFloat("diem_giua_ki"));
                diemCaNhan.setDiemCuoiKi(result.getFloat("diem_cuoi_ki"));
                listDiemCaNhan.add(diemCaNhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDiemCaNhan;
    }
}
