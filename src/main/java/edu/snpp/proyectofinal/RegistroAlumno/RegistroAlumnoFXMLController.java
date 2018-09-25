
package edu.snpp.proyectofinal.RegistroAlumno;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author estela
 */
public class RegistroAlumnoFXMLController implements Initializable {

    @FXML
    private JFXTextField nomAlum;
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private JFXTextField apeAlum;
    @FXML
    private JFXTextField direcAlum;
    @FXML
    private JFXTextField ciAlum;
    @FXML
    private JFXTextField fechnacAlum;
    @FXML
    private JFXTextField aporteAlum;
    @FXML
    private JFXTextField nomEnca;
    @FXML
    private JFXTextField apeEnca;
    @FXML
    private JFXTextField direcEnca;
    @FXML
    private JFXTextField telfEnca;
    @FXML
    private JFXButton Agregar;
    @FXML
    private JFXTextField ciEnca;
    @FXML
    private JFXComboBox<?> parenEnca;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActionAgregar(ActionEvent event) {
        
    }
    
}
