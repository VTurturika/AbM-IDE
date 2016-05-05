package ide.views.markovWidgets;

import ide.logic.markov.MarkovConfiguration;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MarkovWidgetHelper {

    private VBox viewString;
    private MarkovConfiguration memoryString;

    public MarkovWidgetHelper(VBox root) {

        this.viewString = root;
        this.memoryString = new MarkovConfiguration();
    }

    public String getInputString() {

        HBox inputContainer = (HBox) viewString.getChildren().get(0);
        TextField inputString = (TextField) inputContainer.getChildren().get(1);
        return inputString.getText();
    }

    public String getOutputString() {
        HBox outputContainer = (HBox) viewString.getChildren().get(1);
        TextField outputString = (TextField) outputContainer.getChildren().get(1);
        return outputString.getText();
    }

    public void setInputString(String inputString) {

        HBox inputContainer = (HBox) viewString.getChildren().get(0);
        TextField stringWidget = (TextField) inputContainer.getChildren().get(1);
        stringWidget.setText(inputString);
    }

    public void setOutputString(String outputString) {
        HBox outputContainer = (HBox) viewString.getChildren().get(1);
        TextField stringWidget = (TextField) outputContainer.getChildren().get(1);
        stringWidget.setText(outputString);
    }

    public void updateMemoryString() {

        memoryString.setString( getInputString() );
    }

    public void updateViewString() {

        setOutputString( memoryString.getString() );
    }

    public MarkovConfiguration getMemoryString() {
        return memoryString;
    }

    public void setContainerWidth(VBox root, double width) {

        if (width > 0) {

            root.setPrefWidth(width);

            HBox inputContainer = (HBox) root.getChildren().get(0);
            HBox outputContainer = (HBox) root.getChildren().get(1);

            ((TextField)inputContainer.getChildren().get(1)).setPrefWidth(width - 150);
            ((TextField)outputContainer.getChildren().get(1)).setPrefWidth(width - 150);
        }
    }
}
