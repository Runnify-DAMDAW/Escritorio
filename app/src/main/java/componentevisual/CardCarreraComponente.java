/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentevisual;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controladores.ControladorPrincipal;
import interfaces.ApiInscribirse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modelo.Carrera;
import modelo.Inscripcion;
import modelo.RespuestaInscripcion;
import modelo.RunningParticipant;
import modelo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private final Label labelGender;
    private final Button btnMostrarMapa;
    private final Button btnInscribirse;
    private final Button btnDesapuntarse;
    private final WebView webViewMapa;
    private final HBox imagenContainer;
    private boolean mostrandoMapa = false;

    private final BooleanProperty cargando = new SimpleBooleanProperty(false);
    private final LoadingSpinner spinner = new LoadingSpinner();
    
    private Runnable inscribirseHandler;
    private Runnable desapuntarseHandler;
    
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

        btnInscribirse = new Button("Inscribirse");
        btnDesapuntarse = new Button("Desapuntarse");
        btnInscribirse.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnDesapuntarse.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        
        webViewMapa = new WebView();
        WebEngine webEngine = webViewMapa.getEngine();
        webViewMapa.setPrefSize(300, 200); 

        imagenContainer = new HBox();
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
        labelGender = new Label();

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
        labelGender.setFont(font);
                
        labelNombre.setTextFill(Color.RED);
        labelDesc.setTextFill(Color.DARKBLUE);
        labelDate.setTextFill(Color.DARKBLUE);
        labelDistanciaKm.setTextFill(Color.DARKBLUE);
        labelCoordenadas.setTextFill(Color.DARKBLUE);
        labelEntryFee.setTextFill(Color.DARKBLUE);
        labelAvaibleSlots.setTextFill(Color.DARKBLUE);
        labelStatus.setTextFill(Color.DARKBLUE);
        labelCategory.setTextFill(Color.DARKBLUE);
        labelGender.setTextFill(Color.DARKBLUE);

        setSpacing(10);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER_LEFT); 
        setStyle("-fx-background-color: #e6f3ff; -fx-border-color: #b3d9ff; -fx-border-width: 2px; -fx-border-radius: 10px;");

        HBox btns = new HBox(btnMostrarMapa, btnInscribirse, btnDesapuntarse);
        btns.alignmentProperty().setValue(Pos.CENTER);
        btns.spacingProperty().setValue(10);
        
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
            labelGender,
            btns,
            webViewMapa
        );
        
        btnInscribirse.setOnMouseClicked(event -> {
            if (inscribirseHandler != null) {
                inscribirseHandler.run();
            }
        });
        btnDesapuntarse.setOnMouseClicked(event -> {
            if (desapuntarseHandler != null) {
                desapuntarseHandler.run();
            }
        });
        
        spinner.isLoadingProperty.bind(cargando);
        
        cargando.addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                getChildren().add(spinner);
                setOpacity(0.4);
            } else {
                getChildren().remove(spinner);
                setOpacity(1);
            }
        });
        
    }
    
    public void setInscribirseHandler(Runnable inscribirseHandler) {
        this.inscribirseHandler = inscribirseHandler;
    }
    
    public void setDesapuntarseHandler(Runnable desapuntarseHandler){
        this.desapuntarseHandler = desapuntarseHandler;
    }    
    
    public void inscribirse(Carrera carrera, User usuario) {
        String baseURL = "http://192.168.1.41:8000/";

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiInscribirse apiInscribirse = retrofit.create(ApiInscribirse.class); // CORRECCIÓN
        Inscripcion inscripcionData = new Inscripcion(usuario.getId(), carrera.getId(), Long.valueOf(0));

        Call<Boolean> callInscripcion = apiInscribirse.inscribirse(inscripcionData);
        cargando.set(true);
        encolaInsertar(callInscripcion);
    }
    
    public void encolaInsertar(Call<Boolean> callInsercion) {
        callInsercion.enqueue(new Callback<Boolean>() {
            @Override    
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Platform.runLater(() -> {
                    if (response.isSuccessful() && response.body() != null) {
                        System.out.println("INSCRIPCION EXITOSA: ");
                        btnInscribirse.setDisable(true);
                        btnDesapuntarse.setDisable(false);
                        
                    } else {
                        System.out.println("Error en el insercion: " + response.message());
                        
                    }
                    cargando.set(false);
                });
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Platform.runLater(() -> {
                    System.out.println("Error de red: " + t.getMessage());
                    cargando.set(false);
                });
            }
        });
    }
    
    
    public void desapuntarse(Carrera carrera, User usuario) {
        String baseURL = "http://192.168.1.41:8000/";

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiInscribirse apiInscribirse = retrofit.create(ApiInscribirse.class);

        int idRunningParticipant = -1;
        for (RunningParticipant rp : carrera.getRunningParticipants()) {
            if (rp.getUser().getId() == usuario.getId()) {
               idRunningParticipant = rp.getId();
            }
        }
        
        if (idRunningParticipant == -1) {
            System.out.println("Error: No se encontró la inscripción del usuario en esta carrera.");
            return;
        }

        Call<Boolean> callDesapuntar = apiInscribirse.borrarInscripcion(idRunningParticipant);
        cargando.set(true);
        encolaEliminar(callDesapuntar);
    }

    
    public void encolaEliminar(Call<Boolean> callEliminar) {
        callEliminar.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Platform.runLater(() -> {
                    if (response.isSuccessful()) {
                        System.out.println("Desinscripción exitosa");
                        
                        btnInscribirse.setDisable(false);
                        btnDesapuntarse.setDisable(true);
                    } else {
                        System.out.println("Error al desapuntarse: " + response.message());
                    }
                    cargando.set(false);
                });
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Platform.runLater(() -> {
                    System.out.println("Error de red: " + t.getMessage());
                    cargando.set(false);
                });
            }
        });
    }


    public void mostrarDetallesCarrera(Carrera carrera, User usuario) {
        imagenContainer.getChildren().clear();
        imagenContainer.getChildren().add(imgCarrera);
        btnMostrarMapa.setText("Ver Mapa");
        mostrandoMapa = false; 
        labelStatus.setTextFill(Color.DARKBLUE);

        if (carrera != null) {
            
            //imgCarrera.setImage(new Image(getClass().getResource("/img/LOGO.png").toString()));
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
            
            btnInscribirse.setOnAction(e -> {
                inscribirse(carrera, usuario);
            });
            
            btnDesapuntarse.setOnAction(e -> {
                desapuntarse(carrera, usuario);
            });
            
            System.out.println(carrera.toString());
            if (carrera.getRunningParticipants() != null) {
                for (RunningParticipant rp : carrera.getRunningParticipants()) {
                    if (rp.getUser().getId() == usuario.getId()) {
                        btnInscribirse.setDisable(true);
                        btnDesapuntarse.setDisable(false);
                        return;
                    }
                }
            } else {
                System.out.println("Advertencia: La lista de participantes es null");
            }

            btnInscribirse.setDisable(false);
            btnDesapuntarse.setDisable(true);
            
            if(carrera.getStatus().equals("Closed") || carrera.getStatus().equals("Completed")){
                btnInscribirse.setDisable(true);
                btnDesapuntarse.setDisable(true);
                labelStatus.setStyle("-fx-text-fill: red;");

            }


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