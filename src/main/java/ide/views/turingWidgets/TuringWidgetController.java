package ide.views.turingWidgets;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TuringWidgetController implements Initializable {

    @FXML HBox root;

    private TuringWidgetHelper helper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        helper = new TuringWidgetHelper(root);
        addCellRight(null);
    }

    @FXML
    private void addCellRight(ActionEvent event) {

        helper.addCellRight();
    }

    @FXML
    private void addCellLeft(ActionEvent event) {

        helper.addCellLeft();
    }

    @FXML
    private void deleteCellLeft(ActionEvent event) {

        helper.deleteCellLeft();
    }

    @FXML
    private void deleteCellRight(ActionEvent event) {

        helper.deleteCellRight();
    }

    @FXML
    private void moveHead(KeyEvent event) {

       helper.moveHead(event);
    }

    public TuringWidgetHelper getHelper() {
        return helper;
    }
}
