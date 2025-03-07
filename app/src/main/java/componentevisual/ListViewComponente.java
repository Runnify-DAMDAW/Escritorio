/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentevisual;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import modelo.Carrera;

/**
 *
 * @author allae
 */
public class ListViewComponente extends ListView<Carrera> {

    private final ObservableList<Carrera> carreras = FXCollections.observableArrayList();
    private final IntegerProperty tamañoLetras = new SimpleIntegerProperty(12); // Tamaño de letra inicial más pequeño

    public ListViewComponente() {
        super();
        this.setItems(carreras);
        this.setCellFactory(createCustomCellFactory());
        this.setStyle("-fx-background-color: #e6f3ff; -fx-border-color: #b3d9ff; -fx-border-width: 2px; -fx-border-radius: 5px;");

        tamañoLetras.addListener((obs, oldVal, newVal) -> refresh());
    }

    public void actualizarCarreras(List<Carrera> nuevasCarreras) {
        carreras.clear();
        carreras.addAll(nuevasCarreras);
    }

    private Callback<ListView<Carrera>, ListCell<Carrera>> createCustomCellFactory() {
        return param -> new ListCell<>() {
            @Override
            public void updateItem(Carrera carrera, boolean empty) {
                super.updateItem(carrera, empty);
                if (empty || carrera == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setText(carrera.getName() + " - " + carrera.getDate());
                    setFont(Font.font("Arial", FontWeight.BOLD, tamañoLetras.get())); 
                    setTextFill(Color.WHITE);
                    setStyle("-fx-background-color: #80b3ff; -fx-border-color: #4d94ff; -fx-border-width: 1px; -fx-border-radius: 3px; -fx-padding: 8px; -fx-background-radius: 5px;");

                    setOnMouseEntered((MouseEvent ) -> {
                        setStyle("-fx-background-color: #99c2ff; -fx-border-color: #66a3ff; -fx-border-width: 1px; -fx-border-radius: 3px; -fx-padding: 10px; -fx-background-radius: 5px;");
                        setFont(Font.font("Arial", FontWeight.BOLD, tamañoLetras.get() + 0.5)); 
                        setEffect(new DropShadow()); 
                    });

                    setOnMouseExited((MouseEvent) -> {
                        setStyle("-fx-background-color: #80b3ff; -fx-border-color: #4d94ff; -fx-border-width: 1px; -fx-border-radius: 3px; -fx-padding: 8px; -fx-background-radius: 5px;");
                        setFont(Font.font("Arial", FontWeight.BOLD, tamañoLetras.get())); 
                        setEffect(null); 
                    });
                }
            }
        };
    }

    public void setTamañoLetras(int tamaño) {
        this.tamañoLetras.set(tamaño);
    }

    public IntegerProperty tamañoLetrasProperty() {
        return tamañoLetras;
    }
}