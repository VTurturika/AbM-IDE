package markov;

import interpreter.*;

public class MarkovCommand extends Command {

    private String pattern;
    private String replacement;
    private boolean isFinishCommand;

    public MarkovCommand(String pattern, String replacement, boolean isFinishCommand) {
        this.pattern = pattern;
        this.replacement = replacement;
        this.isFinishCommand = isFinishCommand;
    }

    public MarkovCommand(String pattern, String replacement) {
        this(pattern,replacement, false);
    }

    public MarkovCommand() {
        this("","", false);
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public boolean isFinishCommand() {
        return isFinishCommand;
    }

    public void setFinishCommand(boolean finishCommand) {
        isFinishCommand = finishCommand;
    }

    @Override
    public String toString() {
        String result = "";

        result += getPattern().equals("") ? "$" : getPattern();
        result += " " + (isFinishCommand() ? "->." : "->") + " ";
        result += getReplacement().equals("") ? "$" : getReplacement();

        return result;
    }
}
