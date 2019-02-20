package edu.snpp.proyectofinal.inscripcion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Alumno;

import edu.snpp.proyectofinal.entidades.Grado;
import edu.snpp.proyectofinal.entidades.Inscripcion;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.kordamp.ikonli.javafx.FontIcon;

public class CargarInscripcionFXMLController implements Initializable {

    @FXML
    private TableView<Inscripcion> lista;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");

    @FXML
    private TableColumn<Inscripcion, Alumno> colum1;
    @FXML
    private TableColumn<Inscripcion, Date> colum2;
    @FXML
    private TableColumn<Inscripcion, Integer> colum3;
    @FXML
    private TableColumn<Inscripcion, Grado> colum4;

    @FXML
    private JFXTextField anho;
    @FXML
    private JFXComboBox<Grado> grado;

    private JFXListView<Alumno> listv = new JFXListView<>();

    Alumno alu;
    Inscripcion in;

    JFXPopup popup = new JFXPopup();

    private boolean mostrar = true;

    @FXML
    private JFXTextField alumno;
    @FXML
    private JFXTextField txfbuscar;
    @FXML
    private JFXDatePicker fechainscrip;
    @FXML
    private JFXButton agregar;
    @FXML
    private TableColumn colModif;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargardatos();

        cargargrado();
        listv.setCellFactory((ListView<Alumno> a)-> new AlumnoListCell());//para que no muestre con cod.

        grado.setCellFactory((ListView<Grado> g) -> new GradoListCell());
        grado.setButtonCell(new GradoListCell());

        //para cargar los encargados en el popup
        popup.setPopupContent(listv);
        

        this.inicializarListaAlumno();

        this.alumno.textProperty().addListener((ObservableValue<? extends String> obs, String anterior, String nuevo) -> {

            if (this.mostrar) {
                this.cargarAlumno(nuevo);
                popup.show(alumno, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, alumno.getHeight());

            } else {

                this.mostrar = true;
            }
        });
        //hasta aca

