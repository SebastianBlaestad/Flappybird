package flappybird;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;

public class flappybirdApp extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FlappyBird");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("App.fxml"))));
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        Application.launch(args);
        
    }
}
