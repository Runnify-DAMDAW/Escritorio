package Runnify.DAMDAW;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
       
       FXMLLoader hola = new FXMLLoader(getClass().getResource("/vista/VistaLogin.fxml"));
       Parent adios = hola.load();
       

       Scene escena = new Scene(adios);
       primaryStage.setScene(escena);
       primaryStage.setTitle("Proyecto Runnify");
       primaryStage.show();
       
        
    }
   
}
