package edu.snpp.proyectofinal.RegistroAlumno;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.entidades.Encargado;
import edu.snpp.proyectofinal.entidades.ParentescoFamiliar;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author estela
 */
public class RegistroAlumnoFXMLController implements Initializable {

    @FXML
    private JFXTextField nomAlum;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
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
    private JFXComboBox<ParentescoFamiliar> parenEnca;
    @FXML
    private JFXDatePicker fechanac;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarparentesco();
        parenEnca.setCellFactory((ListView<ParentescoFamiliar> p) -> new ParentescoListCell());
        parenEnca.setButtonCell(new ParentescoListCell());
    }

    @FXML
    private void OnActionAgregar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        if (Agregar.getText().equals("Agregar")) {
            Alumno a = new Alumno();

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
            
            Calendar c = new GregorianCalendar(fechanac.getValue().getYear(), fechanac.getValue().getMonthValue() - 1, fechanac.getValue().getDayOfMonth());
            a.setFechaNac(c.getTime());
            

            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        }

        if (Agregar.getText().equals("Agregar")) {
            Encargado e = new Encargado();

            e.setNombre(nomEnca.getText());
            nomEnca.clear();

            e.setApellido(apeEnca.getText());
            apeEnca.clear();

            e.setDireccion(direcEnca.getText());
            direcEnca.clear();

            e.setTelefono(telfEnca.getText());
            telfEnca.clear();

            e.setCi(ciEnca.getText());
            ciEnca.clear();
        }
    }

    private void cargarparentesco() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ParentescoFamiliar> q = em.createQuery("SELECT tm FROM ParentescoFamiliar tm", ParentescoFamiliar.class);
        parenEnca.getItems().clear();
        parenEnca.getItems().addAll(q.getResultList());
    }
}
