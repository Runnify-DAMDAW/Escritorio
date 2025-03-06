package Runnify.DAMDAW;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaLogin.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Proyecto Runnify");

        // Agregar el icono
        Image logo = new Image(getClass().getResource("/img/LOGO.png").toExternalForm());
        primaryStage.getIcons().add(logo);
        
        primaryStage.show();
    }
}
