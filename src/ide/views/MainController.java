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

        try {
            ScrollPane urmWidget = FXMLLoader.load(getClass().getResource("../views/urmWidgets/mainUrm.fxml"));
            urmWidget.setPrefWidth(configWidget.getPrefWidth());
            configWidget.getChildren().add(urmWidget);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
