/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author adria
 */
public class ControladorLogin {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUser;
    
    private ControladorPrincipal controladorPrincipal;

    @FXML
    void pulsar() throws IOException {
        
        String user = txtUser.getText();
        String password = txtPassword.getText();
        System.out.println("El user es:"+user+" y la contraseña es: "+password);
        
        Stage nose = (Stage) txtUser.getScene().getWindow(); //Coge el stage de esta escena
        
        cambiarVentana(nose);
        
        
    }
    
    void cambiarVentana(Stage nosequeponer) throws IOException{
        
        FXMLLoader zigueña = new FXMLLoader(getClass().getResource("/vista/VistaPrincipal.fxml"));
        Parent root = zigueña.load();
        
        Stage siu = new Stage();
        Scene mec = new Scene(root);
        siu.setTitle("Runninfy");
        siu.setScene(mec);
        
        // Agregar el icono
        // Agregar el icono
        Image logo = new Image(getClass().getResource("/img/LOGO.png").toExternalForm());
        siu.getIcons().add(logo);
        
        controladorPrincipal = zigueña.getController();
        controladorPrincipal.cambiarVentana(nosequeponer);
        
        siu.show();
        
        
    }

}
