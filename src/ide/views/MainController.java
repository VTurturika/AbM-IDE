package ide.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML Pane configWidget;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadMarkovWidget();
    }

    private void loadUrmWidget() {

        try {
            HBox urmWidget = FXMLLoader.load(getClass().getResource("./urmWidgets/urmMain.fxml"));

            ScrollPane scrollPane = (ScrollPane) urmWidget.getChildren().get(1);
            scrollPane.setPrefWidth(configWidget.getPrefWidth() - 100);

            urmWidget.setPrefWidth(configWidget.getPrefWidth());
            configWidget.getChildren().add(urmWidget);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTuringWidget() {

        try {
            HBox turingWidget = FXMLLoader.load(getClass().getResource("./turingWidgets/turingTape.fxml"));

            HBox cellContainer = (HBox) ((ScrollPane)turingWidget.getChildren().get(1)).getContent();
            cellContainer.setPrefWidth(configWidget.getPrefWidth() - 200);

            ScrollPane scrollPane = (ScrollPane)turingWidget.getChildren().get(1);
            scrollPane.setPrefWidth(configWidget.getPrefWidth() - 190);
            turingWidget.setPrefWidth(configWidget.getPrefWidth());

            configWidget.getChildren().add(turingWidget);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMarkovWidget() {

        try {
            VBox markovWidget = FXMLLoader.load(getClass().getResource("./markovWidgets/markovWidget.fxml"));

            markovWidget.setPrefWidth(configWidget.getPrefWidth());
            configWidget.getChildren().add(markovWidget);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
