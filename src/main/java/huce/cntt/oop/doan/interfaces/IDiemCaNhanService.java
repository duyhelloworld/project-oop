package huce.cntt.oop.doan.interfaces;

import java.util.List;

import huce.cntt.oop.doan.entities.DiemCaNhan;
import huce.cntt.oop.doan.entities.exception.DatabaseException;

public interface IDiemCaNhanService {
    public List<DiemCaNhan> layDiemCaNhanTheoMaSo(Integer maSo) throws DatabaseException;
}
