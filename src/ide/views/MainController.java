package ide.views;

import ide.views.helpers.CodeAnalyzer;
import ide.views.helpers.CodeHighlighter;
import javafx.beans.value.ChangeListener;
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
    private CodeAnalyzer analyzer = new CodeAnalyzer();

    private ChangeListener<? super String> currentListener = (observable, oldValue, newValue) -> {};

    private ChangeListener<? super String> urmListener = (observable, oldValue, newValue) -> {
        highlighter.highlightUrmCode(codeArea);
        System.out.println(analyzer.analyzeCode(codeArea, "URM"));
    };

    private ChangeListener<? super String> turingListener = (observable, oldValue, newValue) -> {
        highlighter.highlightTuringCode(codeArea);
        System.out.println(analyzer.analyzeCode(codeArea, "Turing"));
    };

    private ChangeListener<? super String> markovListener = (observable, oldValue, newValue) -> {
        highlighter.highlightMarkovCode(codeArea);
        System.out.println(analyzer.analyzeCode(codeArea, "Markov"));
    };

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

        analyzer.analyzeCode(codeArea, modeSwitcher.getValue());

    }

    @FXML
    private void switchIde(ActionEvent event) {

        configWidget.getChildren().clear();
        codeArea.clear();

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
