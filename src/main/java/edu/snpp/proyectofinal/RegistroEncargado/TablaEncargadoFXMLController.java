package edu.snpp.proyectofinal.RegistroEncargado;

import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.MainApp;
import edu.snpp.proyectofinal.RegistroAlumno.TablaAlumnoFXMLController;
import edu.snpp.proyectofinal.entidades.Encargado;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class TablaEncargadoFXMLController implements Initializable {

    @FXML
    private JFXTextField txf;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    
    @FXML
    private TableColumn<Encargado, String> nomb;
    @FXML
    private TableColumn<Encargado, String> ape;
    @FXML
    private TableColumn<Encargado, String> direc;
    @FXML
    private TableColumn<Encargado, Integer> ci;
    @FXML
    private TableColumn<Encargado, String> Telf;
    @FXML
    public TableView<Encargado> listaEnca;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarencargado();
        nomb.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ape.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ci.setCellValueFactory(new PropertyValueFactory<>("ci"));
        direc.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        Telf.setCellValueFactory(new PropertyValueFactory<>("telefono"));

    }

    @FXML
    private void onActionBuscar(ActionEvent event) {
        buscar();
    }

    @FXML
    private void onActionAgregar(ActionEvent event) {
        this.cargarModulo("/fxml/RegistroEncargado/registroEncargadoFXML.fxml", "Registro Encargado", 1);
        cargarencargado();

    }

    @FXML
    private void onActionActualizar(ActionEvent event) {
        cargarencargado();
    }

    @FXML
    private void onActionModificar(ActionEvent event) {
        this.cargarModulo("/fxml/RegistroEncargado/registroEncargadoFXML.fxml", "Registro Encargado", 2);
        cargarencargado();

    }

    @FXML
    private void onActionEliminar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        listaEnca.getSelectionModel().getSelectedItem();
        Alert e = new Alert(Alert.AlertType.CONFIRMATION);
        e.setTitle("Eliminar");
        e.setHeaderText("¿Desea eliminar el Encargado Seleccionado?");
        Optional<ButtonType> result = e.showAndWait();
        if (result.get() == ButtonType.OK) {
            em.getTransaction().begin();
            em.remove(em.merge(listaEnca.getSelectionModel().getSelectedItem()));
            em.getTransaction().commit();
            cargarencargado();
        }
    }

    private void buscar() {
        EntityManager em = emf.createEntityManager();
        String b = txf.getText();
        TypedQuery<Encargado> q = em.createQuery("SELECT e FROM Encargado e WHERE LOWER(e.nombre) LIKE :txt OR LOWER(e.apellido) LIKE :txt OR SQL('CAST(? AS CHAR(11))',e.ci) LIKE :txt", Encargado.class);
        q.setParameter("txt", "%" + b.toLowerCase() + "%");
        listaEnca.getItems().clear();
        listaEnca.getItems().addAll(q.getResultList());
    }

    //crear metodo
    private void cargarencargado() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Encargado> q = em.createQuery("SELECT tm FROM Encargado tm", Encargado.class);
        listaEnca.getItems().clear();
        listaEnca.getItems().addAll(q.getResultList());

    }

    private void cargarModulo(String direccionFXML, String tituloPestania, int modo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResourceAsStream(direccionFXML));

            // sirve para una parte de modificar
            RegistroEncargadoFXMLController re = loader.getController();
            if (modo == 2) {
                re.cargarencargado(listaEnca.getSelectionModel().getSelectedItem());
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

}
