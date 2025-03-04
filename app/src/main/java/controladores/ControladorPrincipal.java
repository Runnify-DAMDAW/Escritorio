/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Carrera;

/**
 *
 * @author allae
 */
public class ControladorPrincipal implements Initializable{
    
    @FXML
    private GridPane gridTodasCarreras;

    @FXML
    private Label prueba;

    private Stage ventana;  

    public void cambiarVentana(Stage hola) {
        this.ventana = hola;
    }
    
    public static ObservableList<Carrera> getCarrerasList() {
        return FXCollections.observableArrayList(
                Arrays.asList(
                        new Carrera("Maratón de Granada", "Carrera anual en Granada", new Date(2025-1900, 4, 10), 42.195, "Granada, España", "37.1773, -3.5986", 30.00, 5000, "Abierta", "Maratón", ""),
                        new Carrera("Media Maratón de Sevilla", "Competencia de media maratón", new Date(2025-1900, 4, 10), 21.097, "Sevilla, España", "37.3886, -5.9823", 25.00, 4000, "Abierta", "Media Maratón", ""),
                        new Carrera("Carrera 10K Madrid", "Carrera urbana de 10K", new Date(2025-1900, 4, 10), 10.0, "Madrid, España", "40.4168, -3.7038", 20.00, 3000, "Cerrada", "10K", ""),
                        new Carrera("Ultra Trail Pirineos", "Carrera de montaña extrema", new Date(2025-1900, 4, 10), 100.0, "Pirineos, España", "42.6675, 0.5863", 50.00, 1000, "Abierta", "Ultra Trail", "")
                )
        );
    }

    public GridPane getGridTodasCarreras() {
        return gridTodasCarreras;
    }

    public Label getPrueba() {
        return prueba;
    }
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Carrera> carreras = getCarrerasList();
       
        System.out.println(carreras.toString());
 
    }
    
    
    
}
