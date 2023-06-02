package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.interfaces.IMonHocService;

public class MonHocService implements IMonHocService {
    private DataAccess access = DataAccess.getInstance();

    public MonHocService() {}
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
            // access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public List<MonHoc> layMonHocTheoMaSo(Integer maso) {
        List<MonHoc> kq = new ArrayList<MonHoc>();
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
                kq.add(mh);
            }
            // access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
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
        } catch (Exception e) {
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
            // access.closeConnection(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;    }

    @Override
    public void capNhatThongTinMonHoc(Integer ma_mon,  MonHoc monHocTruyenVao) {
    //     MonHoc monHocLayRaTuDatabase = layMonHocTheoMaSo(ma_mon);

    //     try {
    //         if (monHocLayRaTuDatabase == null) {
    //             throw new IllegalArgumentException("Not found 'mon hoc'.ma_mon =  " + ma_mon);
    //         }

    //         PreparedStatement statement = access.getStatement(
    //                 "UPDATE MonHoc SET ten_mon_hoc = ?, so_tin_chi = ?, bat_buoc = ?, mon_tien_quyet = ?, mo_ta = ? WHERE ma_mon_hoc = ?");
    //         if (monHocTruyenVao.getTenMon().equals(monHocLayRaTuDatabase.getTenMon())) {
    //             statement.setString(1, monHocTruyenVao.getTenMon());
    //         } else {
    //             statement.setString(1, monHocLayRaTuDatabase.getTenMon());
    //         }

    //         if (monHocTruyenVao.getSoTinChi().equals(monHocLayRaTuDatabase.getSoTinChi())) {
    //             statement.setInt(2, monHocTruyenVao.getSoTinChi());
    //         } else {
    //             statement.setInt(2, monHocLayRaTuDatabase.getSoTinChi());
    //         }

    //         if (monHocTruyenVao.getBatBuoc().equals(monHocLayRaTuDatabase.getBatBuoc())) {
    //             statement.setBoolean(3, monHocTruyenVao.getBatBuoc());
    //         } else {
    //             statement.setBoolean(3, monHocLayRaTuDatabase.getBatBuoc());
    //         }
            
    //         if (monHocTruyenVao.getMonTienQuyet().equals(monHocLayRaTuDatabase.getMonTienQuyet())) {
    //             statement.setString(4, monHocTruyenVao.getMonTienQuyet());
    //         } else {
    //             statement.setString(4, monHocLayRaTuDatabase.getMonTienQuyet());
    //         }      
    //         if (monHocTruyenVao.getMoTa().equals(monHocLayRaTuDatabase.getMoTa())) {
    //             statement.setString(5, monHocTruyenVao.getMoTa());
    //         } else {
    //             statement.setString(5, monHocLayRaTuDatabase.getMoTa());
    //         }         
    //         statement.setInt(6, ma_mon);
    //         int rowAffected = statement.executeUpdate();
    //         if (rowAffected != 1) {
    //             throw new IllegalStateException("Some error happened when update 'monhoc' with id " + ma_mon);
    //         }

    //         // access.closeConnection(statement);
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    }

    @Override
    public boolean xoaMonHoc(Integer maMon) throws SQLException {
        String query = "DELETE FROM MonHoc where ma_mon_hoc = "+ maMon;
        PreparedStatement statement = access.getStatement(query);
        
        int rowAffected = statement.executeUpdate();
        if (rowAffected != 1){
            throw new SQLException("Unimplemented method 'xoaMonHoc'");
        }
        return true;
    }
    // @Override
    // public List<SinhVien> timSinhVienTheoLopQuanLi(Integer ma_lop_quan_li) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'timSinhVienTheoLopQuanLi'");
    // }


    // @Override
    // public List<SinhVien> timSinhVienTheoLopMonHoc(Integer ma_lop_mon_hoc) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'timSinhVienTheoLopMonHoc'");
    // }


    
    
    // public List<MonHoc> fetchDataFromDatabase() {
    //     List<MonHoc> monHocs = new ArrayList<>();

    //     try {
    //         // Tạo câu truy vấn
    //         String query = "SELECT mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi, mh.bat_buoc, mh.mon_tien_quyet, k.ten_khoa, mh.mo_ta " +
    //                 "FROM monhoc mh " +
    //                 "INNER JOIN Khoa_MonHoc kmh ON kmh.ma_mon_hoc = mh.ma_mon_hoc "+
    //                 "INNER JOIN Khoa k ON k.ma_khoa = kmh.ma_khoa";

    //         // Tạo statement và thực thi truy vấn
    //         PreparedStatement statement = access.getStatement(query);
    //         ResultSet resultSet = statement.executeQuery();

    //         // Lặp qua các dòng trong ResultSet
    //         while (resultSet.next()) {
    //             MonHoc monHoc = new MonHoc();  
    //             monHoc.setMaMon(resultSet.getInt("ma_mon_hoc"));   
    //             monHoc.setTenMonHoc(resultSet.getString("ten_mon_hoc"));
    //             monHoc.setMonBatBuoc(resultSet.getBoolean("mon_bat_buoc"));   
    //             monHoc.setMonTienQuyet(resultSet.getInt("mon_tien_quyet"));   
    //             monHoc.setSoTinChi(resultSet.getInt("so_tin_chi"));    
    //             monHoc.setTenKhoa(resultSet.getString("ten_khoa"));
    //             monHoc.setMoTa(resultSet.getString("mo_ta"));

    //             monHocs.add(monHoc);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return monHocs;
    // }
}
