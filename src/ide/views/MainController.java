package ide.views;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleSpansBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML Pane configWidget;
    @FXML Pane codeEditor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadCodeEditor();
        loadUrmWidget();
    }

    private void loadUrmWidget() {

        try {
            HBox urmWidget = FXMLLoader.load(getClass().getResource("./urmWidgets/urmMain.fxml"));

            ScrollPane scrollPane = (ScrollPane) urmWidget.getChildren().get(1);
            scrollPane.setPrefWidth(configWidget.getPrefWidth() - 100);

            urmWidget.setPrefWidth(configWidget.getPrefWidth());
            configWidget.getChildren().add(urmWidget);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTuringWidget() {

        try {
            HBox turingWidget = FXMLLoader.load(getClass().getResource("./turingWidgets/turingTape.fxml"));

            HBox cellContainer = (HBox) ((ScrollPane)turingWidget.getChildren().get(1)).getContent();
            cellContainer.setPrefWidth(configWidget.getPrefWidth() - 200);

            ScrollPane scrollPane = (ScrollPane)turingWidget.getChildren().get(1);
            scrollPane.setPrefWidth(configWidget.getPrefWidth() - 190);
            turingWidget.setPrefWidth(configWidget.getPrefWidth());

            configWidget.getChildren().add(turingWidget);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMarkovWidget() {

        try {
            VBox markovWidget = FXMLLoader.load(getClass().getResource("./markovWidgets/markovWidget.fxml"));

            markovWidget.setPrefWidth(configWidget.getPrefWidth());
            configWidget.getChildren().add(markovWidget);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCodeEditor() {

        CodeArea codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        codeArea.setPrefWidth(codeEditor.getPrefWidth());
        codeArea.setPrefHeight(codeEditor.getPrefHeight());

        //codeArea.richChanges().subscribe(change -> System.out.println(codeArea.getCaretPosition()));
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        codeEditor.getChildren().add(codeArea);
    }

    @FXML
    private void readFromFile(ActionEvent event) {

        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) codeEditor.getScene().getWindow();
        CodeArea codeArea = (CodeArea) codeEditor.getChildren().get(0);


        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {

            String temp;
            codeArea.clear();

            try {
                FileReader fileReader = new FileReader(file.getAbsolutePath());
                BufferedReader reader = new BufferedReader(fileReader);
                while( (temp = reader.readLine() )!= null ) {

                    if(!temp.equals("")) {
                        codeArea.appendText(temp + "\n");
                    }
                }
                fileReader.close();
                reader.close();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                codeArea.clear();
            }
        }
    }
}
