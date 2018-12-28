package edu.snpp.proyectofinal.RegistroAlumno;

import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.MainApp;
import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.listaParentesco.listaParentescoFXMLController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author estela
 */
public class TablaAlumnoFXMLController implements Initializable {

    @FXML
    private JFXTextField txf;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    public TableView<Alumno> lista;
    @FXML
    private TableColumn<Alumno, String> nomb;
    @FXML
    private TableColumn<Alumno, String> ape;
    @FXML
    private TableColumn<Alumno, String> direc;
    @FXML
    private TableColumn<Alumno, Integer> ci;
    @FXML
    private TableColumn<Alumno, Date> fechanac;
    @FXML
    private TableColumn<Alumno, Integer> aporte;
    @FXML
    private TableColumn<Alumno, Boolean > colactivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargaralumno();
        nomb.setCellValueFactory(new PropertyValueFactory<>("nombre"));// el que esta en "" es como esta en la entidad alumno
        ape.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ci.setCellValueFactory(new PropertyValueFactory<>("ci"));
        fechanac.setCellValueFactory(new PropertyValueFactory<>("fechanac"));
        direc.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        aporte.setCellValueFactory(new PropertyValueFactory<>("montoaporte"));
        
        colactivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        colactivo.setCellFactory((TableColumn<Alumno, Boolean> b) -> new activoAlumno());
        
        
        

        //para que la fecha mierda salga bien
        this.fechanac.setCellFactory((TableColumn<Alumno, Date> a) -> {
            return new TableCell<Alumno, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    if (!empty) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                        this.setText(sdf.format(item));
                    } else {
                        this.setText("");
                    }

                    super.updateItem(item, true);
                }
            };
        });

    }

    @FXML
    private void onActionBuscar(ActionEvent event) {
        buscar();
    }

    @FXML
    private void onActionAgregar(ActionEvent event) {

        this.cargarModulo("/fxml/RegistroAlumno/registroAlumnoFXML.fxml", "Registro Alumno", 1);
        cargaralumno();
    }

    @FXML
    private void onActionModificar(ActionEvent event) {

        this.cargarModulo("/fxml/RegistroAlumno/registroAlumnoFXML.fxml", "Registro Alumno", 2);
        cargaralumno();
    }
    
    @FXML
    private void onActionAgregarParentesco(ActionEvent event) {
     
        if(lista.getSelectionModel().isEmpty()){
            
         Alert e = new Alert(Alert.AlertType.INFORMATION);
         e.setTitle("Parentesco");
         e.setHeaderText("Seleccione un Alumno antes de escoger Parentesco");
         e.showAndWait();
         

        }
        else{
            this.cargarModulo("/fxml/listaParentesco/listaParentescoFXML.fxml", "Lista de Parentesco", 3);
        cargaralumno();
        }
  }
    
    
    

    @FXML
    private void onActionEliminar(ActionEvent event) {

        EntityManager em = emf.createEntityManager();
        lista.getSelectionModel().getSelectedItem();
        Alert e = new Alert(Alert.AlertType.CONFIRMATION);
        e.setTitle("Eliminar");
        e.setHeaderText("¿Desea eliminar el Alumno Seleccionado?");
        Optional<ButtonType> result = e.showAndWait();
        if (result.get() == ButtonType.OK) {
            em.getTransaction().begin();
            em.remove(em.merge(lista.getSelectionModel().getSelectedItem()));
            em.getTransaction().commit();
            cargaralumno();
        }
    }

    @FXML
    private void onActionActualizar(ActionEvent event) {
        cargaralumno();
    }

    //crear metodo
    private void cargaralumno() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Alumno> q = em.createQuery("SELECT tm FROM Alumno tm", Alumno.class);
        lista.getItems().clear();
        lista.getItems().addAll(q.getResultList());

    }
    //crear metodo

    private void buscar() {
        EntityManager em = emf.createEntityManager();
        String b = txf.getText();
        TypedQuery<Alumno> q = em.createQuery("SELECT a FROM Alumno a WHERE LOWER(a.nombre) LIKE :txt OR LOWER(a.apellido) LIKE :txt OR SQL('CAST(? AS CHAR(11))',a.ci) LIKE :txt", Alumno.class);
        q.setParameter("txt", "%" + b.toLowerCase() + "%");
        lista.getItems().clear();
        lista.getItems().addAll(q.getResultList());
    }

    private void cargarModulo(String direccionFXML, String tituloPestania, int modo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            
            AnchorPane root = loader.load(getClass().getResourceAsStream(direccionFXML));
            

            // sirve para una parte de modificar
             if (modo == 2) {
                  RegistroAlumnoFXMLController ra = loader.getController();
                ra.cargaralumno(lista.getSelectionModel().getSelectedItem());
                
            }
            if (modo == 3) {
                  listaParentescoFXMLController lp = loader.getController();
                 lp.txfalumn.setText(lista.getSelectionModel().getSelectedItem().getNombre()+" "+lista.getSelectionModel().getSelectedItem().getApellido());
                 lp.txfid.setText(lista.getSelectionModel().getSelectedItem().getIdalumno().toString());
                
                
            }
           //hasta aca
           
            Tab t = new Tab();
            t.setText(tituloPestania);
            t.setContent(root);
            MainApp.VENTANAPRINCIPAL.tabPane.getTabs().add(t);
            MainApp.VENTANAPRINCIPAL.tabPane.getSelectionModel().select(t);
           
        } catch (IOException ex) {
            
            
            LOG.log(Level.SEVERE, "Error al cargar modulo", ex);
            Alert errDlg = new Alert(Alert.AlertType.ERROR);
            errDlg.setTitle("Error al cargar módulo");
            errDlg.setHeaderText("Error al cargar módulo: '" + tituloPestania + "'. Archivo: '" + direccionFXML + "'.");
            errDlg.setContentText(ex.getMessage());
            errDlg.showAndWait();
        }
    }
    private static final Logger LOG = Logger.getLogger(TablaAlumnoFXMLController.class.getName());

    @FXML
    private void OnKeyBUSCAR(KeyEvent event) {
        
        buscar();
    }

    
}
 