package logic.URM;

import logic.interpreter.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implements container for logic.URM's commands
 */
public class UrmProgram implements Program {

    /**Saves commands of program*/
    private ArrayList<UrmCommand> program;

    public UrmProgram() {

        this.program = new ArrayList<>();
    }

    /**{@inheritDoc}*/
    @Override
    public void addCommand(Command c) {

        program.add( (UrmCommand) c.getInstance() );
    }

    /**{@inheritDoc}*/
    @Override
    public Command getCommand(CommandIndex i) {

        int index = ((UrmCommandIndex)i.getIndexInstance()).getIndex();
        return program.get(index);
    }

    /**{@inheritDoc}*/
    @Override
    public void clearProgram() {
        program.clear();
    }

    /**{@inheritDoc}*/
    @Override
    public int getNumberOfCommands() {
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

    /**
     * Returns iterator over {@code Program}
     *
     * @return iterator over {@code Program}
     */
    @Override
    public Iterator<Command> iterator() {
        return new Iterator<Command>() {

            private Iterator<UrmCommand> iter = program.iterator();

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