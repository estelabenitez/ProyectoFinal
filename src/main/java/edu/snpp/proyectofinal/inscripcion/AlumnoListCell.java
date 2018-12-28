
package edu.snpp.proyectofinal.inscripcion;

import edu.snpp.proyectofinal.entidades.Alumno;
import javafx.scene.control.ListCell;

/**
 *
 * @author estela
 */
public class AlumnoListCell extends ListCell<Alumno>{
    @Override
         protected void updateItem(Alumno item, boolean empty){
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
