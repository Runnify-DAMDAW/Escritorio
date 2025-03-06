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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Carrera;
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
    private Label labelEntryFee;

    @FXML
    private Label labelStatus;
    
    //LABELS MIS CARRERAS
    
    @FXML
    private Label labelAvaibleSlotsMiCarrera;

    @FXML
    private Label labelCategoryMiCarrera;
    
    @FXML
    private VBox vBoxMostrarCarrerasMiCarrera;
    
    @FXML
    private HBox hBoxMisCarrerasMiCarrera;

    @FXML
    private Label labelCoordenadasMiCarrera;

    @FXML
    private Label labelDateMiCarrera;

    @FXML
    private Label labelDescMiCarrera;

    @FXML
    private Label labelDistanciaKmMiCarrera;

    @FXML
    private Label labelEntryFeeMiCarrera;

    @FXML
    private Label labelStatusMiCarrera;

    @FXML
    private ListView<Carrera> listViewCarreras;
    
    @FXML
    private ListView<Carrera> listViewMisCarreras;
    
    @FXML
    private Accordion acordeonRanking;
    
    @FXML
    private VBox vBoxRanking;
    
    //FILTROS
    
    @FXML
    private CheckBox chkFiltroInscrito;

    @FXML
    private ComboBox<String> comboBoxFiltroCategoria;

    @FXML
    private ComboBox<String> comboBoxFiltroCuota;

    @FXML
    private ComboBox<String> comboBoxFiltroEstado;
    
    @FXML
    private DatePicker txtFiltroFecha;

    @FXML
    private TextField txtFiltroLocalizacion;

    @FXML
    private TextField txtFiltroNombre;
    
    private ObservableList<Carrera> carreras = FXCollections.observableArrayList();
    private ObservableList<Carrera> carrerasFiltradas = FXCollections.observableArrayList();
    
    private Stage ventana;
    
    public void cambiarVentana(Stage hola) {
        this.ventana = hola;
    }

    @FXML
    void btnMisCarreras() {
        mostrarSeccion(false, false, true); 
    }

    @FXML
    void btnMostrarCarreras() { 
        mostrarSeccion(false, true, false); 
    }

    @FXML
    void btnRankigns() { 
        mostrarSeccion(true, false, false); 
    }

    private void mostrarSeccion(boolean ranking, boolean mostrarCarreras, boolean misCarreras) {
        vBoxRanking.setVisible(ranking);
        vBoxMostrarCarreras.setVisible(mostrarCarreras);
        hBoxMisCarreras.setVisible(misCarreras);
    }
    

    public static ObservableList<Carrera> getCarrerasList() {
        
        
        return FXCollections.observableArrayList(
                Arrays.asList(
                        
                )
        );
    }
    
        public static ObservableList<Carrera> getMisCarrerasList() {
        return FXCollections.observableArrayList(
                Arrays.asList(
                        
                )
        );
        
    }
    
    
    
    private void aplicarFiltros() {
        carrerasFiltradas.setAll(carreras.filtered(carrera ->
            (txtFiltroNombre.getText().isEmpty() || carrera.getName().toLowerCase().contains(txtFiltroNombre.getText().toLowerCase())) &&
            (txtFiltroLocalizacion.getText().isEmpty() || carrera.getLocation().toLowerCase().contains(txtFiltroLocalizacion.getText().toLowerCase())) &&
            (comboBoxFiltroCategoria.getValue() == null || "Categoría".equals(comboBoxFiltroCategoria.getValue()) || carrera.getCategory().equalsIgnoreCase(comboBoxFiltroCategoria.getValue())) &&
            (comboBoxFiltroCuota.getValue() == null || "Cuota".equals(comboBoxFiltroCuota.getValue()) || carrera.getEntry_fee() <= Double.parseDouble(comboBoxFiltroCuota.getValue())) &&
            (comboBoxFiltroEstado.getValue() == null || "Estado".equals(comboBoxFiltroEstado.getValue()) || carrera.getStatus().equalsIgnoreCase(comboBoxFiltroEstado.getValue()))
        ));
        actualizarLista(listViewCarreras, carrerasFiltradas);
    }

    private void actualizarLista(ListView<Carrera> lista, ObservableList<Carrera> carreras) {
        lista.getItems().setAll(carreras);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // PANEL TODAS LAS CARRERAS
        comboBoxFiltroCategoria.setItems(
                FXCollections.observableArrayList("Categoría", "Maratón", "Media Maratón", "10K")
        );
        
        comboBoxFiltroCuota.setItems(
                FXCollections.observableArrayList("Cuota", "20", "25", "30", "50")
        );
        
        comboBoxFiltroEstado.setItems(
                FXCollections.observableArrayList("Estado", "Abierta", "Cerrada")
        );

        listViewCarreras.setOnMouseClicked(value -> {
            System.out.println("Carrera" + listViewCarreras.getSelectionModel().getSelectedItem());
            Carrera carrera = listViewCarreras.getSelectionModel().getSelectedItem();
            // MOSTRAR FOTO (SUPONGO QUE SERA UN ENLACE A UNA FOTO DE INTERNET)
            //imgCarrera
            labelDesc.setText("Descripción: "+carrera.getDescription());
            labelDate.setText("Fecha: "+carrera.getDate().toString());
            labelDistanciaKm.setText("Distancia en KM: "+String.valueOf(carrera.getDistance_km()));
            labelCoordenadas.setText("Coordenadas: "+carrera.getCoordinates());
            labelEntryFee.setText("Entrada: " + String.valueOf(carrera.getEntry_fee()) + " € ");
            labelAvaibleSlots.setText("Slots Totales: " + String.valueOf(carrera.getAvailable_slots()));
            labelStatus.setText("Estado: " + carrera.getStatus());
            labelCategory.setText("Categoría: " + carrera.getCategory());
            
        });
        
        listViewMisCarreras.setOnMouseClicked( value -> {
            Carrera carrera = listViewMisCarreras.getSelectionModel().getSelectedItem();
            // MOSTRAR FOTO (SUPONGO QUE SERA UN ENLACE A UNA FOTO DE INTERNET)
            //imgCarrera
            labelDescMiCarrera.setText("Descripción: "+carrera.getDescription());
            labelDateMiCarrera.setText("Fecha: "+carrera.getDate().toString());
            labelDistanciaKmMiCarrera.setText("Distancia en KM: "+String.valueOf(carrera.getDistance_km()));
            labelCoordenadasMiCarrera.setText("Coordenadas: "+carrera.getCoordinates());
            labelEntryFeeMiCarrera.setText("Entrada: " + String.valueOf(carrera.getEntry_fee()) + " € ");
            labelAvaibleSlotsMiCarrera.setText("Slots Totales: " + String.valueOf(carrera.getAvailable_slots()));
            labelStatusMiCarrera.setText("Estado: " + carrera.getStatus());
            labelCategoryMiCarrera.setText("Categoría: " + carrera.getCategory());
        });
        
        carrerasFiltradas.setAll(carreras);
        listViewCarreras.setItems(FXCollections.observableArrayList());
        actualizarListaCarreras();
        
        txtFiltroNombre.textProperty().addListener((obs, old, nuevo) -> 
                aplicarFiltros()
        );
        txtFiltroLocalizacion.textProperty().addListener((obs, old, nuevo) ->
                aplicarFiltros()
        );
        txtFiltroFecha.valueProperty().addListener((obs, old, nuevo) -> 
                aplicarFiltros()
        );
        comboBoxFiltroCategoria.valueProperty().addListener((obs, old, nuevo) -> 
                aplicarFiltros()
        );
        comboBoxFiltroCuota.valueProperty().addListener((obs, old, nuevo) -> 
                aplicarFiltros()
        );
        comboBoxFiltroEstado.valueProperty().addListener((obs, old, nuevo) -> 
                aplicarFiltros()
        );
        chkFiltroInscrito.selectedProperty().addListener((obs, old, nuevo) -> 
                aplicarFiltros()
        );
                
        // PANEL MIS CARRERAS
        listViewMisCarreras.setItems(FXCollections.observableArrayList());
        actualizarListaMisCarreras();
               
               
    }
    

    private ImageView cargarIcono(String nombreArchivo) {
        Image imagen = new Image(getClass().getResource("/img/" + nombreArchivo).toExternalForm());
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(24);    
        imageView.setFitHeight(24);  
        imageView.setPreserveRatio(true); 
        return imageView;
    }

    public void consultarApi() throws IOException {
        
        String urlEndpoint = "http://192.168.70.198:8000/";

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlEndpoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiLeer leerCarreras = retrofit.create(ApiLeer.class);

        Call<List<Carrera>> call = leerCarreras.obtenerCarreras();
        Response<List<Carrera>> response = call.execute();
        

        if (response.isSuccessful() && response.body() != null) {
            List<Carrera> listaCarreras = response.body();
            System.out.println(listaCarreras);
            listViewCarreras.getItems().addAll(listaCarreras);
        } else {
            System.err.println("Error al obtener las carreras: " + response.message());
        }
    }

}

