
package edu.snpp.proyectofinal.inscripcion;

import edu.snpp.proyectofinal.entidades.Grado;
import edu.snpp.proyectofinal.entidades.Inscripcion;
import javafx.scene.control.TableCell;

/**
 *
 * @author estela
 */
public class GradoTableCell extends TableCell<Inscripcion, Grado> {

    @Override
    protected void updateItem(Grado item, boolean empty) {
        if (!empty && item != null) {

            this.setText(item.getNombre());
        } else {
            this.setText("");
        }
        super.updateItem(item, empty);
    }
}
