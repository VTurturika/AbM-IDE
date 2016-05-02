package ide.logic.markov;

import ide.logic.alphabet.EmptySymbol;
import ide.logic.alphabet.TemplateSymbol;
import ide.logic.interpreter.Command;
import ide.logic.interpreter.Configuration;
import ide.logic.interpreter.Interpreter;
import ide.logic.interpreter.StopCommand;
import ide.views.markovWidgets.MarkovWidgetController;

import java.util.regex.Pattern;

public class MarkovInterpreter extends Interpreter {

    private MarkovConfiguration markovString;
    private boolean hasFinishCommand;

    public MarkovInterpreter() {

        this.markovString = null;
        this.hasFinishCommand = false;
    }

    @Override
    protected Command getFirstCommand() {

        return ( markovString == null ) ? new StopCommand() : nextCommand();
    }

    @Override
    protected Command nextCommand() {

        if(hasFinishCommand) {
            return new StopCommand();
        }

        for(Command c : program) {
            MarkovCommand command = (MarkovCommand) c.getInstance();

            if(!command.canExecute()) {
                prepareForExecute(command);
            }

            if(isSuitableCommand(command)) {
                return c;
            }
        }

        return new StopCommand();
    }

    @Override
    protected void execute(Command c) {

        MarkovCommand command = (MarkovCommand) c.getInstance();

        markovString.setString( replace( command.getExecutePattern(), command.getExecuteReplacement() ) );

        if(command.isFinishCommand()) {
            hasFinishCommand = true;
        }
    }

    @Override
    public void setInput(Configuration input) {
        markovString = (MarkovConfiguration) input.getInstance();
    }

    private String replace(String pattern, String replacement) {

        return markovString.getString().replaceFirst(pattern, replacement);
    }

    private boolean isMetaSymbol(char c) {

        return ( ".\\|()[]{}$^+*?".indexOf(c) != -1 );
    }

    private void prepareForExecute(MarkovCommand command) {

        command.setExecutePattern( protectFromMetaSymbols(command.getPattern()) );
        command.setExecuteReplacement( protectFromMetaSymbols(command.getReplacement()) );

        if(command.isTemplateCommand()) {

            String pattern, replacement, templatePattern;
            for(TemplateSymbol t: command) {

                switch(t.getTemplateStringMode()){

                    case NONE:
                        templatePattern = t.getAlphabet().getSymbolPattern();
                        break;
                    case STRING:
                        templatePattern = t.getAlphabet().getStringPattern();
                        break;
                    case NONEMPTY_STRING:
                        templatePattern = t.getAlphabet().getNonEmptyStringPattern();
                        break;
                    default:
                        templatePattern = "";
                }

                pattern = command.getExecutePattern();
                pattern = pattern.replaceAll(String.valueOf(t.getAlias()),
                                             String.format("(?<%1$c>%2$s)", t.getAlias(), templatePattern));

                replacement = command.getExecuteReplacement();
                replacement = replacement.replaceAll(String.valueOf(t.getAlias()),
                                                     String.format("\\${%1$c}", t.getAlias()));

                command.setExecutePattern(pattern);
                command.setExecuteReplacement(replacement);
            }
        }
        command.canExecute(true);
    }

    private boolean isSuitableCommand(MarkovCommand command) {

        return Pattern.compile(command.getExecutePattern()).matcher(markovString.getString()).find();

    }

    private String protectFromMetaSymbols(String str) {

        String result = "";
        for (int i = 0; i < str.length(); i++) {

            if(str.charAt(i) == EmptySymbol.get()) {
                continue;
            }

            if( isMetaSymbol(str.charAt(i)) ) {
                result += "\\";
            }
            result += str.charAt(i);
        }
        return result;
    }
}
