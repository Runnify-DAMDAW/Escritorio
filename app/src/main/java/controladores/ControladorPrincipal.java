/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import componentevisual.CardCarreraComponente;
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
import modelo.User;
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
    
    @FXML
    private VBox vBoxMiPerfil;
    
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
    
    @FXML
    private Label txtEdadMiPerfil;

    @FXML
    private Label txtEmailMiperfil;
    
     @FXML
    private Label txtNombreMiPerfil;

    @FXML
    private Label txtNombreMiPerfilTitulo;

    @FXML
    private Label txtSexoMiPerfil;

    
    private ObservableList<Carrera> carreras = FXCollections.observableArrayList();
    private ObservableList<Carrera> carrerasFiltradas = FXCollections.observableArrayList();
    
    private Stage ventana;
    
    
    @FXML
    private CardCarreraComponente cardCarrera;
    
    public void cambiarVentana(Stage hola) {
        this.ventana = hola;
    }

    @FXML
    void btnMisCarreras() {
        mostrarSeccion(false, false, true, false); 
    }

    @FXML
    void btnMostrarCarreras() { 
        mostrarSeccion(false, true, false, false); 
    }

    @FXML
    void btnRankigns() { 
        mostrarSeccion(true, false, false, false); 
    }
    
    @FXML
    void btnMiPerfil() {
        mostrarSeccion(false, false, false, true); 
    }

    private void mostrarSeccion(boolean ranking, boolean mostrarCarreras, boolean misCarreras, boolean miPerfil) {
        vBoxRanking.setVisible(ranking);
        vBoxMostrarCarreras.setVisible(mostrarCarreras);
        hBoxMisCarreras.setVisible(misCarreras);
        vBoxMiPerfil.setVisible(miPerfil);
    }
    

    private void configurarFiltros() {
        
        comboBoxFiltroCategoria.setItems(FXCollections.observableArrayList("Categoría", "Maratón", "Media Maratón", "10K"));
        comboBoxFiltroCuota.setItems(FXCollections.observableArrayList("Cuota", "20", "25", "30", "50"));
        comboBoxFiltroEstado.setItems(FXCollections.observableArrayList("Estado", "Abierta", "Cerrada"));

        txtFiltroNombre.textProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        txtFiltroLocalizacion.textProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        txtFiltroFecha.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        comboBoxFiltroCategoria.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        comboBoxFiltroCuota.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        comboBoxFiltroEstado.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        chkFiltroInscrito.selectedProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        
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
        
        try{
            configurarFiltros();
            consultarApi();
            listViewCarreras.setOnMouseClicked(e -> cardCarrera.mostrarDetallesCarrera(listViewCarreras.getSelectionModel().getSelectedItem()));
            listViewMisCarreras.setOnMouseClicked(e -> cardCarrera.mostrarDetallesCarrera(listViewMisCarreras.getSelectionModel().getSelectedItem()));
            actualizarLista(listViewCarreras, carreras);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
               
               
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
        
        String urlEndpoint = "http://localhost:83/academia/"; //

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlEndpoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiLeer leerCarreras = retrofit.create(ApiLeer.class);

        Call<List<Carrera>> call = leerCarreras.obtenerCarrerasLocal();
        Response<List<Carrera>> response = call.execute();
        

        if (response.isSuccessful() && response.body() != null) {
            List<Carrera> listaCarreras = response.body();
            System.out.println(listaCarreras);
            listViewCarreras.getItems().clear();
            carreras.setAll(listaCarreras);
            actualizarLista(listViewCarreras, carreras);
        } else {
            System.err.println("Error al obtener las carreras: " + response.message());
        }
    }
    
    public void setDatosMiperfil(User user) {
        System.out.println(user);
        txtEdadMiPerfil.setText(user.getName());
        txtEmailMiperfil.setText(user.getEmail());
        txtNombreMiPerfil.setText(user.getName());
        txtNombreMiPerfilTitulo.setText(user.getName());
        txtSexoMiPerfil.setText("M");
    }

}

