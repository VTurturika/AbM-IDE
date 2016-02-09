package markov;

import interpreter.Command;
import interpreter.Configuration;
import interpreter.Interpreter;
import interpreter.StopCommand;

public class MarkovInterpreter extends Interpreter {

    private MarkovCommandIndex index;
    private MarkovConfiguration markovString;
    private boolean hasFinishCommand;


    public MarkovInterpreter() {
        this.index = new MarkovCommandIndex();
        this.markovString = new MarkovConfiguration();
        this.hasFinishCommand = false;
    }

    @Override
    protected Command getFirstCommand() {

        if(markovString != null) {
            index.setPattern(markovString.getString());
            return program.getCommand(index);
        }
        else {
            return new StopCommand();
        }
    }

    @Override
    protected Command nextCommand() {

        return (!hasFinishCommand) ? program.getCommand(index) : new StopCommand();
    }

    @Override
    protected void execute(Command c) {

        MarkovCommand command = (MarkovCommand) c.getInstance();

        markovString.setString( replace(command.getPattern(),command.getReplacement()) );

        if( command.isFinishCommand() ) {
            hasFinishCommand = true;
        }
        else {
            index.setPattern(markovString.getString());
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
