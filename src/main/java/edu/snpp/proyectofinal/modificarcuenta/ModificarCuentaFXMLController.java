/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.modificarcuenta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class ModificarCuentaFXMLController implements Initializable {

    @FXML
    private JFXButton aceptar;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField conactual;
    @FXML
    private Label config;
    @FXML
    private JFXPasswordField connueva;

    /**
     * Initializes the controller class.
     */
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
