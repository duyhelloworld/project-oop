package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.DiemLopHP;
import huce.cntt.oop.doan.interfaces.IDiemLopService;

public class DiemLopHPService implements IDiemLopService {
    private DataAccess access = DataAccess.getInstance();

    public DiemLopHPService() {
    }

    private static DiemLopHPService service;

    public static DiemLopHPService getInstance() {
        if (service == null) {
            service = new DiemLopHPService();
        }
        return service;
    }

    @Override
    public boolean xoaDiemLopHP(Integer MSSV) throws SQLException {
        String query = "UPDATE diemsinhvien SET diem_Chuyen_Can = 0, diem_Giua_Ki = 0, diem_Cuoi_Ki = 0 WHERE " + 
                "MSSV = " + MSSV;
        PreparedStatement statement = access.getStatement(query);

        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1) {
            throw new SQLException("Lỗi dữ liệu!!!");
        }
        return true;
    }

    @Override
    public List<DiemLopHP> layDiemLopHPTheoTenMon(String tenMon, String tenLop, Integer hocKi) {
        List<DiemLopHP> kq = new ArrayList<DiemLopHP>();
        PreparedStatement statement = access.getStatement(
                "SELECT sv.mssv, sv.ho_ten, lmh.ten_lop_mon_hoc, dsv.diem_chuyen_can, dsv.diem_giua_ki, dsv.diem_cuoi_ki, lmh.ma_lop_mon_hoc, dsv.hoc_ki "
                        +
                        "FROM monhoc mh " +
                        "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
                        "INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc " +
                        "INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv " +
                        "INNER JOIN lopquanli lql ON lql.ma_lop_quan_li = sv.ma_lop_quan_li " +
                        "WHERE mh.ten_mon_hoc LIKE " + tenMon + " AND lmh.ten_lop_mon_hoc = " + tenLop
                        + " AND dsv.hoc_ki = " + hocKi);
        try {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                DiemLopHP diem = new DiemLopHP();
                diem.setMSSV(result.getInt("mssv"));
                diem.setHoTen(result.getString("ho_ten"));
                diem.setTenLopMonHoc(result.getString("ten_lop_mon_hoc"));
                diem.setDiemChuyenCan(result.getFloat("diem_chuyen_can"));
                diem.setDiemGiuaKi(result.getFloat("diem_giua_ki"));
                diem.setDiemCuoiKi(result.getFloat("diem_cuoi_ki"));
                diem.setMaLopHP(result.getInt("ma_lop_mon_hoc"));
                diem.setHocKi(result.getInt("hoc_ki"));
                kq.add(diem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public List<DiemLopHP> layDiemLopHPTheoMaMon(Integer maMon, String tenLop, Integer hocKi) {
        List<DiemLopHP> kq = new ArrayList<DiemLopHP>();
        PreparedStatement statement = access.getStatement(
                "SELECT sv.mssv, sv.ho_ten, lmh.ten_lop_mon_hoc, dsv.diem_chuyen_can, dsv.diem_giua_ki, dsv.diem_cuoi_ki, lmh.ma_lop_mon_hoc, dsv.hoc_ki "
                        +
                        "FROM monhoc mh " +
                        "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
                        "INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc " +
                        "INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv " +
                        "INNER JOIN lopquanli lql ON lql.ma_lop_quan_li = sv.ma_lop_quan_li " +
                        "WHERE mh.ten_mon_hoc = " + maMon + " AND lmh.ten_lop_mon_hoc = " + tenLop
                        + " AND dsv.hoc_ki = " + hocKi);
        try {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                DiemLopHP diem = new DiemLopHP();
                diem.setMSSV(result.getInt("mssv"));
                diem.setHoTen(result.getString("ho_ten"));
                diem.setTenLopMonHoc(result.getString("ten_lop_mon_hoc"));
                diem.setDiemChuyenCan(result.getFloat("diem_chuyen_can"));
                diem.setDiemGiuaKi(result.getFloat("diem_giua_ki"));
                diem.setDiemCuoiKi(result.getFloat("diem_cuoi_ki"));
                diem.setMaLopHP(result.getInt("ma_lop_mon_hoc"));
                diem.setHocKi(result.getInt("hoc_ki"));
                kq.add(diem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public List<DiemLopHP> layDanhSachLopHPTheoMon(String tenMon) {
        List<DiemLopHP> kq = new ArrayList<DiemLopHP>();
        PreparedStatement statement = access.getStatement(
                "SELECT lmh.ten_lop_mon_hoc " +
                        "FROM monhoc mh " +
                        "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
                        "WHERE mh.ten_mon_hoc = ?");
        try {
            statement.setString(1, "%" + tenMon + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DiemLopHP temp = new DiemLopHP();
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
        String query = "UPDATE diemsinhvien SET diem_Chuyen_Can = 0, diem_Giua_Ki = 0, diem_Cuoi_Ki = 0 WHERE ma_lop_mon_hoc = "
                + maLop + " AND MSSV = " + mssv;
        PreparedStatement statement = access.getStatement(query);

        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1) {
            throw new SQLException("Unimplemented method 'xoaDiemHP'");
        }
        return true;
    }

    @Override
    public List<DiemLopHP> layTatCaDiem() {
        List<DiemLopHP> kq = new ArrayList<DiemLopHP>();
        PreparedStatement statement = access.getStatement("SELECT " +
                "sv.mssv, sv.ho_ten, lmh.ten_lop_mon_hoc, dsv.diem_chuyen_can, dsv.diem_giua_ki, dsv.diem_cuoi_ki, lmh.ma_lop_mon_hoc, dsv.hoc_ki "
                +
                "FROM MonHoc mh " +
                "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
                "INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc " +
                "INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv ");
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DiemLopHP temp = new DiemLopHP();
                temp.setMSSV(result.getInt("mssv"));
                temp.setHoTen(result.getString("ho_ten"));
                temp.setTenLopMonHoc(result.getString("ten_lop_mon_hoc"));
                temp.setDiemChuyenCan(result.getFloat("diem_chuyen_can"));
                temp.setDiemGiuaKi(result.getFloat("diem_giua_ki"));
                temp.setDiemCuoiKi(result.getFloat("diem_cuoi_ki"));
                temp.setMaLopHP(result.getInt("ma_lop_mon_hoc"));
                temp.setHocKi(result.getInt("hoc_ki"));
                kq.add(temp);
            }
            // access.closeConnection(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public boolean capNhatDiemLopHP(Integer maLop, Integer mssv, Float diem1, Float diem2, Float diem3)
            throws SQLException {
        // System.out.println(maLop + " "+ mssv+" "+diem1+" "+diem2+" "+diem3);
        String sql = "UPDATE diemsinhvien SET diem_Chuyen_Can = " + diem1 + ", diem_Giua_Ki = " + diem2
                + ", diem_Cuoi_Ki = " + diem3 + " WHERE ma_lop_mon_hoc = " + maLop + " AND MSSV = " + mssv;

        PreparedStatement statement = access.getStatement(sql);

        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1) {
            throw new SQLException("Unimplemented method 'capNhatDiemHP'");
        }
        return true;
    }

    @Override
    public List<String> layDanhSachLopTheoMaMon(Integer maMon, Integer hocKi) {
        List<String> kq = new ArrayList<String>();
        PreparedStatement statement = access.getStatement(
                "SELECT lmh.ten_lop_mon_hoc " +
                        "FROM MonHoc mh " +
                        "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
                        "INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc " +
                        "INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv " +
                        "WHERE mh.ma_mon_hoc = " + maMon + " AND dsv.hoc_ki = "+ hocKi + " GROUP BY lmh.ten_lop_mon_hoc");
        try {
            // statement.setInt(1, maMon);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String tenLopMonHoc = result.getString("ten_lop_mon_hoc");
                kq.add(tenLopMonHoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(kq);
        return kq;
    }

    @Override
    public List<String> layDanhSachLopTheoTenMon(String tenMon, Integer hocKi) {
        List<String> kq = new ArrayList<String>();
        PreparedStatement statement = access.getStatement(
                "SELECT lmh.ten_lop_mon_hoc " +
                        "FROM MonHoc mh " +
                        "INNER JOIN lopmonhoc lmh ON lmh.ma_mon_hoc = mh.ma_mon_hoc " +
                        "INNER JOIN diemsinhvien dsv ON dsv.ma_lop_mon_hoc = lmh.ma_lop_mon_hoc " +
                        "INNER JOIN sinhvien sv ON sv.mssv = dsv.mssv " +
                        "WHERE mh.ten_mon_hoc LIKE " + tenMon + " AND = dsv.hoc_ki " + hocKi + " GROUP BY lmh.ten_lop_mon_hoc");
        try {
            // statement.setString(1, tenMon);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String tenLopMonHoc = result.getString("ten_lop_mon_hoc");
                kq.add(tenLopMonHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(kq);
        return kq;
    }

}