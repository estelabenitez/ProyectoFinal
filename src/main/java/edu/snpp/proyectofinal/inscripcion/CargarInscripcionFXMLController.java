
package edu.snpp.proyectofinal.inscripcion;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;

import edu.snpp.proyectofinal.entidades.Grado;
import edu.snpp.proyectofinal.entidades.Inscripcion;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.table.DefaultTableModel;


public class CargarInscripcionFXMLController implements Initializable {

    @FXML
    private TableView<Inscripcion> lista;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    
    @FXML
    private TableColumn<Inscripcion , String> colum1;
    @FXML
    private TableColumn<Inscripcion , Date> colum2;
    @FXML
    private TableColumn<Inscripcion , Date> colum3;
    @FXML
    private TableColumn<Inscripcion , String> colum4;
    @FXML
    private JFXTextField txf;
    @FXML
    private JFXDatePicker inscrip;
    @FXML
    private JFXTextField anho;
    @FXML
    private JFXComboBox<Grado> grado;
   
    @FXML
    private JFXTextField alumno;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargardatos();
        DefaultTableModel lista = new DefaultTableModel();
        colum1.setCellValueFactory(
                new PropertyValueFactory<>("alumno")//importar datos de alumnos solo nombre y apellido.
        );
        colum2.setCellValueFactory(
                new PropertyValueFactory<>("fecha")
        );
        colum3.setCellValueFactory(
                new PropertyValueFactory<>("anhoelectivo")
       );
        
        colum4.setCellValueFactory(
                new PropertyValueFactory<>("grado")//importar datos de grado solo nombre.
       );
        
        
        //para buscar a mano para alumno en inscripcion
        this.alumno.textProperty().addListener((ObservableValue<? extends String> obs, String anterior, String nuevo) -> {

            if (!nuevo.isEmpty()) {
                JFXPopup popup = new JFXPopup();
                
                JFXListView<String> listv=new JFXListView<>();
                
                listv.getItems().add("Opcion ");
                listv.getItems().add("Opcion 2");
                listv.getItems().add("Opcion 3");
                popup.setPopupContent(listv);
                popup.show(alumno, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, alumno.getHeight());
            }
        });
        
    }    

    @FXML
    private void onActionBuscar(ActionEvent event) {
        
    }

    @FXML
    private void onActionAgregar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        Calendar c=new GregorianCalendar(inscrip.getValue().getYear(), inscrip.getValue().getMonthValue()-1, inscrip.getValue().getDayOfMonth());
        Inscripcion i = new Inscripcion();
      // i.setAlumno(alumno.getText());
       
        
    }
      

    @FXML
    private void onActionModificar(ActionEvent event) {
        
        EntityManager em = emf.createEntityManager();
        Calendar c= new GregorianCalendar(inscrip.getValue().getYear(), inscrip.getValue().getMonthValue()-1, inscrip.getValue().getDayOfMonth());
        Inscripcion i = lista.getSelectionModel().getSelectedItem();
        
    }

    @FXML
    private void onActionActualizar(ActionEvent event) {
    }

    @FXML
    private void onActionEliminar(ActionEvent event) {
    }

    @FXML
    private void onActionNuevo(ActionEvent event) {
    }
    
    private void cargardatos(){
        EntityManager em= emf.createEntityManager();
        TypedQuery<Inscripcion> q = em.createQuery("SELECT tm FROM Inscripcion tm", Inscripcion.class);
        lista.getItems().clear();
        lista.getItems().addAll(q.getResultList());
        
    }
}
