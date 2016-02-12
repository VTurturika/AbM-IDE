package markov;

import interpreter.Command;
import interpreter.Configuration;
import interpreter.Interpreter;
import interpreter.StopCommand;

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
            if( markovString.getString().contains(command.getPattern()) ) {
                return c;
            }
        }

        return new StopCommand();
    }

    @Override
    protected void execute(Command c) {

        MarkovCommand command = (MarkovCommand) c.getInstance();

        markovString.setString( replace( command.getPattern(), command.getReplacement() ) );

        if(command.isFinishCommand()) {
            hasFinishCommand = true;
        }
    }

    @Override
    public void setInput(Configuration input) {
        markovString = (MarkovConfiguration) input.getInstance();
    }

    private String replace(String pattern, String replacement) {

        String regexPattern = "";
        for (int i = 0; i < pattern.length(); i++) {

            if( isMetaSymbol(pattern.charAt(i)) ) {
                regexPattern += "\\";
            }
            regexPattern += pattern.charAt(i);
        }

        return markovString.getString().replaceFirst(regexPattern, replacement);
    }

    private boolean isMetaSymbol(char c) {

        return ( ".\\|()[]{}$^+*?".indexOf(c) != -1 );
    }
}
