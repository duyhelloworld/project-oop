package huce.cntt.oop.doan.interfaces;

public interface IAdminService {
    public Boolean checkLogin(Integer maGV, String password);

    public Boolean checkAdmin(Integer maSo);
}
