
package edu.snpp.proyectofinal.inscripcion;

import edu.snpp.proyectofinal.entidades.Grado;
import javafx.scene.control.ListCell;

/**
 *
 * @author estela
 */
public class GradoListCell extends ListCell<Grado>{
    @Override
         protected void updateItem(Grado item, boolean empty){
          if(!empty){
              
              this.setText(item.getNombre());
          }
          else
          {
              this.setText("");
          }
          super.updateItem(item, empty);
      }
    
    
}
