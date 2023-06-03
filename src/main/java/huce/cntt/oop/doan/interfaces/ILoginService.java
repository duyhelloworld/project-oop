package huce.cntt.oop.doan.interfaces;

import huce.cntt.oop.doan.entities.GiangVien;

public interface ILoginService {
    public GiangVien checkLogin(Integer maGV, String password);

    public Boolean checkAdmin(Integer maSo);
}
