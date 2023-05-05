package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.MonHoc;

public class MonHocCRUD {
    private DataAccess access = DataAccess.getInstance();

    public List<MonHoc> layTatCaMonHoc() {
        PreparedStatement statement = access.getStatement("SELECT * FROM MonHoc");
        List<MonHoc> kq = new ArrayList<MonHoc>();

        try {
            ResultSet result = statement.executeQuery();
            MonHoc temp = new MonHoc();
            while (result.next()) {
                temp.setMaMonHoc(result.getInt("ma_mon"));
                temp.setTenMonHoc(result.getString("ten"));
                temp.setSoTinChi(result.getInt("so_tin_chi"));
                temp.setSoBuoi(result.getInt("so_buoi"));
                temp.setLaBatBuoc(result.getBoolean("bat_buoc"));
                temp.setLaTienQuyet(result.getBoolean("tien+quyet"));

                kq.add(temp);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public MonHoc layMonHocTheoMaSo(Integer maso) {
        MonHoc kq = new MonHoc();
        PreparedStatement statement = access.getStatement("SELECT * FROM MonHoc WHERE ma_mon = ?");

        try {
            statement.setInt(1, maso);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                kq.setMaMonHoc(maso);
                kq.setTenMonHoc(result.getString("ten"));
                kq.setSoTinChi(result.getInt("so_tin_chi"));
                kq.setSoBuoi(result.getInt("so_buoi"));
                kq.setLaBatBuoc(result.getBoolean("bat_buoc"));
                kq.setLaTienQuyet(result.getBoolean("tien+quyet"));
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public List<MonHoc> timKiemMonHocTheoTen(String ten) {
        List<MonHoc> kq = new ArrayList<MonHoc>();
        PreparedStatement statement = access.getStatement("SELECT * FROM MonHoc WHERE ten LIKE ?");

        try {
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            MonHoc temp = new MonHoc();
            while (result.next()) {
                temp.setMaMonHoc(result.getInt("ma_mon"));
                temp.setTenMonHoc(result.getString("ten"));
                temp.setSoTinChi(result.getInt("so_tin_chi"));
                temp.setSoBuoi(result.getInt("so_buoi"));
                temp.setLaBatBuoc(result.getBoolean("bat_buoc"));
                temp.setLaTienQuyet(result.getBoolean("tien+quyet"));
                kq.add(temp);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public Boolean capNhatThongTinMonHoc(Integer ma_mon, MonHoc monHocTruyenVao) {
        MonHoc monHocLayRaTuDatabase = layMonHocTheoMaSo(ma_mon);

        try {
            if (monHocLayRaTuDatabase == null) {
                throw new IllegalArgumentException("Not found 'mon hoc'.ma_mon =  " + ma_mon);
            }

            PreparedStatement statement = access.getStatement(
                    "UPDATE MonHoc SET ten = ?, so_tin_chi = ?, so_buoi = ?, bat_buoc = ?, tien_quyet = ? WHERE ma_mon = ?");
            if (monHocTruyenVao.getTenMonHoc() != null) {
                statement.setString(1, monHocTruyenVao.getTenMonHoc());
            } else {
                statement.setString(1, monHocLayRaTuDatabase.getTenMonHoc());
            }

            if (monHocTruyenVao.getSoTinChi() != null) {
                statement.setInt(2, monHocTruyenVao.getSoTinChi());
            } else {
                statement.setInt(2, monHocLayRaTuDatabase.getSoTinChi());
            }
            
            if (monHocTruyenVao.getSoBuoi() != null) {
                statement.setInt(3, monHocTruyenVao.getSoBuoi());
            } else {
                statement.setInt(3, monHocLayRaTuDatabase.getSoBuoi());
            }

            if (monHocTruyenVao.getLaBatBuoc() != null) {
                statement.setBoolean(4, monHocTruyenVao.getLaBatBuoc());
            } else {
                statement.setBoolean(4, monHocLayRaTuDatabase.getLaBatBuoc());
            }
            
            if (monHocTruyenVao.getLaTienQuyet() != null) {
                statement.setBoolean(5, monHocTruyenVao.getLaTienQuyet());
            } else {
                statement.setBoolean(5, monHocLayRaTuDatabase.getLaTienQuyet());
            }            
            statement.setInt(6, ma_mon);
            int rowAffected = statement.executeUpdate();
            access.closeConnection(statement);

            return rowAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
