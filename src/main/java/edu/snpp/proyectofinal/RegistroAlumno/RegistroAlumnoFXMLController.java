
package edu.snpp.proyectofinal.RegistroAlumno;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Alumno;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.persistence.EntityManager;
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
    @FXML
    private JFXDatePicker fechanac;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void OnActionAgregar(ActionEvent event) {
        EntityManager em=emf.createEntityManager();
        Alumno a= new Alumno();
        
        a.setNombre(nomAlum.getText());
        nomAlum.clear();
        
        a.setApellido(apeAlum.getText());
        apeAlum.clear();
        
        a.setDireccion(direcAlum.getText());
        direcAlum.clear();
        
        a.setCi(ciAlum.getText());
        ciAlum.clear();
        
        a.setMontoAporte(Integer.parseInt(aporteAlum.getText()));
        aporteAlum.clear();
        
        Calendar c=new GregorianCalendar( fechanac.getValue().getYear(),  fechanac.getValue().getMonthValue()-1,  fechanac.getValue().getDayOfMonth());
        
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
       
       
        
        
        
        
    }
    
}
