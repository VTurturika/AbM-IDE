package ide.views.turingWidgets;

import ide.logic.alphabet.EmptySymbol;
import ide.logic.turing.TuringConfiguration;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

public class TuringWidgetHelper {

    private TuringConfiguration memoryTape;
    private HBox viewTape;
    private int indexOfCurrentCell = 0;
    private VBox currentCell;

    public TuringWidgetHelper(HBox root) {

        memoryTape = new TuringConfiguration();
        viewTape = (HBox) ((ScrollPane)root.getChildren().get(1)).getContent();
    }

    public void addCellRight() {
        try {
            VBox cell = FXMLLoader.load(getClass().getResource("./cell.fxml"));
            ((TextField)cell.getChildren().get(0)).setPromptText(EmptySymbol.getAsString());

           cell.setOnMouseClicked(e -> {
                clearHead();
                currentCell = setHead(cell);
                indexOfCurrentCell = viewTape.getChildren().indexOf(cell);
            });

            TextField cellValue = (TextField) cell.getChildren().get(0);

            cellValue.textProperty().addListener((observable, oldValue, newValue) -> {
                if(cellValue.getText().length() > 1) {
                    cellValue.setText(cellValue.getText().substring(0,1));
                }
            });

            viewTape.getChildren().add(cell);

            if(currentCell == null) currentCell = setHead( getCell(0) );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCellLeft() {

        try {
            VBox cell = FXMLLoader.load(getClass().getResource("./cell.fxml"));
            ((TextField)cell.getChildren().get(0)).setPromptText(EmptySymbol.getAsString());

           cell.setOnMouseClicked(e -> {
                clearHead();
                currentCell = setHead(cell);
                indexOfCurrentCell = viewTape.getChildren().indexOf(cell);
            });

            TextField cellValue = (TextField) cell.getChildren().get(0);
            cellValue.textProperty().addListener((observable, oldValue, newValue) -> {
                if(cellValue.getText().length() > 1) {
                    cellValue.setText(cellValue.getText().substring(0,1));
                }
            });

            indexOfCurrentCell++;
            viewTape.getChildren().add(0,cell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfCells() {
        return viewTape.getChildren().size();
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

    private VBox getCell(int index) {
        return (VBox) viewTape.getChildren().get(index);
    }

    private void clearHead() {

        if(currentCell != null) {

            currentCell.setAlignment(Pos.CENTER);
            currentCell.setPadding(new Insets(5,0,5,0));
            currentCell.getChildren().remove(0);
            currentCell.getChildren().remove(1);
        }
    }

    public void moveHead(KeyEvent event) {

        switch (event.getCode()) {
            case RIGHT:
                if(indexOfCurrentCell < viewTape.getChildren().size()-1) {
                    clearHead();
                    currentCell = setHead((VBox) viewTape.getChildren().get(++indexOfCurrentCell));
                }
                break;
            case LEFT:
                if(indexOfCurrentCell - 1 > -1) {
                    clearHead();
                    currentCell = setHead((VBox) viewTape.getChildren().get(--indexOfCurrentCell));
                }
                break;
        }
    }

    public void deleteCellLeft() {

        try {
            if(viewTape.getChildren().size() > 1) {

                if(indexOfCurrentCell == 0) {
                    moveHead(new KeyEvent(null, "", "", KeyCode.RIGHT, false, false, false, false));
                }
                viewTape.getChildren().remove(0);
                indexOfCurrentCell--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCellRight() {

        try {
            if(viewTape.getChildren().size() > 1) {

                if(indexOfCurrentCell == viewTape.getChildren().size()-1) {
                    moveHead(new KeyEvent(null, "", "", KeyCode.LEFT, false, false, false, false));
                }
                viewTape.getChildren().remove( viewTape.getChildren().size()-1 );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TuringConfiguration getMemoryTape() {
        return memoryTape;
    }

    public void setMemoryTape(TuringConfiguration memoryTape) {
        this.memoryTape = memoryTape;
    }

    public char getCellValue(int index) {
        if(index >= 0) {

            VBox cell = getCell(index);

            int cellValueIndex = (cell.getChildren().size() > 1 ) ? 1 : 0;

            TextField cellValue = (TextField) cell.getChildren().get(cellValueIndex);
            return cellValue.getText().equals("") ? EmptySymbol.get() : cellValue.getText().charAt(0);

        }
        else {
            return EmptySymbol.get();
        }
    }

    public void setCellValue(int index, char value) {

        if(index >= 0) {

            VBox cell = getCell(index);

            int cellValueIndex = (cell.getChildren().size() > 1 ) ? 1 : 0;

            TextField cellValue = (TextField) cell.getChildren().get(cellValueIndex);
            cellValue.setText(String.valueOf(value));
        }
    }

    public void updateMemoryTape() {

        int size = getNumberOfCells();
        String tape = "";

        for (int i = 0; i < size ; i++) {
            tape += getCellValue(i);
        }

        memoryTape.setTape(tape);
        for (int i = 0; i <indexOfCurrentCell  ; i++) {
            memoryTape.moveHeadRight();
        }
    }

    public void updateViewTape() {

        int size = memoryTape.getLenOfTape();
        int headOffset = memoryTape.getHeadOffset();

        memoryTape.moveHeadToBegin();
        for (int i = 0; i <size ; i++) {

            if(i >= getNumberOfCells() ) {
                addCellRight();
            }

            setCellValue(i, memoryTape.getSymbol());
            memoryTape.moveHeadRight();
        }

        memoryTape.moveHeadToBegin();
        for (int i = 0; i <headOffset ; i++) {
            memoryTape.moveHeadRight();
        }

        while(getNumberOfCells() > size) {
            deleteCellRight();
        }

        clearHead();
        currentCell = setHead( getCell(headOffset) );
    }

    public void setContainerWidth(HBox root, double width) {

        if(width > 0) {

            HBox cellContainer = (HBox) ((ScrollPane)root.getChildren().get(1)).getContent();
            cellContainer.setPrefWidth(width - 200);

            ScrollPane scrollPane = (ScrollPane)root.getChildren().get(1);
            scrollPane.setPrefWidth(width - 190);
        }
    }

}
