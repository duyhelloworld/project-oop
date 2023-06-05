package huce.cntt.oop.doan.entities.properties;

import java.util.regex.PatternSyntaxException;

import huce.cntt.oop.doan.entities.exception.ChuyenDiaChiException;

public class DiaChi {
    private String diaChi;

    public DiaChi(String input) throws ChuyenDiaChiException {
        try {
            String[] cacPhanDiaChi = input.split(",");
            if (cacPhanDiaChi.length <= 2) {
                throw new ChuyenDiaChiException("1 địa chỉ cần ít nhất 3 thành phần!!!");
            }
            this.diaChi = input;
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            throw new ChuyenDiaChiException();
        }
    }

    @Override
    public String toString() {
        return diaChi;
    }
}
