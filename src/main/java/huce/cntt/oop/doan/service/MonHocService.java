package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.XoaException;
import huce.cntt.oop.doan.interfaces.IMonHocService;

public class MonHocService implements IMonHocService {
    private DataAccess access = DataAccess.getInstance();

    private MonHocService() {}
    private static MonHocService service;

    public static MonHocService getInstance(){
        if (service == null) {
           service = new MonHocService();
        }
        return service;
    }

    @Override
    public List<MonHoc> layTatCaMonHoc() {
        PreparedStatement statement = access.getStatement(
            "SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta " +
                    "FROM monhoc mh " +
                    "INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc "+
                    "INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa"
        );
        List<MonHoc> kq = new ArrayList<MonHoc>();
        MonHoc temp = null;

        try (ResultSet result = statement.executeQuery()){
            while (result.next()) {
                temp = new MonHoc();
                temp.setMaMon(result.getInt("ma_mon_hoc"));
                temp.setTenMon(result.getString("ten_mon_hoc"));
                temp.setSoTinChi(result.getInt("so_tin_chi"));
                temp.setBatBuoc(result.getBoolean("bat_buoc"));
                temp.setMonTienQuyet(result.getString("mon_tien_quyet"));
                temp.setKhoa(result.getString("ten_khoa"));
                temp.setMoTa(result.getString("mo_ta"));
                kq.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public MonHoc layMonHocTheoMaSo(Integer maso) {
        PreparedStatement statement = access.getStatement(
            "SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta " +
                    "FROM monhoc mh " +
                    "INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc "+
                    "INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa " +
                    "WHERE mh.ma_mon_hoc = ?"
        );
        try {
            statement.setInt(1, maso);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MonHoc mh = new MonHoc();
                mh.setMaMon(maso);
                mh.setTenMon(result.getString("ten_mon_hoc"));
                mh.setSoTinChi(result.getInt("so_tin_chi"));
                mh.setBatBuoc((result.getBoolean("bat_buoc")));
                mh.setMonTienQuyet(result.getString("mon_tien_quyet"));
                mh.setKhoa(result.getString("ten_khoa"));
                mh.setMoTa(result.getString("mo_ta"));
                return mh;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MonHoc> timKiemMonHocTheoTen(String ten) {
        List<MonHoc> kq = new ArrayList<MonHoc>();
        PreparedStatement statement = access.getStatement(
            "SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta " +
                    "FROM monhoc mh " +
                    "INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc "+
                    "INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa " +
                    "WHERE mh.ten_mon_hoc LIKE ?");
        try {
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MonHoc temp = new MonHoc();
                temp.setMaMon(result.getInt("ma_mon_hoc"));
                temp.setTenMon(result.getString("ten_mon_hoc"));
                temp.setSoTinChi(result.getInt("so_tin_chi"));
                temp.setBatBuoc(result.getBoolean("bat_buoc"));
                temp.setMonTienQuyet(result.getString("mon_tien_quyet"));
                temp.setKhoa(result.getString("ten_khoa"));
                temp.setMoTa(result.getString("mo_ta"));
                kq.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
    @Override
    public List<MonHoc> timKiemMonHocTheoKhoa(String ten) {
        List<MonHoc> kq = new ArrayList<MonHoc>();
        PreparedStatement statement = access.getStatement(
            "SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta " +
                    "FROM monhoc mh " +
                    "INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc "+
                    "INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa " +
                    "WHERE k.ten_khoa LIKE ?"
        );
        try {
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MonHoc temp = new MonHoc();
                temp.setMaMon(result.getInt("ma_mon_hoc"));
                temp.setTenMon(result.getString("ten_mon_hoc"));
                temp.setSoTinChi(result.getInt("so_tin_chi"));
                temp.setBatBuoc(result.getBoolean("bat_buoc"));
                temp.setMonTienQuyet(result.getString("mon_tien_quyet"));
                temp.setKhoa(result.getString("ten_khoa"));
                temp.setMoTa(result.getString("mo_ta"));
                kq.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;    }

    @Override
    public void capNhatThongTinMonHoc(MonHoc monHoc) throws CapNhatException {
        int maMon = monHoc.getMaMon();
        MonHoc monHocTrongDb = layMonHocTheoMaSo(maMon);
    
        if (monHocTrongDb == null) {
            throw new CapNhatException("Lỗi : Không tìm thấy môn học với mã " + maMon);
        }

        try {
            PreparedStatement statement = access.getStatement("UPDATE MonHoc SET ten_mon_hoc = ?, " +
            "so_tin_chi = ?, bat_buoc = ?, mon_tien_quyet = ?, mo_ta = ? " + 
            "WHERE ma_mon_hoc = ?");
            statement.setString(1, monHoc.getTenMon());
            statement.setInt(2, monHoc.getSoTinChi());
            statement.setBoolean(3,  monHoc.getBatBuoc());
            statement.setString(4, monHoc.getMonTienQuyet());
            statement.setString(5, monHoc.getMoTa());
            statement.setInt(6, monHoc.getMaMon());

            int rowAffected = statement.executeUpdate();
            if (rowAffected != 1) {
                throw new CapNhatException("Cập nhật không thành công do lỗi hệ thống!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (contraintException(e)) {
                throw new CapNhatException("Môn này đã tồn tại ");
            }
            throw new CapNhatException("Có lỗi khi cập nhật môn học có mã số " + maMon);
        }
    }

    @Override
    public boolean xoaMonHoc(Integer maMon) throws XoaException {
        String query = "DELETE FROM MonHoc where ma_mon_hoc = "+ maMon;
       try {
            PreparedStatement statement = access.getStatement(query);
            
            int rowAffected = statement.executeUpdate();
            if (rowAffected != 1){
                throw new XoaException("Xóa môn học không thành công do lỗi hệ thống!");
            }
       } catch (SQLException e) {
            e.printStackTrace();
            throw new XoaException("Có lỗi khi xóa môn học có mã số " + maMon);
       }
        return true;
    }
    
    private boolean contraintException(SQLException e) {
        return e.getSQLState().startsWith("23");
    }
}