        //para que la fecha salga bien
        this.colum2.setCellFactory((TableColumn<Inscripcion, Date> fi) -> {
            return new TableCell<Inscripcion, Date>() {
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
                
        
        //alumnoTablCell
        colum1.setCellValueFactory(new PropertyValueFactory<>("alumno"));
        colum1.setCellFactory((TableColumn<Inscripcion, Alumno> a) -> new alumnoTableCell());

        //fecha de inscripcion
        colum2.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        //Año electivo
        colum3.setCellValueFactory(new PropertyValueFactory<>("anhoelectivo"));

        colum4.setCellValueFactory(new PropertyValueFactory<>("grado"));
        colum4.setCellFactory((TableColumn<Inscripcion, Grado> g) -> new GradoTableCell());

        //la parte de iconos modificar
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

                            Inscripcion i = (Inscripcion) this.getTableRow().getItem();

                            alumno.setText(i.getAlumno().getNombre() + " " + i.getAlumno().getApellido());
                            alu= i.getAlumno();
                            in= i;
                            grado.setValue(i.getGrado());

                            Calendar c = new GregorianCalendar();
                            c.setTime(i.getFecha());
                            LocalDate fe = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
                            fechainscrip.setValue(fe);

                            anho.setText(i.getAnhoelectivo().toString());

                            agregar.setText("Guardar");
                        });
                        this.setGraphic(btnModificar);
                        
                    } 
                    else
                    {
                        this.setGraphic(null);
                    }
                    super.updateItem(item, empty);
                }
            };
        });
 }//hasta aca

    @FXML

    private void onActionBuscar(ActionEvent event) {
        buscar();

    }

    @FXML
    private void onActionAgregar(ActionEvent event) {

        EntityManager em = emf.createEntityManager();

        if (this.agregar.getText().equals("Agregar")) {

            in = new Inscripcion();

            in.setAlumno(alu);
            in.setGrado(grado.getValue());

            Calendar c = new GregorianCalendar(fechainscrip.getValue().getYear(), fechainscrip.getValue().getMonthValue() - 1, fechainscrip.getValue().getDayOfMonth());
            in.setFecha(c.getTime());
            

            in.setAnhoelectivo(Integer.parseInt(anho.getText()));
            anho.clear();

            em.getTransaction().begin();
            em.persist(in);
            em.getTransaction().commit();
            JFXSnackbar sb = new JFXSnackbar(pane);
            sb.show("El registro se a completado con éxito", 5000);

        } else {

           // Inscripcion in = lista.getSelectionModel().getSelectedItem();

            in.setAlumno(alu);
            alumno.clear();
            
            in.setGrado(grado.getValue());
            
            in.setAnhoelectivo(Integer.parseInt(anho.getText()));
            anho.clear();
            
            Calendar c = new GregorianCalendar(fechainscrip.getValue().getYear(), fechainscrip.getValue().getMonthValue() - 1, fechainscrip.getValue().getDayOfMonth());
            in.setFecha(c.getTime());
            fechainscrip.setValue(null);

            em.getTransaction().begin();
            em.merge(in);
            em.getTransaction().commit();
            JFXSnackbar sb = new JFXSnackbar(pane);
            sb.show("El registro se a completado con éxito", 5000);
            this.grado.getSelectionModel().clearSelection();//limpiar combobox
            
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
        Alert e = new Alert(Alert.AlertType.CONFIRMATION);
        e.setTitle("Eliminar");
        e.setHeaderText("¿Desea Eliminar esta Inscripción?");
        Optional<ButtonType> result = e.showAndWait();
        if (result.get() == ButtonType.OK) {

            em.getTransaction().begin();
            em.remove(em.merge(lista.getSelectionModel().getSelectedItem()));
            em.getTransaction().commit();
            JFXSnackbar sb = new JFXSnackbar(pane);
            sb.show("La acción se a completado con éxito", 5000);
            cargardatos();
        }
    }

    private void cargarAlumno(String busc) {

        EntityManager em = emf.createEntityManager();
        if (busc.isEmpty()) {
            TypedQuery<Alumno> q = em.createQuery("SELECT tm FROM Alumno tm", Alumno.class);
            listv.getItems().clear();
            listv.getItems().addAll(q.getResultList());
        } else {
            TypedQuery<Alumno> q = em.createQuery("SELECT tm FROM Alumno tm WHERE LOWER(tm.nombre) LIKE :busc", Alumno.class);
            q.setParameter("busc", "%" + busc.toLowerCase() + "%");
            listv.getItems().clear();
            listv.getItems().addAll(q.getResultList());
        }
    }

    private void inicializarListaAlumno() {
        //para que agregue al dar click
        listv.getStyleClass().addAll("combo-box-popup");
        listv.setOnKeyReleased(a -> {
            if (a.getCode() == KeyCode.ENTER) {
                select(listv);
                a.consume();
            }
            if (a.getCode() == KeyCode.ESCAPE) {
                popup.hide();
                a.consume();
            }
        });
        listv.setOnMouseClicked(a -> {
            if (a.getClickCount() == 1) {
                select(listv);

            }
            a.consume();
        });
        //hasta aca

    }

    // select para añadir lista 
    private void select(JFXListView<Alumno> listv) {
        mostrar = false;
        alumno.setText(listv.getSelectionModel().getSelectedItem().getNombre() + " " + listv.getSelectionModel().getSelectedItem().getApellido());
        alu = listv.getSelectionModel().getSelectedItem();
        this.popup.hide();
    }

    private void cargardatos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Inscripcion> q = em.createQuery("SELECT tm FROM Inscripcion tm", Inscripcion.class);
        lista.getItems().clear();
        lista.getItems().addAll(q.getResultList());

    }

    private void cargargrado() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Grado> q = em.createQuery("SELECT tm FROM Grado tm", Grado.class);
        grado.getItems().clear();
        grado.getItems().addAll(q.getResultList());
    }
    
    private void buscar() {
        EntityManager em = emf.createEntityManager();
        String b = txfbuscar.getText();
        TypedQuery<Inscripcion> q = em.createQuery("SELECT a FROM Inscripcion a WHERE LOWER(a.alumno.nombre) LIKE :txt", Inscripcion.class);
        
        q.setParameter("txt", "%" + b.toLowerCase() + "%");
        lista.getItems().clear();
        lista.getItems().addAll(q.getResultList());
    }


}
