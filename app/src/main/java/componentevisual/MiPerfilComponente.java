/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentevisual;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.User;


/**
 *
 * @author allae
 */
public class MiPerfilComponente extends HBox {

    private final ImageView imgPerfil;
    private final TextField txtNombre;
    private final TextField txtEmail;
    private final TextField txtSexo;
    private final TextField txtEdad;
    private final PasswordField txtContraseña; 
    private final Button btnEditar;
    private final Button btnAceptar;
    private final SimpleStringProperty btnEditarName = new SimpleStringProperty("Editar");
    private final SimpleStringProperty btnAceptarName = new SimpleStringProperty("Aceptar");

    
    
    public MiPerfilComponente() {
        super();
        setSpacing(15);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #f9f9f9; -fx-padding: 20;");

        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);

        imgPerfil = new ImageView();
        imgPerfil.setFitWidth(120);
        imgPerfil.setFitHeight(120);
        imgPerfil.setPreserveRatio(true);
        imgPerfil.setImage(new Image(""));

        txtNombre = crearTextField("Nombre");
        txtEmail = crearTextField("Email");
        txtSexo = crearTextField("Sexo");
        txtEdad = crearTextField("Edad");
        txtContraseña = new PasswordField();
        txtContraseña.setPromptText("Contraseña");
        txtContraseña.setEditable(false); 

        btnEditar = new Button(btnEditarName.get());
        btnEditar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAceptar = new Button(btnAceptarName.get());
        btnAceptar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAceptar.setDisable(true);

        btnEditar.setOnAction(event -> editarPerfil());
        btnAceptar.setOnAction(event -> aceptarCambios());

        vBox.getChildren().addAll(
            imgPerfil,
            txtNombre,
            txtEmail,
            txtSexo,
            txtEdad,
            txtContraseña,
            btnEditar,
            btnAceptar
        );

        getChildren().add(vBox);
    }
    
    private TextField crearTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setEditable(false); 
        textField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: #b3b3b3; -fx-border-radius: 5px;");
        textField.setPrefWidth(300);
        return textField;
    }

    private void editarPerfil() {
        txtNombre.setEditable(true);
        txtEmail.setEditable(true);
        txtSexo.setEditable(true);
        txtEdad.setEditable(true);
        txtContraseña.setEditable(true);
        btnEditar.setDisable(true); 
        btnAceptar.setDisable(false);
    }

    private void aceptarCambios() {
        if (txtContraseña.getText().isEmpty()) {
            showAlert("Error", "La contraseña es obligatoria.");
        } else {
            showAlert("Éxito", "Perfil actualizado correctamente.");
            btnEditar.setDisable(false); 
            btnAceptar.setDisable(true);
            desactivarCampos();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void desactivarCampos() {
        txtNombre.setEditable(false);
        txtEmail.setEditable(false);
        txtSexo.setEditable(false);
        txtEdad.setEditable(false);
        txtContraseña.setEditable(false);
    }
}