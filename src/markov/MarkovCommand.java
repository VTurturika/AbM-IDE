package markov;

import interpreter.*;

public class MarkovCommand extends Command {

    private String pattern;
    private String replacer;
    private boolean isFinishCommand;

    public MarkovCommand(String pattern, String replacer, boolean isFinishCommand) {
        this.pattern = pattern;
        this.replacer = replacer;
        this.isFinishCommand = isFinishCommand;
    }

    public MarkovCommand(String pattern, String replacer) {
        this(pattern,replacer, false);
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

    public String getReplacer() {
        return replacer;
    }

    public void setReplacer(String replacer) {
        this.replacer = replacer;
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
        result += getReplacer().equals("") ? "$" : getReplacer();

        return result;
    }
}
