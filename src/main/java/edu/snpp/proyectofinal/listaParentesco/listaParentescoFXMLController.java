package edu.snpp.proyectofinal.listaParentesco;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.entidades.DetalleEncargado;
import edu.snpp.proyectofinal.entidades.DetalleEncargadoPK;
import edu.snpp.proyectofinal.entidades.Encargado;
import edu.snpp.proyectofinal.entidades.ParentescoFamiliar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author estela
 */
public class listaParentescoFXMLController implements Initializable {

    @FXML
    public TextField txfid;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");

    @FXML
    private TextField txfid1;
    @FXML
    public JFXTextField txfalumn;
    @FXML
    private JFXTextField txfenca;
    @FXML
    private TableColumn<DetalleEncargado, Alumno> columalum;
    @FXML
    private TableColumn<DetalleEncargado, ParentescoFamiliar> columparent;
    @FXML
    private JFXComboBox<ParentescoFamiliar> combparent;
    @FXML
    private TableColumn<DetalleEncargado, Encargado> columEnca;
    @FXML
    private TableView<DetalleEncargado> listaParen;

    private JFXListView<Encargado> listv = new JFXListView<>();
    ;
    JFXPopup popup = new JFXPopup();
    private boolean mostrar = true;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargardatos();
        cargarparentesco();
        combparent.setCellFactory((ListView<ParentescoFamiliar> pf) -> new ParentescoListCell());
        combparent.setButtonCell(new ParentescoListCell());

        //para agregar a la tabla o lista los tres datos
        columalum.setCellValueFactory(new PropertyValueFactory<>("Alumno"));
        columalum.setCellFactory((TableColumn<DetalleEncargado, Alumno> a) -> new AlumnoTableCell());

        columEnca.setCellValueFactory(new PropertyValueFactory<>("Encargado"));
        columEnca.setCellFactory((TableColumn<DetalleEncargado, Encargado> e) -> new EncargadoTableCell());

        columparent.setCellValueFactory(new PropertyValueFactory<>("ParentescoFamiliar"));
        columparent.setCellFactory((TableColumn<DetalleEncargado, ParentescoFamiliar> pf) -> new ParentescoTableCell());

        //para cargar los encargados en el popup
        popup.setPopupContent(listv);

        this.inicializarListaEnc();
        this.txfenca.textProperty().addListener((ObservableValue<? extends String> obs, String anterior, String nuevo) -> {

            if (this.mostrar) {
                this.cargarencargado(nuevo);
                popup.show(txfenca, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, txfenca.getHeight());

            } else {

                this.mostrar = true;
            }
        });
        //hasta aca

    }

    @FXML
    private void onActionAgregar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        
        DetalleEncargado de = new DetalleEncargado();
        DetalleEncargadoPK depk = new DetalleEncargadoPK();
        
        //de.setAlumno1(this.cargaridAlumno());
        //de.setEncargado1(this.cargaridEncargado());
        //de.setParentescoFamiliar(combparent.getValue());
        
        depk.setAlumno(Integer.parseInt(this.txfid.getText()));
        depk.setEncargado(Integer.parseInt(this.txfid1.getText()));
        depk.setParentesco(combparent.getValue().getIdparentescoFamiliar());
        
        de.setDetalleEncargadoPK(depk);
        
        em.getTransaction().begin();
        em.persist(de);
        em.getTransaction().commit();
        
        

    }

    @FXML
    private void onActionActualizar(ActionEvent event) {
    }

    @FXML
    private void onActionModificar(ActionEvent event) {
    }

    @FXML
    private void onActionEliminar(ActionEvent event) {

    }

    private void cargarparentesco() {

        EntityManager em = emf.createEntityManager();
        TypedQuery<ParentescoFamiliar> q = em.createQuery("SELECT tm FROM ParentescoFamiliar tm", ParentescoFamiliar.class);
        combparent.getItems().clear();
        combparent.getItems().addAll(q.getResultList());
    }

    private Alumno cargaridAlumno() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Alumno> q = em.createQuery("SELECT a FROM Alumno a WHERE a.idalumno= :p ", Alumno.class);
        q.setParameter("p", Integer.parseInt(txfid.getText()));

        return q.getSingleResult();

    }
    
    private Encargado cargaridEncargado() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Encargado> q = em.createQuery("SELECT e FROM Encargado e WHERE e.idencargado= :en ", Encargado.class);
        q.setParameter("en", Integer.parseInt(txfid1.getText()));

        return q.getSingleResult();
    }

    

    private void cargarencargado(String busc) {

        EntityManager em = emf.createEntityManager();
        if (busc.isEmpty()) {
            TypedQuery<Encargado> q = em.createQuery("SELECT tm FROM Encargado tm", Encargado.class);
            listv.getItems().clear();
            listv.getItems().addAll(q.getResultList());
        } else {
            TypedQuery<Encargado> q = em.createQuery("SELECT tm FROM Encargado tm WHERE LOWER(tm.nombre) LIKE :busc", Encargado.class);
            q.setParameter("busc", "%"+busc.toLowerCase()+"%");
            listv.getItems().clear();
            listv.getItems().addAll(q.getResultList());
        }
    }

    private void inicializarListaEnc() {
        //para que agregue al dar click
        listv.getStyleClass().addAll("combo-box-popup");
        listv.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                select(listv);
                e.consume();
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                popup.hide();
                e.consume();
            }
        });
        listv.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) {
                select(listv);
            }
            e.consume();
        });
        //hasta aca

    }

    private void select(JFXListView<Encargado> listv) {
        mostrar = false;
        txfid1.setText(listv.getSelectionModel().getSelectedItem().getIdencargado().toString());
        txfenca.setText(listv.getSelectionModel().getSelectedItem().getNombre() + " " + listv.getSelectionModel().getSelectedItem().getApellido());
        this.popup.hide();
    }
    
    private void cargardatos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<DetalleEncargado> q = em.createQuery("SELECT tm FROM DetalleEncargado tm", DetalleEncargado.class);
        listaParen.getItems().clear();
        listaParen.getItems().addAll(q.getResultList());
    }



}
