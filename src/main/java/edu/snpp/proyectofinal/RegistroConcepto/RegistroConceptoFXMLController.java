package edu.snpp.proyectofinal.RegistroConcepto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;

import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Concepto;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * FXML Controller class
 *
 * @author estela
 */
public class RegistroConceptoFXMLController implements Initializable {

    @FXML
    private JFXTextField txfbuscar;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private JFXTextField nombtxf;
    @FXML
    private JFXTextField montotxf;
    @FXML
    private TableView<Concepto> lista;
    @FXML
    private TableColumn<Concepto, String> colconcepto;
    @FXML
    private TableColumn<Concepto, Integer> colmonto;
    @FXML
    private TableColumn<Concepto, Boolean> colTipo;
    @FXML
    private TableColumn colModif;

    @FXML
    private JFXButton agregar;
    @FXML
    private RadioButton radioingreso;
    @FXML
    private RadioButton radiogasto;
    @FXML
    private ToggleGroup Seleccionar;
    
    Concepto conc;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargardatos();

        colconcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        colmonto.setCellValueFactory(new PropertyValueFactory<>("montoIngreso"));
        
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colTipo.setCellFactory((TableColumn<Concepto, Boolean> b) -> new TipoTableCell());
        
        
       
        

        //icon modificar
        this.colModif.setCellFactory((TableColumn) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    if (!empty) {
                        Button btnModificar = new Button();

                        FontIcon icono = new FontIcon("gmi-create");
                        btnModificar.setGraphic(icono);
                        btnModificar.setOnAction((evt) -> {

                            EntityManager em = emf.createEntityManager();

                            Concepto co = (Concepto) this.getTableRow().getItem();
                            
                            conc= co;
                            
                            nombtxf.setText(co.getConcepto());
                            
                            if(co.getTipo().equals(true)){//para que pueda obviar el monto cuando modifique
                                                             // un concepto gasto
                            montotxf.setText(co.getMontoIngreso().toString());
                            
                            }
                            
                            agregar.setText("Guardar");

                        });
                        this.setGraphic(btnModificar);
                    } else {
                        this.setGraphic(null);
                    }
                    super.updateItem(item, empty);

                }

            };
        });//hasta aca
    }

    @FXML
    private void onActionBuscar(ActionEvent event) {
        buscar();

    }

    @FXML
    private void onActionAgregar(ActionEvent event) {

        EntityManager em = emf.createEntityManager();
        boolean tipo = false;
        
        Concepto c = new Concepto();
        if (agregar.getText().equals("Agregar")) {
            if (radioingreso.isSelected()) {

                c.setConcepto(nombtxf.getText());
                nombtxf.clear();

                c.setMontoIngreso(Integer.parseInt(montotxf.getText()));
                montotxf.clear();
                
                c.setTipo(true);
                
            } 
            else{
                if (radiogasto.isSelected()) {

                    c.setConcepto(nombtxf.getText());
                    nombtxf.clear();
                    
                    
                    
                    c.setTipo(false);
                }
            }
            

            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            JFXSnackbar sb = new JFXSnackbar(pane);
            sb.show("El registro se a completado con éxito", 5000);

        } else {

           // Concepto conc = lista.getSelectionModel().getSelectedItem();

            conc.setConcepto(nombtxf.getText());
            nombtxf.clear();
            
            if(conc.getTipo().equals(true)){//para que pueda obviar el monto cuando modifique
                                            // un concepto gasto
            conc.setMontoIngreso(Integer.parseInt(montotxf.getText()));
            montotxf.clear();
            }

            em.getTransaction().begin();
            em.merge(conc);
            em.getTransaction().commit();
            JFXSnackbar sb = new JFXSnackbar(pane);
            sb.show("El registro se a completado con éxito", 5000);

            agregar.setText("Agregar");

        }

        cargardatos();
    }

    @FXML
    private void onActionActualizar(ActionEvent event) {
        cargardatos();
    }

    @FXML
    private void onActionEliminar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        lista.getSelectionModel().getSelectedItem();
        Alert c = new Alert(Alert.AlertType.CONFIRMATION);
        c.setTitle("Eliminar");
        c.setHeaderText("¿Desea Eliminar este Concepto?");
        Optional<ButtonType> result = c.showAndWait();
        if (result.get() == ButtonType.OK) {

            em.getTransaction().begin();
            em.remove(em.merge(lista.getSelectionModel().getSelectedItem()));
            em.getTransaction().commit();
            cargardatos();
        }
    }

    private void cargardatos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Concepto> q = em.createQuery("SELECT tm FROM Concepto tm", Concepto.class);
        lista.getItems().clear();
        lista.getItems().addAll(q.getResultList());

    }

    private void buscar() {
        EntityManager em = emf.createEntityManager();
        String b = txfbuscar.getText();
        TypedQuery<Concepto> q = em.createQuery("SELECT c FROM Concepto c WHERE LOWER(c.concepto) LIKE :txt", Concepto.class);

        q.setParameter("txt", "%" + b.toLowerCase() + "%");
        lista.getItems().clear();
        lista.getItems().addAll(q.getResultList());
    }

    @FXML
    private void OnActionKey(KeyEvent event) {
        buscar();
    }

    @FXML
    private void OnAcionRadioGasto(ActionEvent event) {
        this.montotxf.setDisable(this.radiogasto.isSelected());
        

    }

    @FXML
    private void OnActionIngreso(ActionEvent event) {
        this.montotxf.setDisable(false);
    }
    
   


}
