/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import componentevisual.CardCarreraComponente;
import componentevisual.LoadingSpinner;
import interfaces.ApiEditarPerfil;
import interfaces.ApiInscribirse;
import interfaces.ApiLeer;
import interfaces.ApiLogin;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.CambiarContraseña;
import modelo.Carrera;
import modelo.EditarPerfil;
import modelo.Inscripcion;
import modelo.Login;
import modelo.RespuestaInscripcion;
import modelo.RespuestaLogin;
import modelo.RunningParticipant;
import modelo.RunningParticipantUser;
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
public class ControladorPrincipal implements Initializable{

    @FXML
    private BorderPane borderPanePrincipal;
    
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
    private VBox hBoxMisCarreras;

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
    private ComboBox<String> comboBoxFiltroCategoria;

    @FXML
    private ComboBox<String> comboBoxFiltroCuota;
    
    @FXML
    private ComboBox<String> comboBoxGeneroFiltro;
 
    @FXML
    private ComboBox<String> comboBoxFiltroEstado;
    
    @FXML
    private DatePicker txtFiltroFecha;

    @FXML
    private TextField txtFiltroLocalizacion;

    @FXML
    private TextField txtFiltroNombre;
    
    @FXML
    private TextField txtEdadMiPerfil;

    @FXML
    private TextField txtEmailMiperfil;
    
     @FXML
    private TextField txtNombreMiPerfil;

    @FXML
    private Label txtNombreMiPerfilTitulo;

    @FXML
    private TextField txtSexoMiPerfil;
    
    @FXML
    private ImageView imgMiPerfil;

    @FXML
    private Button btnEditarPerfil;

    @FXML
    private Button btnGuardarPerfil;
    
    private ObservableList<Carrera> carreras = FXCollections.observableArrayList();
    private ObservableList<Carrera> carrerasFiltradas = FXCollections.observableArrayList();
    private ObservableList<Carrera> misCarreras = FXCollections.observableArrayList();

    private User usuario;
    private Stage ventana;
    private String ip;
    
            
    
    @FXML
    private CardCarreraComponente cardCarrera;
    
    @FXML
    private CardCarreraComponente cardCarreraMiCarrera;
    
    private final BooleanProperty cargando = new SimpleBooleanProperty(false);
    private final LoadingSpinner spinner = new LoadingSpinner();
    
    @FXML
    private Label labelContraseña;

    @FXML
    private Label labelNuevaContraseña;

    @FXML
    private Label labelRespuesta;
    
    @FXML
    private Label labelRepetirContraseña;
    
    @FXML
    private TextField txtNuevaContraseña;

    @FXML
    private TextField txtRepetirNuevaContraseña;
    
    @FXML
    private TextField txtContraseña;
    
    @FXML
    private Button btnCambiarContraseña;
    
    @FXML 
    private Button btnGuardarContraseña;
    
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
    
    @FXML
    void recargarDatos() throws IOException {
        consultarMiUsuario();
        consultarApi();
        cargarMisCarreras();
        mostrarRankings();
    }
    
    private void mostrarSeccion(boolean ranking, boolean mostrarCarreras, boolean misCarreras, boolean miPerfil) {
        vBoxRanking.setVisible(ranking);
        vBoxMostrarCarreras.setVisible(mostrarCarreras);
        hBoxMisCarreras.setVisible(misCarreras);
        vBoxMiPerfil.setVisible(miPerfil);
    }
    

