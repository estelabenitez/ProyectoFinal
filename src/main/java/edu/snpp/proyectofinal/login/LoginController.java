package edu.snpp.proyectofinal.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LoginController implements Initializable {
    

    @FXML
    private Label config;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXButton aceptar;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXPasswordField contrasena;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActiononAceptar(ActionEvent event) {
    }

    @FXML
    private void OnActiononCancelar(ActionEvent event) {
    }
}
