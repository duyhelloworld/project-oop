package huce.cntt.oop.doan.controller;

import huce.cntt.oop.doan.entities.SinhVien;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CheckBoxTableCellFactory implements Callback<TableColumn<SinhVien, CheckBox>, TableCell<SinhVien, CheckBox>> {

    @Override
    public TableCell<SinhVien, CheckBox> call(TableColumn<SinhVien, CheckBox> arg0) {
        return new TableCell<SinhVien, CheckBox>() {
            private final CheckBox checkBox = new CheckBox();
                {
                    checkBox.setAlignment(Pos.CENTER);
                    checkBox.setOnAction(e -> {
                        if (getTableRow() != null && getItem() != null) {
                            getItem().setSelected(checkBox.isSelected());
                        }
                    });
                }

                @Override
                protected void updateItem(CheckBox item, boolean empty) {
                    super.updateItem(item, empty);
    
                    if (empty || getItem() == null) {
                        setGraphic(null);
                    } else {
                        checkBox.setSelected(getItem().isSelected());
                        setGraphic(checkBox);
                    }
                }
        };
    }
    
}
