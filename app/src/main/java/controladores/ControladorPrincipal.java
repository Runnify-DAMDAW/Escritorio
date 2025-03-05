/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.ApiLeer;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Carrera;
import modelo.CarrerasRunning;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author allae
 */
public class ControladorPrincipal implements Initializable{

    @FXML
    private ImageView imgCarrera;

    @FXML
    private ImageView imgLogo;
    
    @FXML
    private Button btnMisCarreras;

    @FXML
    private Button btnMostrarCarreras;

    @FXML
    private Button btnRankings;

    @FXML
    private Label labelAvaibleSlots;

    @FXML
    private Label labelCategory;
    
    @FXML
    private VBox vBoxMostrarCarreras;
    
    @FXML
    private HBox hBoxMisCarreras;


    @FXML
    private Label labelCoordenadas;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelDesc;

    @FXML
    private Label labelDistanciaKm;

    @FXML
    private Label labelEntryFree;

    @FXML
    private Label labelStatus;

    @FXML
    private ListView<String> listViewCarreras;
    
    @FXML
    private Accordion acordeonRanking;
    
    @FXML
    private VBox vBoxRanking;
    
    private Stage ventana;
    
    public void cambiarVentana(Stage hola) {
        this.ventana = hola;
    }

    @FXML
    void btnMisCarreras() {
        vBoxRanking.visibleProperty().set(false);
        vBoxMostrarCarreras.visibleProperty().set(false);
        hBoxMisCarreras.visibleProperty().set(true);
    }

    @FXML
    void btnMostrarCarreras() {
        vBoxRanking.visibleProperty().set(false);
        vBoxMostrarCarreras.visibleProperty().set(true);
        hBoxMisCarreras.visibleProperty().set(false);
    }

    @FXML
    void btnRankigns() {
        vBoxRanking.visibleProperty().set(true);
        vBoxMostrarCarreras.visibleProperty().set(false);
        hBoxMisCarreras.visibleProperty().set(false);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Carrera> carreras = getCarrerasList();
        for (Carrera carrera : carreras) {
            String titulo = carrera.getName() + " - " + carrera.getLocation();


            VBox content = new VBox();
            TitledPane pane = new TitledPane(titulo, content);
            pane.setOnMouseClicked(values -> {
                content.getChildren().clear();
                content.getChildren().addAll(
                    new Label("Descripción: " + carrera.getDescription()),
                    new Label("Fecha: " + carrera.getDate()),
                    new Label("Distancia: " + carrera.getDistance_km() + " km"),
                    new Label("Precio: " + carrera.getEntry_fee() + "€"),
                    new Label("Estado: " + carrera.getStatus()),
                    new Label("Tipo: " + carrera.getCategory())
                );
                System.out.println("Carga datos");
            });
            acordeonRanking.getPanes().add(pane);
            
        }
               configurarBoton(btnMostrarCarreras, "carreras.png");
               configurarBoton(btnMisCarreras, "mis_carreras.png");
               configurarBoton(btnRankings, "rankings.png");
    }
    
    private void configurarBoton(Button boton, String nombreIcono) {
        boton.setGraphic(cargarIcono(nombreIcono));
        boton.setContentDisplay(ContentDisplay.LEFT);    
        boton.setGraphicTextGap(10);                    
        boton.setPrefWidth(200);                        
        boton.setAlignment(Pos.CENTER_LEFT);             
    }

    private ImageView cargarIcono(String nombreArchivo) {
        Image imagen = new Image(getClass().getResource("/img/" + nombreArchivo).toExternalForm());
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(24);    
        imageView.setFitHeight(24);  
        imageView.setPreserveRatio(true); 
        return imageView;
    }

    public void consultarApi(String url) throws IOException{
       
        
        String urlEndpoint = url;

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlEndpoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiLeer leerCarreras = retrofit.create(ApiLeer.class);


        Call<CarrerasRunning> call = leerCarreras.obtenerCarreras();
        Response<CarrerasRunning> hola = call.execute();

        CarrerasRunning listaCarreras = hola.body();

        //Poner lo que devuelva listaCarreras para log

        
        
    }

}

