package ide.views.turingWidgets;

import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;

public class TuringWidgetController implements Initializable {

    @FXML HBox cellContainer;
    private VBox currentCell;
    private int indexOfCurrentCell;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCellRight(null);
        currentCell = setHead( (VBox) cellContainer.getChildren().get(0)) ;
        indexOfCurrentCell = 0;
    }

    @FXML
    private void addCellRight(ActionEvent event) {

        try {
            VBox cell = FXMLLoader.load(getClass().getResource("./cell.fxml"));
            ((TextField)cell.getChildren().get(0)).setText("R");

            cell.setOnMouseClicked(e -> {
                clearHead();
                currentCell = setHead(cell);
                indexOfCurrentCell = cellContainer.getChildren().indexOf(cell);
            });

            TextField cellValue = (TextField) cell.getChildren().get(0);
            cellValue.textProperty().addListener((observable, oldValue, newValue) -> {
                if(cellValue.getText().length() > 1) {
                    cellValue.setText(cellValue.getText().substring(0,1));
                }
            });

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

            cell.setOnMouseClicked(e -> {
                clearHead();
                currentCell = setHead(cell);
                indexOfCurrentCell = cellContainer.getChildren().indexOf(cell);
            });

            TextField cellValue = (TextField) cell.getChildren().get(0);
            cellValue.textProperty().addListener((observable, oldValue, newValue) -> {
                if(cellValue.getText().length() > 1) {
                    cellValue.setText(cellValue.getText().substring(0,1));
                }
            });

            indexOfCurrentCell++;
            cellContainer.getChildren().add(0,cell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCellLeft(ActionEvent event) {

        try {
            if(cellContainer.getChildren().size() > 1) {

                if(indexOfCurrentCell == 0) {
                    moveHead(new KeyEvent(null, "", "", KeyCode.RIGHT, false, false, false, false));
                }
                cellContainer.getChildren().remove(0);
                indexOfCurrentCell--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCellRight(ActionEvent event) {

        try {
            if(cellContainer.getChildren().size() > 1) {

                if(indexOfCurrentCell == cellContainer.getChildren().size()-1) {
                    moveHead(new KeyEvent(null, "", "", KeyCode.LEFT, false, false, false, false));
                }
                cellContainer.getChildren().remove( cellContainer.getChildren().size()-1 );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private VBox setHead(VBox cell) {

        cell.setAlignment(Pos.TOP_CENTER);
        cell.setPadding(new Insets(14,0,14,0));

        Polygon upperTriangle = new Polygon(10,0,30,15,10,30);
        upperTriangle.rotateProperty().set(90);
        cell.getChildren().add(0, upperTriangle);

        Polygon lowerTriangle = new Polygon(10,0,30,15,10,30);
        lowerTriangle.rotateProperty().set(270);
        cell.getChildren().add(lowerTriangle);

        return cell;
    }

    private void clearHead() {

        currentCell.setAlignment(Pos.CENTER);
        currentCell.setPadding(new Insets(5,0,5,0));
        currentCell.getChildren().remove(0);
        currentCell.getChildren().remove(1);
    }

    @FXML
    private void moveHead(KeyEvent event) {

        switch (event.getCode()) {
            case RIGHT:
                if(indexOfCurrentCell < cellContainer.getChildren().size()-1) {
                    clearHead();
                    currentCell = setHead((VBox)cellContainer.getChildren().get(++indexOfCurrentCell));
                }
                break;
            case LEFT:
                if(indexOfCurrentCell - 1 > -1) {
                   clearHead();
                    currentCell = setHead((VBox)cellContainer.getChildren().get(--indexOfCurrentCell));
                }
                break;
        }
    }
}
