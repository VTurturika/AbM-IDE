package ide.views.urmWidgets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class UrmWidgetController implements Initializable {

    @FXML HBox registersContainer;
    private int registersCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRegister(null);
    }

    @FXML
    private void addRegister(ActionEvent event) {
        try {
            VBox register = FXMLLoader.load(getClass().getResource("./register.fxml"));

            ((Label)register.getChildren().get(1)).setText(String.valueOf(registersCount++));

            TextField registerValue = (TextField) register.getChildren().get(0);

            TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
                String text = change.getText();
                if (text.matches("[0-9]*")) {
                    return change;
                }
                return null;
            });

            registerValue.setTextFormatter(textFormatter);
            registersContainer.getChildren().add(register);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteRegister(ActionEvent event) {
        if(registersCount > 1) {
            registersContainer.getChildren().remove(--registersCount);
        }
    }

    @FXML
    private void clearRegisters(ActionEvent event) {

        VBox register;

        for (int i = 0; i< registersCount; i++) {
              register = (VBox) registersContainer.getChildren().get(i);
            ((TextField)register.getChildren().get(0)).setText("0");
        }
    }

}
