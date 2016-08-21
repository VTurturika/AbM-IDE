package ide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Victor Turturika on 27.04.2016.
 */
public class App extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox mainLayout = FXMLLoader.load(getClass().getResource("/fxml/mainLayout.fxml"));
        Scene mainScene = new Scene(mainLayout, mainLayout.getPrefWidth(), mainLayout.getPrefHeight());
        mainScene.getStylesheets().add("/css/styles.css");
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("AbM IDE - Abstract Machines IDE");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
