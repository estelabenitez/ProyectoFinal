package edu.snpp.proyectofinal.Pagos;

import edu.snpp.proyectofinal.entidades.Concepto;
import javafx.scene.control.ListCell;

/**
 *
 * @author estela
 */
public class conceptoListCell extends ListCell<Concepto> {
    
    @Override
    protected void updateItem(Concepto item, boolean empty) {
        if (!empty) {
            
            if (item.getTipo().equals(false)) {
                
                this.setText(item.getConcepto());
            }
        } else {
            this.setText("");
        }
        super.updateItem(item, empty);
    }
    
}
