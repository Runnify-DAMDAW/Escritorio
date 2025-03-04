/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author adria
 */
public class ControladorLogin {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    void pulsar( ) {
        
        String user = txtUser.getText();
        String password = txtPassword.getText();
        System.out.println("El user es:"+user+" y la contraseña es: "+password);
        
    }

}
