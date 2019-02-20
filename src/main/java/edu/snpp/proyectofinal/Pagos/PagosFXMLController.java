package edu.snpp.proyectofinal.Pagos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Concepto;
import edu.snpp.proyectofinal.entidades.DetalleCaja;
import edu.snpp.proyectofinal.entidades.MovimientoCaja;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
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
public class PagosFXMLController implements Initializable {

    @FXML
    private JFXComboBox<Concepto> combconcep;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");

    @FXML
    private JFXTextField txfmonto;

    @FXML
    private JFXTextField txfdescrip;
    @FXML
    private JFXButton buttonpagar;

    @FXML
    private JFXButton buttoncancel;
    @FXML
    private JFXTextField numfac;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarConcepto();

        combconcep.setCellFactory((ListView<Concepto> c) -> new conceptoListCell());
        combconcep.setButtonCell(new conceptoListCell());

    }

    private void cargarConcepto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Concepto> q = em.createQuery("SELECT tm FROM Concepto tm", Concepto.class);
        combconcep.getItems().clear();
        combconcep.getItems().addAll(q.getResultList());
    }

    //para que cargue el id de la caja habilitada
    private MovimientoCaja obtenerCajaHabilitada() {

        EntityManager em = emf.createEntityManager();
        MovimientoCaja movimientoCaja = null;

        TypedQuery<MovimientoCaja> q = em.createQuery("SELECT m FROM MovimientoCaja m WHERE m.habilitado = 1", MovimientoCaja.class);

        movimientoCaja = q.getResultList().get(0);

        return movimientoCaja;

    }

    @FXML
    private void onActionPagar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();

        DetalleCaja dc = new DetalleCaja();
        

        dc.setCaja(obtenerCajaHabilitada());

        dc.setEntrada(0);

        dc.setSalida(Integer.parseInt(txfmonto.getText()));
        txfmonto.clear();

        dc.setDescripcion(txfdescrip.getText());
        txfdescrip.clear();

        dc.setConcepto(combconcep.getValue());

        dc.setNumfactura(numfac.getText());
        numfac.clear();

            //para que guarde la hora de sistema.
        Date d=new Date();
        System.out.println("Timestamp: "+d.getTime());
        dc.setHora(d);

        em.getTransaction().begin();
        em.persist(dc);
        em.getTransaction().commit();
        JFXSnackbar sb = new JFXSnackbar(pane);
        sb.show("El registro se a completado con éxito", 5000);
        this.combconcep.getSelectionModel().clearSelection();
    }

    @FXML
    private void onActionCancelar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        Alert e = new Alert(Alert.AlertType.CONFIRMATION);
        e.setTitle("Cancelar");
        e.setHeaderText("¿Desea cancelar esta operción?");
        Optional<ButtonType> result = e.showAndWait();
        if (result.get() == ButtonType.OK) {
            txfmonto.clear();
            txfdescrip.clear();
            numfac.clear();
            this.combconcep.getSelectionModel().clearSelection();
            

        }

        
    }

}
