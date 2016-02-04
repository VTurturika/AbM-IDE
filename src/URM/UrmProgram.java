package URM;

import interpreter.*;
import java.util.ArrayList;

public class UrmProgram implements Program {

    private ArrayList<UrmCommand> program;

    public UrmProgram() {

        this.program = new ArrayList<>();
    }

    public void addCommand(Command c) {

        program.add( (UrmCommand) c.getInstance() );
    }

    public Command getCommand(CommandIndex i) {

        int index = ((UrmCommandIndex)i.getIndexInstance()).getIndex();
        return program.get(index);
    }

    @Override
    public void clearProgram() {
        program.clear();
    }

    @Override
    public int getCommandNumber() {
        return program.size();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i  < program.size() ; i++) {
            result += (i+1) + ") " + program.get(i) + "\n";
        }
        return result;
    }
}