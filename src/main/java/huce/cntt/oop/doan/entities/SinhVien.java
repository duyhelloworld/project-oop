package huce.cntt.oop.doan.entities;

import java.sql.Date;
import java.time.LocalDate;

import huce.cntt.oop.doan.entities.parents.ConNguoi;

public class SinhVien extends ConNguoi {

    private LocalDate ngayVaoTruong;

    public SinhVien() {
    }

    public LocalDate getNgayVaoTruong() {
        return this.ngayVaoTruong;
    }

    public Date getNgayVaoTruongVoiKieuSQL(){
        return Date.valueOf(this.ngayVaoTruong);
    }

    public void setNgayVaoTruong(LocalDate ngayVaoTruong) {
        this.ngayVaoTruong = ngayVaoTruong;
    }

    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString() 
            + ", ngày vào trường = '" + getNgayVaoTruong() + "'";
    }
}
