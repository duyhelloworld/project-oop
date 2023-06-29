package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.DiemLopHocPhan;
import huce.cntt.oop.doan.interfaces.IDiemLopHocPhanService;

public class DiemLopHocPhanService implements IDiemLopHocPhanService{
    private DataAccess access = DataAccess.getInstance();

    public DiemLopHocPhanService(){}
    private static DiemLopHocPhanService service;
    public static DiemLopHocPhanService getInstance(){
        if (service == null){
            service = new DiemLopHocPhanService();
        }
        return service;
    }

    @Override
    public boolean xoaDiemLopHP(Integer MSSV) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'xoaDiemLopHP'");
    }

    @Override
    public List<DiemLopHocPhan> capNhatDiemLopHP(Integer mssv, Float diem) {
        
        throw new UnsupportedOperationException("Unimplemented method 'capNhatDiemLopHP'");
    }
    

    @Override
    public List<DiemLopHocPhan> layDiemLopHPTheoTenMon(String tenMon, String tenLop) {
        List<DiemLopHocPhan> kq = new ArrayList<DiemLopHocPhan>();
        PreparedStatement statement = access.getStatement(
            "SELECT sv.mssv, sv.ho_ten, lql.ten_lop_quan_li, dsv.diem_chuyen_can, dsv.diem_giua_ki, dsv.diem_cuoi_ki"+
            "FROM monhoc mh "+
            "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc "+
            "INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc "+
            "INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv "+
            "INNER JOIN lopquanli lql ON lql.ma_lop_quan_li = sv.ma_lop_quan_li "+
            "WHERE mh.ten_mon_hoc = ? AND lmh.ten_lop_mon_hoc = ?"
        );
        try {
            statement.setString(1, "%" + tenMon + "%");
            statement.setString(0, "%" + tenLop + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DiemLopHocPhan temp = new DiemLopHocPhan();
                temp.setMSSV(result.getInt("mssv"));
                temp.setHoTen(result.getString("ho_ten"));
                temp.setLopQL(result.getString("ten_lop_quan_li"));
                temp.setDiemChuyenCan(result.getFloat("diem_chuyen_can"));
                temp.setDiemGiuaKi(result.getFloat("diem_giua_ki"));
                temp.setDiemCuoiKi(result.getFloat("diem_cuoi_ki"));
                kq.add(temp);
            }
            // access.closeConnection(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public List<DiemLopHocPhan> layDiemLopHPTheoMaMon(Integer maMon) {
        throw new UnsupportedOperationException("Unimplemented method 'layDiemLopHPTheoMaMon'");
    }

    @Override
    public List<DiemLopHocPhan> layDanhSachLopHPTheoMon(String tenMon) {
        List<DiemLopHocPhan> kq = new ArrayList<DiemLopHocPhan>();
        PreparedStatement statement = access.getStatement(
            "SELECT lmh.ten_lop_mon_hoc " +
            "FROM monhoc mh " +
            "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
            "WHERE mh.ten_mon_hoc = ?"
        );
        try {
            statement.setString(1, "%" + tenMon + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DiemLopHocPhan temp = new DiemLopHocPhan();
                temp.setTenLopMonHoc(result.getString("ten_lop_mon_hoc"));
                kq.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public boolean xoa(Integer maLop, Integer mssv) throws SQLException {
        String query = "DELETE FROM diemsinhvien where ma_lop_mon_hoc = "+ maLop + " AND mssv = " + mssv;
        PreparedStatement statement = access.getStatement(query);
        
        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1){
            throw new SQLException("Unimplemented method 'xoaMonHoc'");
        }
        return true;
    }

    @Override
    public List<DiemLopHocPhan> layTatCaDiem() {
        List<DiemLopHocPhan> kq = new ArrayList<DiemLopHocPhan>();
        PreparedStatement statement = access.getStatement("SELECT " +
        "sv.mssv, sv.ho_ten, lql.ten_lop_quan_li, dsv.diem_chuyen_can, dsv.diem_giua_ki, dsv.diem_cuoi_ki " +
        "FROM MonHoc mh " +
        "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
        "INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc " +
        "INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv " +
        "INNER JOIN lopquanli lql ON lql.ma_lop_quan_li = sv.ma_lop_quan_li ");
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DiemLopHocPhan temp = new DiemLopHocPhan();
                temp.setMSSV(result.getInt("mssv"));
                temp.setHoTen(result.getString("ho_ten"));
                temp.setLopQL(result.getString("ten_lop_quan_li"));
                temp.setDiemChuyenCan(result.getFloat("diem_chuyen_can"));
                temp.setDiemGiuaKi(result.getFloat("diem_giua_ki"));
                temp.setDiemCuoiKi(result.getFloat("diem_cuoi_ki"));
                kq.add(temp);
            }
            // access.closeConnection(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
}