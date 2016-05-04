package ide.views.urmWidgets;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class UrmWidgetController implements Initializable {

    @FXML HBox root;
    private UrmWidgetHelper helper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helper = new UrmWidgetHelper(root);
        addRegister(null);
    }

    @FXML
    private void addRegister(ActionEvent event) {

        helper.addRegister();
    }

    @FXML
    private void deleteRegister(ActionEvent event) {
        helper.deleteRegister();
    }

    @FXML
    private void clearRegisters(ActionEvent event) {

        for(int i=0; i<helper.getNumberOfRegisters(); i++) {
            helper.setRegisterValue(i,0);
        }
    }

    public UrmWidgetHelper getHelper() {
        return helper;
    }
}
