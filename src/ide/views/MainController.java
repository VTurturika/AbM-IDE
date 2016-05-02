package ide.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML Pane configWidget;
    @FXML Pane codeEditor;
    @FXML ComboBox<String> modeSwitcher;

    private CodeArea codeArea = new CodeArea();
    private CodeHighlighter highlighter = new CodeHighlighter();
    private ChangeListener<? super String> currentListener = (observable, oldValue, newValue) -> {};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        modeSwitcher.getItems().addAll("URM", "Turing", "Markov");
        modeSwitcher.setValue("URM");
        switchIde(null);

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

        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.setPrefWidth(codeEditor.getPrefWidth());
        codeArea.setPrefHeight(codeEditor.getPrefHeight());

        codeArea.richChanges().subscribe(change -> {

            if(codeEditor.getScene().getStylesheets().size() == 0) {
                codeEditor.getScene().getStylesheets().add("file://" + getClass().getResource("./styles.css").getPath());
            }
            //codeHighlighter.highlightUrmCode(codeArea);
        });
        codeEditor.getChildren().add(codeArea);

    }

    @FXML
    private void readFromFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) codeEditor.getScene().getWindow();

        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(stage);

        if(file != null) {

            String temp;
            codeArea.clear();

            try {
                FileReader fileReader = new FileReader(file.getAbsolutePath());
                BufferedReader reader = new BufferedReader(fileReader);

                while( (temp = reader.readLine() )!= null ) {
                     codeArea.appendText(temp + "\n");
                }
                fileReader.close();
                reader.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                codeArea.clear();
            }
        }
    }

    @FXML
    private void test(ActionEvent event) {

        codeEditor.getScene().getStylesheets().add("file://" + getClass().getResource("./styles.css").getPath());
        CodeHighlighter highlighter = new CodeHighlighter();
        highlighter.highlightMarkovCode(codeArea);
    }

    @FXML
    private void switchIde(ActionEvent event) {

        configWidget.getChildren().clear();
        codeArea.clear();

        ChangeListener<? super String> urmListener = (observable, oldValue, newValue) -> {
            highlighter.highlightUrmCode(codeArea);
        };

        ChangeListener<? super String> turingListener = (observable, oldValue, newValue) -> {
            highlighter.highlightTuringCode(codeArea);
        };

        ChangeListener<? super String> markovListener = (observable, oldValue, newValue) -> {
            highlighter.highlightMarkovCode(codeArea);
        };

        codeArea.textProperty().removeListener(currentListener);

        switch (modeSwitcher.getValue()) {
            case "URM":
                loadUrmWidget();
                currentListener = urmListener;
                break;
            case "Turing":
                loadTuringWidget();
                currentListener = turingListener;
                break;
            case "Markov":
                loadMarkovWidget();
                currentListener = markovListener;
                break;
        }

        codeArea.textProperty().addListener(currentListener);
    }
}
