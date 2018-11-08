package edu.snpp.proyectofinal;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    public static VentanaPrincipalController VENTANAPRINCIPAL;
    

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/fxml/VentanaPrincipal.fxml"));
        VENTANAPRINCIPAL = loader.getController();
        
        Scene scene = new Scene(root);
        
        
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

   

}
