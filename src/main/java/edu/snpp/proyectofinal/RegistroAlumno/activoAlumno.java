
package edu.snpp.proyectofinal.RegistroAlumno;

import edu.snpp.proyectofinal.entidades.Alumno;
import javafx.scene.control.TableCell;

/**
 *
 * @author estela
 */
public class activoAlumno extends TableCell<Alumno, Boolean> {
    

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        if (!empty) {
            if(item.equals(true)){
                this.setText("Activo");
            }
            else{
                this.setText("Inactivo");
            }
            
            
        } else {
            
            this.setText("");
        }
        super.updateItem(item, true);
    }
}
