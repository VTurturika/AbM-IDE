package ide.views.markovWidgets;

import ide.logic.markov.MarkovConfiguration;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MarkovWidgetHelper {

    private VBox viewString;
    private MarkovConfiguration memoryString;

    public MarkovWidgetHelper(VBox root) {

        this.viewString = root;
        this.memoryString = new MarkovConfiguration();
    }

    public String getInputString() {

        return ((TextField)viewString.getChildren().get(1)).getText();
    }

    public String getOutputString() {
        return ((TextField)viewString.getChildren().get(3)).getText();
    }

    public void setInputString(String inputString) {
        ((TextField)viewString.getChildren().get(1)).setText(inputString);
    }

    public void setOutputString(String outputString) {
        ((TextField)viewString.getChildren().get(3)).setText(outputString);
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
}
