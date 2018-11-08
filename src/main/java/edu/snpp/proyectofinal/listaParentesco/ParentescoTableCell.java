package edu.snpp.proyectofinal.listaParentesco;

import edu.snpp.proyectofinal.entidades.DetalleEncargado;
import edu.snpp.proyectofinal.entidades.ParentescoFamiliar;
import javafx.scene.control.TableCell;

/**
 *
 * @author estela
 */
public class ParentescoTableCell extends TableCell<DetalleEncargado, ParentescoFamiliar> {

    @Override
    protected void updateItem(ParentescoFamiliar item, boolean empty) {
        if (!empty && item != null) {

            this.setText(item.getDescripcion());
        } else {
            this.setText("");
        }
        super.updateItem(item, empty);
    }
}
