package ide.views.turingWidgets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TuringWidgetController implements Initializable {

    @FXML HBox cellContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCellRight(null);
    }

    @FXML
    private void addCellRight(ActionEvent event) {

        try {
            VBox cell = FXMLLoader.load(getClass().getResource("./cell.fxml"));
            ((TextField)cell.getChildren().get(0)).setText("R");

            cellContainer.getChildren().add(cell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addCellLeft(ActionEvent event) {

        try {
            VBox cell = FXMLLoader.load(getClass().getResource("./cell.fxml"));
            ((TextField)cell.getChildren().get(0)).setText("L");

            cellContainer.getChildren().add(0,cell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCellLeft(ActionEvent event) {

        try {
            if(cellContainer.getChildren().size() > 1) {
                cellContainer.getChildren().remove(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCellRight(ActionEvent event) {

        try {
            if(cellContainer.getChildren().size() > 1) {
                cellContainer.getChildren().remove( cellContainer.getChildren().size()-1 );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
