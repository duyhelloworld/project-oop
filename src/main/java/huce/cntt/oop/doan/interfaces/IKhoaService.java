package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.Khoa;
import huce.cntt.oop.doan.entities.exception.XoaException;

public interface IKhoaService {
    public List<String> layTenTatCaKhoa();

    public List<Khoa> layTatCaCacKhoa();

    public String layKhoaTheoMa(Integer maKhoa);

    public void xoaMonKhoa(int maMon) throws XoaException;
}
