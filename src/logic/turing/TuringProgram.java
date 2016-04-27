package logic.turing;

import logic.interpreter.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class TuringProgram implements Program {

    private HashMap<TuringCommandIndex, TuringCommand> program;

    public TuringProgram() {
        this.program = new HashMap<>();
    }

    @Override
    public void clearProgram() {
        program.clear();
    }

    @Override
    public void addCommand(Command c) {

        TuringCommand command = (TuringCommand) c.getInstance();
        program.put(command.getBefore(), command);
    }

    @Override
    public Command getCommand(CommandIndex i) {

        TuringCommandIndex index =(TuringCommandIndex) i.getIndexInstance();
        return program.get(index);
    }

    @Override
    public int getNumberOfCommands() {
        return program.size();
    }

    @Override
    public String toString() {
        String result = "";
        Collection<TuringCommand> commands = program.values();

        for(TuringCommand c : commands ) {
            result += c + "\n";
        }

        return result;
    }

    @Override
    public Iterator<Command> iterator() {
        return new Iterator<Command>() {

            private Iterator<TuringCommand> iter = program.values().iterator();

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
