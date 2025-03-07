/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.ApiLogin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Login;
import modelo.RespuestaLogin;
import modelo.ValidadoresLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author adria
 */
public class ControladorLogin {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUser;
    
    private ControladorPrincipal controladorPrincipal;

    private boolean operacionExitosa;
    
    private Stage nose;
    
    @FXML
    void pulsar() throws IOException {
        String user = txtUser.getText();
        String password = txtPassword.getText();
        System.out.println("El usuario es: " + user + " y la contraseña es: " + password);
        
        
        // Validación: el campo del email no debe estar vacío
        if (!ValidadoresLogin.noEstaVacio(user)) {
            mostrarAlerta("Error de Validación", "El campo User no puede estar vacío.");
            return;
        }


        // Validación: el campo de contraseña no debe estar vacío
        if (!ValidadoresLogin.noEstaVacio(password)) {
            mostrarAlerta("Error de Validación", "El campo contraseña no puede estar vacío.");
            return;
        }
        
        

        String baseURL = "http://192.168.70.82:8000/";

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiLogin apiLogin = retrofit.create(ApiLogin.class); // CORRECCIÓN
        Login loginData = new Login(user, password);

        Call<RespuestaLogin> callLogin = apiLogin.login(loginData);

        encolaInsertar(callLogin);
        //nose = (Stage) txtUser.getScene().getWindow();
        //cambiarVentana(nose);
        
    }

    public void encolaInsertar(Call<RespuestaLogin> callLogin) {
        callLogin.enqueue(new Callback<RespuestaLogin>() {
            @Override    
            public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                Platform.runLater(() -> {
                    if (response.isSuccessful() && response.body() != null) {
                        System.out.println("Login exitoso: " + response.body().getUser().getName());
                        operacionExitosa = true;
                        Stage nose = (Stage) txtUser.getScene().getWindow();
                        try {
                            cambiarVentana(nose);

                            controladorPrincipal.cargarDatosMiperfil(response.body().getUser());
                            nose.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("Error en el login: " + response.message());
                    }
                });
            }

            @Override
            public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                Platform.runLater(() -> {
                    System.out.println("Error de red: " + t.getMessage());
                });
            }
        });
    }


    
    

    
    void cambiarVentana(Stage nosequeponer) throws IOException{
        
        FXMLLoader zigueña = new FXMLLoader(getClass().getResource("/vista/VistaPrincipal.fxml"));
        Parent root = zigueña.load();
        
        Stage siu = new Stage();
        Scene mec = new Scene(root);
        siu.setTitle("Runninfy");
        siu.setScene(mec);
        
        // Agregar el icono
        // Agregar el icono
        Image logo = new Image(getClass().getResource("/img/LOGO.png").toExternalForm());
        siu.getIcons().add(logo);
        
        controladorPrincipal = zigueña.getController();
        controladorPrincipal.cambiarVentana(nosequeponer);
        
        siu.show();
        //nose.close();
        
        
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        
        // Obtén la ventana de la alerta y añade el icono
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/LOGO.png")));
    
        alerta.showAndWait();
    }

}
