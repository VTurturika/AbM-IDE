package ide.logic.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private String initialMessage;
    private List<String> container;
    private String finalMessage;

    public Logger() {
        initialMessage = "Start";
        container = new ArrayList<>();
        finalMessage = "Finish";
    }

    public void add(String str) {
        container.add(str);
    }

    public String getInitialMessage() {
        return initialMessage;
    }

    public void setInitialMessage(String initialMessage) {
        this.initialMessage = initialMessage;
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    public void clear() {
        container.clear();
    }

    @Override
    public String toString() {

        String result = getInitialMessage() + "\n\n";

        for(String s : container) {
            result += s + "\n\n";
        }

        return result + getFinalMessage();
    }
}
