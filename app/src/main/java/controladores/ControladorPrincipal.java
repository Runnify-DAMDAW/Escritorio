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
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            consultarApi();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
               
               
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

