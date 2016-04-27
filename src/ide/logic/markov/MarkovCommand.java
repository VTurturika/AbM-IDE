package ide.logic.markov;

import ide.logic.alphabet.TemplateSymbol;
import ide.logic.interpreter.*;

import java.util.ArrayList;
import java.util.Iterator;

public class MarkovCommand extends Command implements Iterable<TemplateSymbol> {

    private String readPattern;
    private String readReplacement;

    private String executePattern;
    private String executeReplacement;

    private ArrayList<TemplateSymbol> templateSymbols;
    private boolean isFinishCommand;
    private boolean canExecute;

    public MarkovCommand(String pattern, String replacement, boolean isFinishCommand) {
        this.readPattern = pattern;
        this.readReplacement = replacement;
        this.canExecute = false;
        this.isFinishCommand = isFinishCommand;
        this.templateSymbols = null;
    }

    public MarkovCommand(String pattern, String replacement) {
        this(pattern, replacement, false);
    }

    public String getPattern() {
        return readPattern;
    }

    public void setPattern(String readPattern) {
        this.readPattern = readPattern;
    }

    public String getReplacement() {
        return readReplacement;
    }

    public void setReplacement(String readReplacement) {
        this.readReplacement = readReplacement;
    }

    public String getExecutePattern() {
        return executePattern;
    }

    public void setExecutePattern(String executePattern) {
        this.executePattern = executePattern;
    }

    public String getExecuteReplacement() {
        return executeReplacement;
    }

    public void setExecuteReplacement(String executeReplacement) {
        this.executeReplacement = executeReplacement;
    }

    public boolean canExecute() {
        return canExecute;
    }

    public void canExecute(boolean flag) {
        canExecute =  flag;
    }

    public boolean isFinishCommand() {
        return isFinishCommand;
    }

    public void setFinishCommand(boolean finishCommand) {
        isFinishCommand = finishCommand;
    }

    public void addTemplateSymbol(TemplateSymbol t) {

        if(templateSymbols == null) {
            templateSymbols = new ArrayList<>();
        }
        templateSymbols.add(t);
    }

    public boolean isTemplateCommand() {
        return templateSymbols != null && !templateSymbols.isEmpty();
    }

    @Override
    public Iterator<TemplateSymbol> iterator() {

        if( isTemplateCommand() ) {
            return templateSymbols.iterator();
        }
        else {
            throw new UnsupportedOperationException();
        }

    }
}
