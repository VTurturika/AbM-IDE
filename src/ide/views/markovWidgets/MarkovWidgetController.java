package ide.views.markovWidgets;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MarkovWidgetController implements Initializable {

    @FXML VBox root;
    private MarkovWidgetHelper helper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helper = new MarkovWidgetHelper(root);
    }

    public MarkovWidgetHelper getHelper() {
        return helper;
    }
}
