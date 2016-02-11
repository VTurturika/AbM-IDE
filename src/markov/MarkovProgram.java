package markov;

import interpreter.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkovProgram implements Program {

    ArrayList<MarkovCommand> program;

    public MarkovProgram() {
        this.program = new ArrayList<>();
    }

    @Override
    public void addCommand(Command c) {
        program.add( (MarkovCommand) c.getInstance() );
    }

    @Override
    public Command getCommand(CommandIndex index) {

        MarkovCommandIndex commandIndex = (MarkovCommandIndex) index.getIndexInstance();

        for(MarkovCommand c: program) {

            if( commandIndex.getPattern().contains(c.getPattern()) ) {
                return c;
            }
        }

        return new StopCommand();
    }

    @Override
    public int getCommandNumber() {
        return program.size();
    }

    @Override
    public void clearProgram() {
        program.clear();
    }

    @Override
    public String toString() {
        String result = "";

        for(MarkovCommand c : program) {
            result += c + "\n";
        }

        return result;
    }

    @Override
    public Iterator<Command> iterator() {
        return new Iterator<Command>() {

            private Iterator<MarkovCommand> iter = program.iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Command next() {
                return iter.next();
            }
        };
    }
}
