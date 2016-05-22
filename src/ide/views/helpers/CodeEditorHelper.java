package ide.views.helpers;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.fxmisc.richtext.CodeArea;

import java.util.ArrayList;
import java.util.List;

public class CodeEditorHelper {

    private CodeArea codeArea;
    private List<Integer> breakpoints = new ArrayList<>();

    public CodeEditorHelper() {
        this.codeArea = new CodeArea();

        codeArea.setParagraphGraphicFactory(rowNumber -> {
            Label label = new Label(String.valueOf(rowNumber+1));

            label.setPrefWidth( 35 );
            label.setAlignment(Pos.BASELINE_RIGHT);
            label.getStyleClass().add("lineNumber");

            label.setOnMouseClicked(event -> {

                int index = breakpoints.indexOf(rowNumber);

                if(index == -1) {
                    breakpoints.add(rowNumber);
                    label.getStyleClass().add("breakpoint");
                }
                else {
                    breakpoints.remove(index);
                    label.getStyleClass().remove("breakpoint");
                }

            });

             if(breakpoints.contains(rowNumber)) {
                 label.getStyleClass().add("breakpoint");
             }
            return label;
        });

    }

    public CodeArea getCodeEditor() {
        return codeArea;
    }

    public void setWidthAndHeight(Pane container) {
        codeArea.setPrefWidth(container.getPrefWidth());
        codeArea.setPrefHeight(container.getPrefHeight());
    }
}
