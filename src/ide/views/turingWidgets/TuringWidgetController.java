package ide.views.turingWidgets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.TriangleMesh;

import java.net.URL;
import java.util.ResourceBundle;

public class TuringWidgetController implements Initializable {

    @FXML HBox cellContainer;
    private VBox currentCell;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCellRight(null);
        currentCell = setHead( (VBox) cellContainer.getChildren().get(0)) ;
    }

    @FXML
    private void addCellRight(ActionEvent event) {

        try {
            VBox cell = FXMLLoader.load(getClass().getResource("./cell.fxml"));
            ((TextField)cell.getChildren().get(0)).setText("R");

            cell.setOnMouseClicked(e -> {
                clearHead();
                currentCell = setHead(cell);
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
            });

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

    private VBox setHead(VBox cell) {

        cell.setAlignment(Pos.TOP_CENTER);
        cell.setPadding(new Insets(15,0,15,0));

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

        System.out.println("Pressed " + event.getCode());
    }

}
