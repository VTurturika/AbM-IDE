package ide.views.logsWidget;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogsWidgetController implements Initializable {

    @FXML TextArea console;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void clearConsole(ActionEvent event) {
        console.clear();
    }

    @FXML
    private void saveToFile(ActionEvent event) {

        if(console.getText().length() > 0) {

            FileChooser fileChooser = new FileChooser();
            Stage stage = (Stage) console.getScene().getWindow();

            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
            File file = fileChooser.showSaveDialog(stage);

            if(file != null) {
                writeToFile(console.getText(), file);
            }
        }
    }

    public void setLogs(String logs) {
        console.setText(logs);
    }

    public void clear() {
        clearConsole(null);
    }

    private void writeToFile(String content, File file){
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);

            fileWriter.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }

    }
}
