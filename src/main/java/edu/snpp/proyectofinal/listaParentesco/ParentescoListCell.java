
package edu.snpp.proyectofinal.listaParentesco;

import edu.snpp.proyectofinal.entidades.ParentescoFamiliar;
import javafx.scene.control.ListCell;

/**
 *
 * @author estela
 */
public class ParentescoListCell extends ListCell<ParentescoFamiliar>{
    @Override
         protected void updateItem(ParentescoFamiliar item, boolean empty){
          if(!empty){
              
              this.setText(item.getDescripcion());
          }
          else
          {
              this.setText("");
          }
          super.updateItem(item, empty);
      }
    
    
}
