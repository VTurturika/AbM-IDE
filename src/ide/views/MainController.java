package ide.views;

import ide.logic.interpreter.*;
import ide.logic.URM.*;
import ide.logic.turing.*;
import ide.logic.markov.*;

import ide.views.helpers.*;
import ide.views.urmWidgets.*;
import ide.views.turingWidgets.*;
import ide.views.markovWidgets.*;

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
    @FXML Button runButton;
    @FXML Button debugButton;

    private CodeArea codeArea = new CodeArea();
    private CodeHighlighter highlighter = new CodeHighlighter();
    private CodeAnalyzer analyzer = new CodeAnalyzer();

    private UrmWidgetHelper urmHelper;
    private TuringWidgetHelper turingHelper;
    private MarkovWidgetHelper markovHelper;

    private Interpreter interpreter = null;
    private Program program = null;
    private boolean canExecute = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        modeSwitcher.getItems().addAll("URM", "Turing", "Markov");
        modeSwitcher.setValue("URM");
        switchIde(null);
        loadCodeEditor();
    }

    private void loadUrmWidget() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("./urmWidgets/urmMain.fxml"));

            HBox urmWidget = loader.load();
            urmHelper = ((UrmWidgetController)loader.getController()).getHelper();

            urmHelper.setContainerWidth(urmWidget, configWidget.getPrefWidth() - 100);
            urmWidget.setPrefWidth(configWidget.getPrefWidth());

            configWidget.getChildren().add(urmWidget);

            interpreter = new UrmInterpreter();
            interpreter.setInput( urmHelper.getMemoryRegisters() );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTuringWidget() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("./turingWidgets/turingTape.fxml"));

            HBox turingWidget = loader.load();
            turingHelper = ((TuringWidgetController)loader.getController()).getHelper();

            turingHelper.setContainerWidth(turingWidget, configWidget.getPrefWidth());
            turingWidget.setPrefWidth(configWidget.getPrefWidth());

            configWidget.getChildren().add(turingWidget);

            interpreter = new TuringInterpreter();
            interpreter.setInput( turingHelper.getMemoryTape() );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMarkovWidget() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("./markovWidgets/markovWidget.fxml"));

            VBox markovWidget = loader.load();
            markovHelper = ((MarkovWidgetController)loader.getController()).getHelper();

            markovWidget.setPrefWidth(configWidget.getPrefWidth());
            configWidget.getChildren().add(markovWidget);

            interpreter = new MarkovInterpreter();
            interpreter.setInput( markovHelper.getMemoryString() );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCodeEditor() {

        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.setPrefWidth(codeEditor.getPrefWidth());
        codeArea.setPrefHeight(codeEditor.getPrefHeight());

        codeArea.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if( newValue - oldValue > 1 ) {
                highlighter.highlightAllCode(codeArea, modeSwitcher.getValue());
                canExecute = analyzer.analyzeAllCode(codeArea, modeSwitcher.getValue()) != null;
            }
            else if( newValue - oldValue <= 1 ) {
                highlighter.highlightLine(codeArea, modeSwitcher.getValue());
                canExecute  = analyzer.analyzeCode(codeArea, modeSwitcher.getValue());
            }
            controlButtons();
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

                String buffer = "";

                while( (temp = reader.readLine() )!= null ) {
                    buffer += temp + "\n";
                }
                fileReader.close();
                reader.close();

                codeArea.appendText(buffer);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                codeArea.clear();
            }
        }
    }

    @FXML
    private void run(ActionEvent event) {

        if(canExecute) {

            program = analyzer.analyzeAllCode(codeArea, modeSwitcher.getValue());
            if(program != null) {

                interpreter.loadProgram(program);
                updateInput();

                interpreter.runProgram();

                updateOutput();
            }
            else {
                System.out.println("wrong program");
            }
        }
    }

    @FXML
    private void switchIde(ActionEvent event) {

        configWidget.getChildren().clear();
        codeArea.clear();
        canExecute = false;

        switch (modeSwitcher.getValue()) {
            case "URM":
                loadUrmWidget();
                break;
            case "Turing":
                loadTuringWidget();
                break;
            case "Markov":
                loadMarkovWidget();
                break;
        }
    }

    private void controlButtons() {
        if(canExecute) {
            runButton.setDisable(false);
            debugButton.setDisable(false);
        }
        else {
            runButton.setDisable(true);
            debugButton.setDisable(true);
        }
    }

    private void updateInput() {

        switch(modeSwitcher.getValue()) {
            case "URM":
                urmHelper.updateMemoryRegisters();
                break;
            case "Turing":
                turingHelper.updateMemoryTape();
                break;
            case "Markov":
                markovHelper.updateMemoryString();
                break;
        }
    }

    private void updateOutput() {

        switch(modeSwitcher.getValue()) {
            case "URM":
                urmHelper.updateViewRegisters();
                break;
            case "Turing":
                turingHelper.updateViewTape();
                break;
            case "Markov":
                markovHelper.updateViewString();
                break;
        }
    }
}
