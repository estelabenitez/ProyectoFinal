
package edu.snpp.proyectofinal.RegistroEncargado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Encargado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author estela
 */
public class RegistroEncargadoFXMLController implements Initializable {

    @FXML
    private JFXTextField nomEnca;
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
   
    @FXML
    private JFXTextField apeEnca;
    @FXML
    private JFXTextField direcEnca;
    @FXML
    private JFXTextField ciEnca;
    @FXML
    private JFXTextField telfEnca;
    @FXML
    private JFXButton Agregar;
    @FXML
    private TextField txfid;
    boolean al=false;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActionAgregar(ActionEvent event) {
         EntityManager em = emf.createEntityManager();
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

            e.setCi(Integer.parseInt(ciEnca.getText()));
            ciEnca.clear();

            
            em.getTransaction().begin();
            
            
             //este ya es para modificar
            if(al){
                e.setIdencargado(Integer.parseInt(txfid.getText()));
                em.merge(e);
            }
            else{
            em.persist(e);                
            }
            em.getTransaction().commit();
            JFXSnackbar sb = new JFXSnackbar(pane);
            sb.show("El registro se a completado con éxito", 5000);
        }
    }
    
   
     public void cargarencargado(Encargado enca ){
      txfid.setText(enca.getIdencargado().toString());
      nomEnca.setText(enca.getNombre());
      apeEnca.setText(enca.getApellido());
      ciEnca.setText(enca.getCi().toString());
      telfEnca.setText(enca.getTelefono());
      
      al= true;
     
    }

    
}
