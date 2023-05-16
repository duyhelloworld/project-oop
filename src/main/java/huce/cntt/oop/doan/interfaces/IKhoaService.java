package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.Khoa;

public interface IKhoaService {
    public List<String> layTenTatCaKhoa();

    public List<Khoa> layTatCaCacKhoa();

    public String layKhoaTheoMa(Integer ma_khoa);
}
