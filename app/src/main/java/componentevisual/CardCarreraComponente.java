/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentevisual;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modelo.Carrera;

/**
 *
 * @author allae
 */
public class CardCarreraComponente extends VBox {

    private final ImageView imgCarrera;
    private final Label labelDesc;
    private final Label labelDate;
    private final Label labelDistanciaKm;
    private final Label labelCoordenadas;
    private final Label labelEntryFee;
    private final Label labelAvaibleSlots;
    private final Label labelStatus;
    private final Label labelCategory;
    private final Label labelNombre;
    private final Button btnMostrarMapa;
    private final WebView webViewMapa;
    private HBox imagenContainer;
    private boolean mostrandoMapa = false;

    public CardCarreraComponente() {
        super();


        setPrefWidth(350);
        setPrefHeight(450);


        imgCarrera = new ImageView();
        imgCarrera.setFitWidth(300);
        imgCarrera.setFitHeight(200);
        imgCarrera.setPreserveRatio(true);


        btnMostrarMapa = new Button("Ver Mapa");
        btnMostrarMapa.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        
        webViewMapa = new WebView();
        WebEngine webEngine = webViewMapa.getEngine();
        webViewMapa.setPrefSize(300, 200); 

        imagenContainer = new HBox(imgCarrera);
        imagenContainer.setAlignment(Pos.CENTER); 
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
        Font fontNombre = Font.font("Arial", FontWeight.BOLD, 12);
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


        setSpacing(10);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER_LEFT); 
        setStyle("-fx-background-color: #e6f3ff; -fx-border-color: #b3d9ff; -fx-border-width: 2px; -fx-border-radius: 10px;");


        getChildren().addAll(
            imagenContainer, 
            labelNombre,
            labelDesc,
            labelDate,
            labelDistanciaKm,
            labelCoordenadas,
            labelEntryFee,
            labelAvaibleSlots,
            labelStatus,
            labelCategory,
            btnMostrarMapa,
            webViewMapa
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


            btnMostrarMapa.setOnAction(event -> {
                if (mostrandoMapa) {

                    imagenContainer.getChildren().clear();
                    imagenContainer.getChildren().add(imgCarrera);
                    btnMostrarMapa.setText("Ver Mapa");
                    mostrandoMapa = false; 
                } else {

                    String coordenadas = carrera.getCoordinates(); 
                    String urlMapa = generarUrlMapa(coordenadas); 

                    imagenContainer.getChildren().clear();
                    imagenContainer.getChildren().add(webViewMapa);
                    webViewMapa.getEngine().load(urlMapa);
                    btnMostrarMapa.setText("Ver Imagen");
                    mostrandoMapa = true;
                }
            });
        }
    }


    private String generarUrlMapa(String coordenadas) {
        
        String[] coords = coordenadas.split(",");
        double lat = Double.parseDouble(coords[0].trim());
        double lng = Double.parseDouble(coords[1].trim());
        return "https://www.openstreetmap.org/export/embed.html?bbox=" 
                + (lng - 0.01) + "," 
                + (lat - 0.01) + "," 
                + (lng + 0.01) + "," 
                + (lat + 0.01) + "&layer=mapnik&marker=" + lat + "," + lng;
    }

}