    private void configurarFiltros() {
        
        comboBoxFiltroCategoria.setItems(FXCollections.observableArrayList("Categoría", "Maratón", "Media maratón", "5K", "10K", "Obstáculos", "Relevos", "Sprint"));
        comboBoxFiltroCuota.setItems(FXCollections.observableArrayList("Cuota", "20", "25", "30", "50"));
        comboBoxFiltroEstado.setItems(FXCollections.observableArrayList("Estado", "Open", "Closed", "Completed"));
        comboBoxGeneroFiltro.setItems(FXCollections.observableArrayList("Género", "H", "M"));
                
        txtFiltroNombre.textProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        txtFiltroLocalizacion.textProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        txtFiltroFecha.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        comboBoxFiltroCategoria.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        comboBoxFiltroCuota.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        comboBoxFiltroEstado.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        comboBoxGeneroFiltro.valueProperty().addListener((obs, old, nuevo) -> aplicarFiltros());
        
    }
    
    
    private void aplicarFiltros() {
        carrerasFiltradas.setAll(carreras.filtered(carrera ->
            (txtFiltroNombre.getText().isEmpty() || carrera.getName().toLowerCase().contains(txtFiltroNombre.getText().toLowerCase())) &&
            (txtFiltroLocalizacion.getText().isEmpty() || carrera.getLocation().toLowerCase().contains(txtFiltroLocalizacion.getText().toLowerCase())) &&
            (comboBoxFiltroCategoria.getValue() == null || "Categoría".equals(comboBoxFiltroCategoria.getValue()) || carrera.getCategory().equalsIgnoreCase(comboBoxFiltroCategoria.getValue())) &&
            (comboBoxGeneroFiltro.getValue()==null || "Género".equals(comboBoxGeneroFiltro.getValue()) || carrera.getGender().equalsIgnoreCase(comboBoxGeneroFiltro.getValue())) &&
            (comboBoxFiltroCuota.getValue() == null || "Cuota".equals(comboBoxFiltroCuota.getValue()) || carrera.getEntry_fee() <= Double.parseDouble(comboBoxFiltroCuota.getValue())) &&
            (comboBoxFiltroEstado.getValue() == null || "Estado".equals(comboBoxFiltroEstado.getValue()) || carrera.getStatus().equalsIgnoreCase(comboBoxFiltroEstado.getValue()))
        ));
        actualizarLista(listViewCarreras, carrerasFiltradas);
    }

    private void actualizarLista(ListView<Carrera> lista, ObservableList<Carrera> carreras) {
        lista.getItems().clear();
        lista.getItems().setAll(carreras);
    }
    

    private void mostrarRankings() {
        acordeonRanking.getPanes().clear();

        for (Carrera carrera : carreras) {
            String titulo = carrera.getName() + " - " + carrera.getLocation();
            VBox content = new VBox(10);
            content.setPadding(new Insets(10));

            TitledPane pane = new TitledPane(titulo, content);

            pane.setOnMouseClicked(event -> {
                if (content.getChildren().isEmpty()) {
                    content.getChildren().addAll(
                        new Label("Participantes:")
                    );

                    List<RunningParticipant> participantesOrdenados = carrera.getRunningParticipants()
                        .stream()
                        .sorted(Comparator.comparing(RunningParticipant::getTime))
                        .toList();

                    int posicion = 1;
                    

                    for (RunningParticipant participante : participantesOrdenados) {
                        String tiempoTardado = formatDuration(Duration.ofMillis(participante.getTime()));

                        Label participanteLabel = new Label(
                            "#" + posicion++ + " " + participante.getUser().getName() + 
                            " | Tiempo: " + tiempoTardado + 
                            " | Dorsal: " + participante.getDorsal()
                        );
                        content.getChildren().add(participanteLabel);
                    }
                }
            });

            acordeonRanking.getPanes().add(pane);
        }
    }

