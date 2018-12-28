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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VentanaPrincipalController implements Initializable {

    private static final Logger LOG = Logger.getLogger(VentanaPrincipalController.class.getName());
    @FXML
    public TabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
    }

    private void cargar(String direccionFXML, String tituloPestania) {

          try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane root = loader.load(getClass().getResourceAsStream(direccionFXML));
                Tab t = new Tab();
                t.setText(tituloPestania);
                t.setContent(root);
                this.tabPane.getTabs().add(t);
                this.tabPane.getSelectionModel().select(t);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error al cargar registro", ex);
                Alert errDlg = new Alert(Alert.AlertType.ERROR);
                errDlg.setTitle("Error al cargar registro");
                errDlg.setHeaderText("Error al cargar registro: '" + tituloPestania + "'. Archivo: '" + direccionFXML + "'.");
                errDlg.setContentText(ex.getMessage());
                errDlg.showAndWait();
            }
        
    }

    @FXML
    private void OnActionRegistro(ActionEvent event) {
        this.cargar("/fxml/RegistroAlumno/registroAlumnoFXML.fxml", "Registro Alumno");
    }

    @FXML
    private void OnActionInscripcion(ActionEvent event) {
        
        this.cargar("/fxml/inscripcion/cargarInscripcionFXML.fxml", "Registro Inscripciones");
    }

    @FXML
    private void OnActionRegistroEncargado(ActionEvent event) {
        this.cargar("/fxml/RegistroEncargado/registroEncargadoFXML.fxml", "Registro Encargado");
    }

    @FXML
    private void OnActionListaAlumno(ActionEvent event) {
        this.cargar("/fxml/RegistroAlumno/tablaAlumnoFXML.fxml", "Lista de Alumnos");
    }

    @FXML
    private void OnActionListaEncargado(ActionEvent event) {
        this.cargar("/fxml/RegistroEncargado/tablaEncargadoFXML.fxml", "Lista de Encargado");
    }

    @FXML
    private void OnActionListaParentesco(ActionEvent event) {
         this.cargar("/fxml/listaParentesco/listaParentescoFXML.fxml", "Lista de Parentesco");
    }

    @FXML
    private void OnActionConcepto(ActionEvent event) {
        this.cargar("/fxml/RegistroConcepto/registroConceptoFXML.fxml", "Registro de Conceptos");
    }

    @FXML
    private void OnActionPagos(ActionEvent event) {
        this.cargar("/fxml/Pagos/PagosFXML.fxml", "Registro de Pagos");
    }
    
    
    
    

}
