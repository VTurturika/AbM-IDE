package turing;

import interpreter.*;

import java.util.Collection;
import java.util.HashMap;

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
    public int getCommandNumber() {
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
}
