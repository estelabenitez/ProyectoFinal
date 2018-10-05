
package edu.snpp.proyectofinal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class VentanaPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }  
     private void cargarRegistro(String direccionFXML, String tituloPestania) {
         
         
     }
    @FXML
    private void OnActionRegistro(ActionEvent event) {
        
     this.cargarRegistro("/fxml/RegistroAlumno/registroAlumnoFXML.fxml", "Registro");
    }

}
