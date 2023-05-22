package huce.cntt.oop.doan.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.dataconnection.DataAccess;
import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.interfaces.IMonHocService;

public class MonHocService implements IMonHocService {
    private DataAccess access = DataAccess.getInstance();

    @Override
    public List<MonHoc> layTatCaMonHoc() {
        PreparedStatement statement = access.getStatement(
            "SELECT ma_mon_hoc, ten_mon_hoc, so_tin_chi, bat_buoc, ma_mon_tien_quyet, khoa.ma_khoa, mo_ta, ten_khoa FROM MonHoc INNER JOIN khoa ON khoa.ma_khoa = monhoc.ma_khoa");
        List<MonHoc> kq = new ArrayList<MonHoc>();

        try {
            ResultSet result = statement.executeQuery();
            MonHoc temp = new MonHoc();
            while (result.next()) {
                temp.setMaMonHoc(result.getInt("ma_mon_hoc"));
                temp.setTenMonHoc(result.getString("ten_mon_hoc"));
                temp.setSoTinChi(result.getInt("so_tin_chi"));
                temp.setMonBatBuoc(result.getBoolean("bat_buoc"));
                temp.setTenKhoa(result.getString("ten_khoa"));
                temp.setMonTienQuyet(result.getInt("ma_mon_tien_quyet"));
                kq.add(temp);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public MonHoc layMonHocTheoMaSo(Integer maso) {
        MonHoc mh = new MonHoc();
        PreparedStatement statement = access.getStatement("SELECT * FROM MonHoc WHERE ma_monhoc = ?");

        try {
            statement.setInt(1, maso);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                mh.setMaMonHoc(maso);
                mh.setTenMonHoc(result.getString("ten_monhoc"));
                mh.setSoTinChi(result.getInt("so_tin_chi"));
                mh.setMonBatBuoc((result.getBoolean("bat_buoc")));
                mh.setTenKhoa(result.getString("ten_khoa"));
                mh.setMoTa(result.getString("mo_ta"));
                mh.setMonTienQuyet(result.getInt("ma_mon_tien_quyet"));
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mh;
    }

    @Override
    public List<MonHoc> timKiemMonHocTheoTen(String ten) {
        List<MonHoc> kq = new ArrayList<MonHoc>();
        PreparedStatement statement = access.getStatement("SELECT * FROM MonHoc WHERE ten LIKE ?");

        try {
            statement.setString(1, "%" + ten + "%");
            ResultSet result = statement.executeQuery();
            MonHoc temp = new MonHoc();
            while (result.next()) {
                temp.setMaMonHoc(result.getInt("ma_monhoc"));
                temp.setTenMonHoc(result.getString("ten_monhoc"));
                temp.setSoTinChi(result.getInt("so_tin_chi"));
                temp.setMonBatBuoc(result.getBoolean("bat_buoc"));
                temp.setTenKhoa(result.getString("ten_khoa"));
                temp.setMonTienQuyet(result.getInt("ma_mon_tien_quyet"));
                kq.add(temp);
            }
            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public void capNhatThongTinMonHoc(Integer ma_mon,  MonHoc monHocTruyenVao) {
        MonHoc monHocLayRaTuDatabase = layMonHocTheoMaSo(ma_mon);

        try {
            if (monHocLayRaTuDatabase == null) {
                throw new IllegalArgumentException("Not found 'mon hoc'.ma_mon =  " + ma_mon);
            }

            PreparedStatement statement = access.getStatement(
                    "UPDATE MonHoc SET ten = ?, so_tin_chi = ?, so_buoi = ?, bat_buoc = ?, tien_quyet = ? WHERE ma_mon = ?");
            if (monHocTruyenVao.getTenMonHoc().equals(monHocLayRaTuDatabase.getTenMonHoc())) {
                statement.setString(1, monHocTruyenVao.getTenMonHoc());
            } else {
                statement.setString(1, monHocLayRaTuDatabase.getTenMonHoc());
            }

            if (monHocTruyenVao.getSoTinChi().equals(monHocLayRaTuDatabase.getSoTinChi())) {
                statement.setInt(2, monHocTruyenVao.getSoTinChi());
            } else {
                statement.setInt(2, monHocLayRaTuDatabase.getSoTinChi());
            }

            if (monHocTruyenVao.getMonBatBuoc().equals(monHocLayRaTuDatabase.getMonBatBuoc())) {
                statement.setBoolean(4, monHocTruyenVao.getMonBatBuoc());
            } else {
                statement.setBoolean(4, monHocLayRaTuDatabase.getMonBatBuoc());
            }
            
            if (monHocTruyenVao.getMaMonTienQuyet().equals(monHocLayRaTuDatabase.getMaMonTienQuyet())) {
                statement.setInt(5, monHocTruyenVao.getMaMonTienQuyet());
            } else {
                statement.setInt(5, monHocLayRaTuDatabase.getMaMonTienQuyet());
            }            
            statement.setInt(6, ma_mon);
            int rowAffected = statement.executeUpdate();
            if (rowAffected != 1) {
                throw new IllegalStateException("Some error happened when update 'monhoc' with id " + ma_mon);
            }

            access.closeConnection(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SinhVien> timSinhVienTheoLopQuanLi(Integer ma_lop_quan_li) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timSinhVienTheoLopQuanLi'");
    }

    @Override
    public List<SinhVien> timSinhVienTheoLopMonHoc(Integer ma_lop_mon_hoc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timSinhVienTheoLopMonHoc'");
    }
}
