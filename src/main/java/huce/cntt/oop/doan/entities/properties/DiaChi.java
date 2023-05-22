package huce.cntt.oop.doan.entities.properties;

import java.util.HashMap;
import java.util.Map;

enum DonVi {
    SO("số "), DUONG(", đường "), NGO(", ngõ "), QUAN(", quận "),
    PHO(", phố "), XA("xã "), HUYEN(", huyện "), TINH(", tỉnh ");

    private final String value;

    DonVi(String value) {
        this.value = value;
    }

    public String getUnicode() {
        return value;
    }
}

public class DiaChi {

    private final Map<DonVi, String> diaChi;

    private StringBuilder giaTriInRa;

    private static boolean isString(String str) {
        return str.matches("^[A-Za-z ]+$");
    }

    private static boolean isNumber(String str){
        return str.matches("^[0-9 ]+$");
    }

    /**
     * @param diaChi : tự động chuyển đổi địa chỉ hộ
     * @apiNote Logic : các thành phần chia theo dấu ","
     
     * @serialData Nếu có 3 thành phần, check xem cái đầu là chữ hay số
            * Chữ : gắn XA, HUYEN, TINH
            * Số  : gắn SO, DUONG, QUAN
            
     * @serialData Nếu có 4 thành phần, check cái thứ 2 là gì
            * Chữ : gắn SO, NGO, DUONG, QUAN
            * Số  : gắn SO, PHO, DUONG, QUAN
     * @serialData Nếu có 5 thành phần : 
            Format : SO, NGO, PHO, DUONG, QUAN
     */
    public DiaChi(String nguoi_dung_nhap) {
        String[] cacThanhPhan = nguoi_dung_nhap.split(",");
        int soThanhPhan = cacThanhPhan.length;
        this.diaChi = new HashMap<DonVi, String>();
        giaTriInRa = new StringBuilder();

        if (soThanhPhan <= 2) {
            giaTriInRa.append(nguoi_dung_nhap);
            return;
            // throw new IllegalArgumentException("Không thể nhận dạng địa chỉ này");
        }

        String cacTp0 = cacThanhPhan[0], cacTp1 = cacThanhPhan[1], cacTp2 = cacThanhPhan[2];
        if (soThanhPhan == 3) {

            if (isString(cacThanhPhan[0])) {
                diaChi.put(DonVi.XA, cacTp0);
                diaChi.put(DonVi.HUYEN, cacTp1);
                diaChi.put(DonVi.TINH, cacTp2);
                giaTriInRa.append(DonVi.XA.getUnicode()).append(cacTp0)
                        .append(DonVi.HUYEN.getUnicode()).append(cacTp1)
                        .append(DonVi.TINH.getUnicode()).append(cacTp2);
                return;
            }
            else {
                diaChi.put(DonVi.SO, cacTp0);
                diaChi.put(DonVi.DUONG, cacTp1);
                diaChi.put(DonVi.QUAN, cacTp2);
                giaTriInRa.append(DonVi.SO.getUnicode()).append(cacTp0)
                        .append(DonVi.DUONG.getUnicode()).append(cacTp1)
                        .append(DonVi.QUAN.getUnicode()).append(cacTp2);
                return;
            }
        }  
        
        String cacTp3 = cacThanhPhan[3];
        
        if (soThanhPhan == 4) {
            if (isNumber(cacTp1)) {
                diaChi.put(DonVi.SO, cacTp0);
                diaChi.put(DonVi.NGO, cacTp1);
                diaChi.put(DonVi.DUONG, cacTp2);
                diaChi.put(DonVi.QUAN, cacTp3);
                giaTriInRa.append(DonVi.SO.getUnicode()).append(cacTp0)
                        .append(DonVi.NGO.getUnicode()).append(cacTp1)
                        .append(DonVi.DUONG.getUnicode()).append(cacTp2)
                        .append(DonVi.QUAN.getUnicode()).append(cacTp3);
                return;
            } else {
                diaChi.put(DonVi.SO, cacTp0);
                diaChi.put(DonVi.PHO, cacTp1);
                diaChi.put(DonVi.DUONG, cacTp2);
                diaChi.put(DonVi.QUAN, cacTp3);
                giaTriInRa.append(DonVi.SO.getUnicode()).append(cacTp0)
                        .append(DonVi.PHO.getUnicode()).append(cacTp1)
                        .append(DonVi.DUONG.getUnicode()).append(cacTp2)
                        .append(DonVi.QUAN.getUnicode()).append(cacTp3);
                return;
            }
        } 
        else if (soThanhPhan >= 5) {
            String cacTp4 = cacThanhPhan[4];
            diaChi.put(DonVi.SO, cacTp0);
            diaChi.put(DonVi.NGO, cacTp1);
            diaChi.put(DonVi.PHO, cacTp2);
            diaChi.put(DonVi.DUONG, cacTp3);
            diaChi.put(DonVi.QUAN, cacTp4);
            giaTriInRa.append(DonVi.SO.getUnicode()).append(cacTp0)
                        .append(DonVi.NGO.getUnicode()).append(cacTp1)
                        .append(DonVi.PHO.getUnicode()).append(cacTp2)
                        .append(DonVi.DUONG.getUnicode()).append(cacTp3)
                        .append(DonVi.QUAN.getUnicode()).append(cacTp4);
                return;
        }
    }

    @Override
    public String toString() {
        if (this.giaTriInRa == null) {
            return "";
        }
        return this.giaTriInRa.toString();
    }

    public String formatToSaveDataBase() {
        if (diaChi == null) {
            throw new IllegalStateException("Thiếu giá trị ở địa chỉ " + this.diaChi.getClass().getSimpleName());
        }
        return diaChi.values().stream().reduce(
                (s1, s2) -> s1 + "," + s2)
                .orElse("");
    }
}