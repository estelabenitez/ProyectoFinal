
package edu.snpp.proyectofinal.inscripcion;

import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.entidades.Inscripcion;
import javafx.scene.control.TableCell;

/**
 *
 * @author estela
 */
public class alumnoTableCell extends TableCell<Inscripcion , Alumno>{
     @Override
         protected void updateItem(Alumno item, boolean empty){
          if(!empty && item!= null){
              
              this.setText(item.getNombre()+" "+ item.getApellido());
          }
          else
          {
              this.setText("");
          }
          super.updateItem(item, empty);
      }

    
}
