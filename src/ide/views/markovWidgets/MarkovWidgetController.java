package ide.views.markovWidgets;

import com.sun.imageio.plugins.common.I18N;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MarkovWidgetController implements Initializable {

    @FXML VBox root;
    @FXML Label inputLen;
    @FXML Label outputLen;
    @FXML TextField input;
    @FXML TextField output;
    @FXML Button clearInput;
    @FXML Button clearOutput;

    private MarkovWidgetHelper helper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helper = new MarkovWidgetHelper(root);

        input.textProperty().addListener((observable, oldValue, newValue) ->  {

            String result = newValue.length() > 0 ? String.valueOf(newValue.length()) : "-";
            inputLen.setText( result) ;
        });

        output.textProperty().addListener((observable, oldValue, newValue) ->  {

            String result = newValue.length() > 0 ? String.valueOf(newValue.length()) : "-";
            outputLen.setText( result) ;
        });

        clearInput.setOnAction(event -> input.clear());
        clearOutput.setOnAction(event -> output.clear());

    }

    public MarkovWidgetHelper getHelper() {
        return helper;
    }
}
