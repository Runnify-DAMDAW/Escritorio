/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentevisual;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modelo.Carrera;

/**
 *
 * @author allae
 */
public class CardCarreraComponente extends VBox {

    // Componentes visuales
    private final ImageView imgCarrera;
    private final Label labelNombre;
    private final Label labelDesc;
    private final Label labelDate;
    private final Label labelDistanciaKm;
    private final Label labelCoordenadas;
    private final Label labelEntryFee;
    private final Label labelAvaibleSlots;
    private final Label labelStatus;
    private final Label labelCategory;
    
    public CardCarreraComponente() {
        super();
        imgCarrera = new ImageView();
        imgCarrera.setFitWidth(300); // Ancho de la imagen
        imgCarrera.setFitHeight(200); // Alto de la imagen
        imgCarrera.setPreserveRatio(true);
        
        labelNombre = new Label();
        labelDesc = new Label();
        labelDate = new Label();
        labelDistanciaKm = new Label();
        labelCoordenadas = new Label();
        labelEntryFee = new Label();
        labelAvaibleSlots = new Label();
        labelStatus = new Label();
        labelCategory = new Label();

        Font font = Font.font("Arial", FontWeight.BOLD, 12);
        Font fontNombre = Font.font("Arial", FontWeight.BOLD, 14);
        labelNombre.setFont(fontNombre);
        labelDesc.setFont(font);
        labelDate.setFont(font);
        labelDistanciaKm.setFont(font);
        labelCoordenadas.setFont(font);
        labelEntryFee.setFont(font);
        labelAvaibleSlots.setFont(font);
        labelStatus.setFont(font);
        labelCategory.setFont(font);

        labelNombre.setTextFill(Color.RED);
        labelDesc.setTextFill(Color.DARKBLUE);
        labelDate.setTextFill(Color.DARKBLUE);
        labelDistanciaKm.setTextFill(Color.DARKBLUE);
        labelCoordenadas.setTextFill(Color.DARKBLUE);
        labelEntryFee.setTextFill(Color.DARKBLUE);
        labelAvaibleSlots.setTextFill(Color.DARKBLUE);
        labelStatus.setTextFill(Color.DARKBLUE);
        labelCategory.setTextFill(Color.DARKBLUE);

        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setStyle("-fx-background-color: #e6f3ff; -fx-border-color: #b3d9ff; -fx-border-width: 2px; -fx-border-radius: 10px;");

        this.getChildren().addAll(
            imgCarrera,
            labelNombre,
            labelDesc,
            labelDate,
            labelDistanciaKm,
            labelCoordenadas,
            labelEntryFee,
            labelAvaibleSlots,
            labelStatus,
            labelCategory
        );
    }    
        public void mostrarDetallesCarrera(Carrera carrera) {
            
            if (carrera != null) {
                imgCarrera.setImage(new Image(carrera.getImage()));
                labelNombre.setText(carrera.getName());
                labelDesc.setText("Descripción: " + carrera.getDescription());
                labelDate.setText("Fecha: " + carrera.getDate().toString());
                labelDistanciaKm.setText("Distancia en KM: " + carrera.getDistance_km());
                labelCoordenadas.setText("Lugar: " + carrera.getLocation());
                labelEntryFee.setText("Entrada: " + carrera.getEntry_fee() + " €");
                labelAvaibleSlots.setText("Slots Totales: " + carrera.getAvailable_slots());
                labelStatus.setText("Estado: " + carrera.getStatus());
                labelCategory.setText("Categoría: " + carrera.getCategory());
        }
    }
        
}