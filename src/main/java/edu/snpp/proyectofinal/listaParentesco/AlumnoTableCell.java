
package edu.snpp.proyectofinal.listaParentesco;

import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.entidades.DetalleEncargado;
import javafx.scene.control.TableCell;


/**
 *
 * @author estela
 */
public class AlumnoTableCell extends TableCell<DetalleEncargado , Alumno>{
    
    @Override
         protected void updateItem(Alumno item, boolean empty){
          if(!empty && item!= null){
              
              this.setText(item.getNombre());
              this.setText(item.getApellido());
          }
          else
          {
              this.setText("");
          }
          super.updateItem(item, empty);
      }

    
    
}
