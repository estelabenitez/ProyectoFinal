
package edu.snpp.proyectofinal.listaParentesco;

import edu.snpp.proyectofinal.entidades.DetalleEncargado;
import edu.snpp.proyectofinal.entidades.Encargado;
import javafx.scene.control.TableCell;

/**
 *
 * @author estela
 */
public class EncargadoTableCell extends TableCell<DetalleEncargado, Encargado> {

    @Override
    protected void updateItem(Encargado item, boolean empty) {
        if (!empty && item != null) {

            this.setText(item.getNombre());
            this.setText(item.getApellido());
            
        } else {
            
            this.setText("");
        }
        super.updateItem(item, empty);
    }
}
