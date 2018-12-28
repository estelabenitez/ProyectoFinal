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

import javafx.scene.control.TextField;

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
    private JFXButton Agregar;

    @FXML
    private JFXDatePicker fechanac;
    @FXML
    private TextField txfid;
    boolean al=false;
    @FXML
    private JFXComboBox<String> combactivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.combactivo.getItems().addAll("Activo", "Inactivo");

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

            a.setCi(Integer.parseInt(ciAlum.getText()));
            ciAlum.clear();

            a.setMontoaporte(Integer.parseInt(aporteAlum.getText()));
            aporteAlum.clear();
            
            a.setActivo(this.convertiractivo(combactivo.getValue()));
            
            
            

            Calendar c = new GregorianCalendar(fechanac.getValue().getYear(), fechanac.getValue().getMonthValue() - 1, fechanac.getValue().getDayOfMonth());
            a.setFechanac(c.getTime());

            em.getTransaction().begin();
            
            //este ya es para modificar
            if(al){//modifica
                a.setIdalumno(Integer.parseInt(txfid.getText()));
                em.merge(a);
            }
            else{//registra
            em.persist(a);                
            }
            em.getTransaction().commit();
            this.combactivo.getSelectionModel().clearSelection();//limpiar combobox
        }
    }
    
    //esta en otra interfaz por eso es este
    public void cargaralumno(Alumno alum ){
      txfid.setText(alum.getIdalumno().toString());
      nomAlum.setText(alum.getNombre());
      apeAlum.setText(alum.getApellido());
      ciAlum.setText(alum.getCi().toString());
      aporteAlum.setText(alum.getMontoaporte().toString());
      direcAlum.setText(alum.getDireccion());
      combactivo.setValue(alum.getActivo().toString());
      al= true;
      
    }
    
    private boolean convertiractivo(String acti){
        boolean num= true;
        switch (acti){
            case "Activo":
                num= true;
              break;
              
            case "Inactivo":
                num = false;
                break;
            default: 
                System.out.println("Error");
                
                break;
            
        }
        
        return num;
        
        
    }



}
