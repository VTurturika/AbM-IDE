package ide.views.urmWidgets;

import ide.logic.URM.UrmConfiguration;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UrmWidgetHelper {

    private UrmConfiguration memoryRegisters;
    private HBox viewRegisters;

    public UrmWidgetHelper(HBox root) {

        this.memoryRegisters = new UrmConfiguration();
        this.viewRegisters = (HBox) ((ScrollPane)root.getChildren().get(1)).getContent();
    }

    public UrmConfiguration getMemoryRegisters() {
        return memoryRegisters;
    }

    public void setMemoryRegisters(UrmConfiguration memoryRegisters) {
        this.memoryRegisters = memoryRegisters;
    }

    public int getRegisterValue(int index){

       VBox register = (VBox) viewRegisters.getChildren().get(index);
       String valueAsString = ((TextField)register.getChildren().get(0)).getText();
       if(valueAsString.equals("")) {
           return 0;
       }
       else {
           return Integer.parseInt(valueAsString);
       }
    }

    public void setRegisterValue(int index, int value) {

        VBox register = (VBox) viewRegisters.getChildren().get(index);
        TextField registerValue = (TextField)register.getChildren().get(0);

        if(value == -1) {
            registerValue.setText( "" );
        }
        else {
            registerValue.setText( String.valueOf(value) );
        }
    }

    public void addRegister() {
        try {
            VBox register = FXMLLoader.load(getClass().getResource("./register.fxml"));

            ((Label)register.getChildren().get(1)).setText(String.valueOf(getNumberOfRegisters()));

            TextField registerValue = (TextField) register.getChildren().get(0);

            TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
                String text = change.getText();
                if (text.matches("[0-9]*")) {
                    return change;
                }
                return null;
            });

            registerValue.setTextFormatter(textFormatter);
            viewRegisters.getChildren().add(register);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfRegisters(){

        return viewRegisters.getChildren().size();
    }

    public void deleteRegister() {

        if(getNumberOfRegisters() > 1) {

            viewRegisters.getChildren().remove(getNumberOfRegisters()-1);
        }
    }

    public void setContainerWidth(HBox root, double width) {

        if(width > 0) {

            ScrollPane scrollPane = (ScrollPane) root.getChildren().get(1);
            scrollPane.setPrefWidth(width);
        }
    }

    public void updateMemoryRegisters() {

        int size = getNumberOfRegisters();
        int registers[] = new int[size];

        for (int i = 0; i <size ; i++) {
            registers[i] = getRegisterValue(i);
        }

        memoryRegisters.setRegisters(registers);
    }

    public void updateViewRegisters() {

        int size = memoryRegisters.getNumberOfRegisters();

        for (int i = 0; i <size ; i++) {

            if(i >= getNumberOfRegisters() ) {
                addRegister();
            }
            setRegisterValue(i, memoryRegisters.getRegister(i));
        }

        while(getNumberOfRegisters() > size) {
            deleteRegister();
        }
    }
}