    //MÉTODO PARA FORMATEAR LA DURACIÓN EN HORAS, MINUTOS Y SEGUNDOS
    private String formatDuration(Duration duration) {
        long horas = duration.toHours();
        long minutos = duration.toMinutesPart();
        long segundos = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo config.properties");
            }
            System.out.println("CARGADO IP");
            properties.load(input);
            ip = properties.getProperty("IP");
            System.out.println(ip);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar config.properties", e);
        }
        
        spinner.isLoadingProperty.bind(cargando);
        
        cargando.addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                borderPanePrincipal.getChildren().add(spinner);
            } else {
                borderPanePrincipal.getChildren().remove(spinner);
            }
        });
        
        misCarreras.addListener((ListChangeListener<? super Carrera>) (change -> {
           while (change.next()) {
               if (change.wasAdded() || change.wasRemoved()) {
                   actualizarLista(listViewMisCarreras, misCarreras);
               }
           }
        }));
        
        carreras.addListener((ListChangeListener<? super Carrera>) (change -> {
           while (change.next()) {
               if (change.wasAdded() || change.wasRemoved()) {
                   actualizarLista(listViewCarreras, carreras);
               }
           }
       }));
        setEditable(false);
        btnGuardarPerfil.setDisable(true);
        
        try{
            configurarFiltros();
            consultarApi();
            listViewCarreras.setOnMouseClicked(e ->{
                cardCarrera.mostrarDetallesCarrera(listViewCarreras.getSelectionModel().getSelectedItem(), usuario, ip);
                cardCarrera.setDesapuntarseHandler(() -> {
                    try {
                        recargarDatos();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                cardCarrera.setInscribirseHandler(()-> {try {
                    recargarDatos();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });
            listViewMisCarreras.setOnMouseClicked(e -> {
                cardCarreraMiCarrera.mostrarDetallesCarrera(listViewMisCarreras.getSelectionModel().getSelectedItem(), usuario, ip);
                cardCarreraMiCarrera.setDesapuntarseHandler(() -> {
                    try {
                        recargarDatos();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                cardCarreraMiCarrera.setInscribirseHandler(()-> {try {
                    recargarDatos();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });
            actualizarLista(listViewCarreras, carreras);
            mostrarRankings();
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
        
<<<<<<< HEAD
        String urlEndpoint = "http://192.168.70.91:8000/"; //
=======
        String urlEndpoint = ip; //
>>>>>>> e55b4d2826391e5d1e3fd7357d758a3f0493f775

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlEndpoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiLeer leerCarreras = retrofit.create(ApiLeer.class);
        cargando.set(true);
        Call<List<Carrera>> call = leerCarreras.obtenerCarreras();
        Response<List<Carrera>> response = call.execute();
        

        if (response.isSuccessful() && response.body() != null) {
            List<Carrera> listaCarreras = response.body();
            //System.out.println(listaCarreras);
            listViewCarreras.getItems().clear();
            carreras.setAll(listaCarreras);
            actualizarLista(listViewCarreras, carreras);
            cargando.set(false);
        } else {
            System.err.println("Error al obtener las carreras: " + response.message());
            cargando.set(false);
        }
    }
    
    public void cargarMisCarreras() {
        if (usuario != null && usuario.getRunningParticipants() != null) {
            misCarreras.clear();
            for (RunningParticipantUser rp : usuario.getRunningParticipants()) {
                int idCarrera = rp.getRunning().getId();
                for(Carrera carrera : carreras) {
                    if(idCarrera == carrera.getId()){
                        misCarreras.add(carrera);
                    }
                }

            }
        } else {
            System.out.println("Usuario o sus carreras son null");
        }

        System.out.println("MISCARRERAS: " + misCarreras);
        actualizarLista(listViewMisCarreras, misCarreras);
    }
    
    
    public void consultarMiUsuario() throws IOException {
        
<<<<<<< HEAD
        String urlEndpoint = "http://192.168.70.91:8000/"; //
=======
        String urlEndpoint = ip; 
>>>>>>> e55b4d2826391e5d1e3fd7357d758a3f0493f775

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlEndpoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiLeer leerCarreras = retrofit.create(ApiLeer.class);
        cargando.set(true);
        Call<User> call = leerCarreras.obtenerMiUsuario(usuario.getId());
        Response<User> response = call.execute();
        

        if (response.isSuccessful() && response.body() != null) {
            usuario = response.body();
            System.out.println("consulta" + usuario);
            cargando.set(false);
        } else {
            System.err.println("Error al obtener tu usuario: " + response.message());
            cargando.set(false);
        }
    }
    
    public void cargarDatosMiperfil(User user) throws IOException {
        if (user != null) {
            this.usuario = user;
            System.out.println("Usuario asignado: " + usuario);
            //Instant instant = user.getAge().toInstant();
            //LocalDate fechaNacimiento = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            //long edad = ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());
            txtEdadMiPerfil.setText(String.valueOf(user.getAge()));
            txtEmailMiperfil.setText(user.getEmail());
            txtNombreMiPerfil.setText(user.getName());
            txtNombreMiPerfilTitulo.setText(user.getName());
            txtSexoMiPerfil.setText(user.getGender());
            Image image = new Image(user.getImage());
            imgMiPerfil.setImage(image);
        } else {
            System.err.println("Error: usuario recibido es null");
        }
        
        cargarMisCarreras();

    }
    
    @FXML
    void editarPerfil() {
        setEditable(true);
        btnEditarPerfil.setDisable(true);
        btnGuardarPerfil.setDisable(false);
    }

    @FXML
    void guardarPerfil() {
<<<<<<< HEAD
        String baseURL = "http://192.168.70.91:8000/";
=======
        String baseURL = ip;
>>>>>>> e55b4d2826391e5d1e3fd7357d758a3f0493f775

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiEditarPerfil apiEditarPerfil = retrofit.create(ApiEditarPerfil.class);

        EditarPerfil editarPerfilData = new EditarPerfil(
                txtNombreMiPerfil.getText(),
                txtEmailMiperfil.getText(),
                Integer.parseInt(txtEdadMiPerfil.getText()),
                txtSexoMiPerfil.getText(),
                false, 
                null,
                null
        );

        Call<Boolean> callEditar = apiEditarPerfil.editarPerfil(usuario.getId(), editarPerfilData);

        cargando.set(true);

        encolaModificarPerfil(callEditar);
    }

    public void encolaModificarPerfil(Call<Boolean> callPerfil) {
        callPerfil.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Platform.runLater(() -> {
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body()) {
                            System.out.println("Perfil actualizado correctamente.");
                            labelRespuesta.setText("Perfil actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el perfil.");
                            labelRespuesta.setText("Error al actualizar el perfil.");
                        }
                    } else {
                        System.out.println("Error en la actualización: " + response.message());
                    }
                    cargando.set(false);
                    btnEditarPerfil.setDisable(false);
                    btnGuardarPerfil.setDisable(true);
                    setEditable(false);
                });
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Platform.runLater(() -> {
                    System.out.println("Error de red: " + t.getMessage());
                    cargando.set(false);
                    btnEditarPerfil.setDisable(false);
                    btnGuardarPerfil.setDisable(true);
                    setEditable(false);
                });
            }
        });
    }

    public void encolaModificarContraseña(Call<Boolean> callContraseña) {
        callContraseña.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Platform.runLater(() -> {
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body()) {
                            System.out.println("Contraseña actualizado correctamente.");
                            labelRespuesta.setText("Contraseña actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar Contraseña.");
                            labelRespuesta.setText("Error al actualizar Contraseña.");
                        }
                    } else {
                        System.out.println("Error en la actualización: " + response.message());
                    }
                    labelContraseña.setVisible(false); 
                    labelNuevaContraseña.setVisible(false);
                    labelRepetirContraseña.setVisible(false);
                    txtNuevaContraseña.setVisible(false);
                    txtRepetirNuevaContraseña.setVisible(false);
                    txtContraseña.setVisible(false);
                    btnCambiarContraseña.setVisible(true);
                    btnGuardarContraseña.setVisible(false);
                    btnGuardarContraseña.setDisable(true);
                    btnCambiarContraseña.setDisable(false);
                    setEditable(false);
                });
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Platform.runLater(() -> {
                    System.out.println("Error de red: " + t.getMessage());
                    labelContraseña.setVisible(false); 
                    labelNuevaContraseña.setVisible(false);
                    labelRepetirContraseña.setVisible(false);
                    txtNuevaContraseña.setVisible(false);
                    txtRepetirNuevaContraseña.setVisible(false);
                    txtContraseña.setVisible(false);
                    btnCambiarContraseña.setVisible(true);
                    btnGuardarContraseña.setVisible(false);
                    btnGuardarContraseña.setDisable(true);
                    btnCambiarContraseña.setDisable(false);
                    setEditable(false);
                });
            }
        });
    }
    
    private void setEditable(boolean estado) {
        txtNombreMiPerfil.setEditable(estado);
        txtEmailMiperfil.setEditable(estado);
        txtSexoMiPerfil.setEditable(estado);
        txtEdadMiPerfil.setEditable(estado);

        String color = estado ? "white" : "transparent";
        txtNombreMiPerfil.setStyle("-fx-background-color: " + color + ";");
        txtEmailMiperfil.setStyle("-fx-background-color: " + color + ";");
        txtSexoMiPerfil.setStyle("-fx-background-color: " + color + ";");
        txtEdadMiPerfil.setStyle("-fx-background-color: " + color + ";");
    }
    
    @FXML
    void cambiarContraseña() {
        labelContraseña.setVisible(true); 
        labelNuevaContraseña.setVisible(true);
        labelRepetirContraseña.setVisible(true);
        txtNuevaContraseña.setVisible(true);
        txtRepetirNuevaContraseña.setVisible(true);
        txtContraseña.setVisible(true);
        btnCambiarContraseña.setVisible(true);
        btnGuardarContraseña.setVisible(true);
        btnGuardarContraseña.setDisable(false);
        btnCambiarContraseña.setDisable(true);

 
    }
    
    @FXML 
    void guardarContraseña() {
<<<<<<< HEAD
        String baseURL = "http://192.168.70.91:8000/";
=======
        String baseURL = ip;
>>>>>>> e55b4d2826391e5d1e3fd7357d758a3f0493f775

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiEditarPerfil apiEditarPerfil = retrofit.create(ApiEditarPerfil.class);
        
        CambiarContraseña cambiarContraseñaData = new CambiarContraseña(txtContraseña.getText(),txtNuevaContraseña.getText());
        
        Call<Boolean> callContraseña = apiEditarPerfil.editarContraseña(usuario.getId(), cambiarContraseñaData);
        cargando.set(true);
        if (!txtNuevaContraseña.getText().equals(txtRepetirNuevaContraseña.getText())) {
            System.out.println("Error: Las contraseñas no coinciden.");
            return;
        }

        encolaModificarContraseña(callContraseña);
        
        
    }
    
    
    
    
}

