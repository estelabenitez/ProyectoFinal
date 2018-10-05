
package edu.snpp.proyectofinal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;



public class VentanaPrincipalController implements Initializable {

    private static final Logger LOG = Logger.getLogger(VentanaPrincipalController.class.getName());
    @FXML
    private TabPane tabPane;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
    }  
    
    private void cargarRegistro(String direccionFXML, String tituloPestania) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = FXMLLoader.load(getClass().getResource(direccionFXML));
            Tab t=new Tab();
            t.setText(tituloPestania);
            t.setContent(root);
            this.tabPane.getTabs().add(t);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al cargar registro", ex);
            Alert errDlg=new Alert(Alert.AlertType.ERROR);
            errDlg.setTitle("Error al cargar registro");
            errDlg.setHeaderText("Error al cargar registro: '"+tituloPestania+"'. Archivo: '"+direccionFXML+"'.");
            errDlg.setContentText(ex.getMessage());
            errDlg.showAndWait();
        }
    }

    @FXML
    private void OnActionRegistro(ActionEvent event) {
        this.cargarRegistro("/fxml/RegistroAlumno/registroAlumnoFXML.fxml", "Registro");
    }
    
    
    

}
