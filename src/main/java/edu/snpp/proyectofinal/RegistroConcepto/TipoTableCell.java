
package edu.snpp.proyectofinal.RegistroConcepto;

import edu.snpp.proyectofinal.entidades.Concepto;
import javafx.scene.control.TableCell;

/**
 *
 * @author estela
 */
public class TipoTableCell extends TableCell<Concepto,  Boolean> {
    

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        if (!empty) {
            if(item){
                this.setText("Ingreso");
            }
            else{
                this.setText("Gasto");
            }
            
            
        } else {
            
            this.setText("");
        }
        super.updateItem(item, empty);
    }
}
