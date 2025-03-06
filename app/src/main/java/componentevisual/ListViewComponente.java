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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

/**
 *
 * @author allae
 */
public class ListViewComponente extends ListView<String> {

    private final ObservableList<String> carreras = FXCollections.observableArrayList();
    private final IntegerProperty tamañoLetras = new SimpleIntegerProperty(14);

    public ListViewComponente() {
        super();
        this.setItems(carreras);
        this.setCellFactory(createCustomCellFactory());
        this.setStyle("-fx-background-color: #e8f5e9; -fx-border-color: #c8e6c9; -fx-border-width: 2px; -fx-border-radius: 5px;");

        tamañoLetras.addListener((obs, oldVal, newVal) -> refresh());
    }

    public void actualizarCarreras(List<String> nuevasCarreras) {
        carreras.clear();
        carreras.addAll(nuevasCarreras);
    }

    private Callback<ListView<String>, ListCell<String>> createCustomCellFactory() {
        return param -> new ListCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setText(item);
                    setFont(Font.font("Arial", FontWeight.BOLD, tamañoLetras.get())); // Fuente en negrita
                    setTextFill(Color.WHITE); // Texto en blanco para contrastar con el fondo
                    setStyle("-fx-background-color: #4caf50; -fx-border-color: #388e3c; -fx-border-width: 1px; -fx-border-radius: 3px; -fx-padding: 10px; -fx-background-radius: 5px;");
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