package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.DiemCaNhan;

public interface IDiemCaNhanService {

    // Xem
    public List<DiemCaNhan> layTatCaDiemCaNhan();

    public List<DiemCaNhan> layDiemCaNhanTheoMaSo(Integer maSo);

}
