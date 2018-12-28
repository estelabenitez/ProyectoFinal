
package edu.snpp.proyectofinal.listaParentesco;
import edu.snpp.proyectofinal.entidades.Encargado;
import javafx.scene.control.ListCell;

/**
 *
 * @author estela
 */
public class EncargadoListCell extends ListCell<Encargado>{
    @Override
         protected void updateItem(Encargado item, boolean empty){
          if(!empty){
              
              this.setText(item.getNombre()+" "+ item.getApellido());
          }
          else
          {
              this.setText("");
          }
          super.updateItem(item, empty);
      }
    
    
}
