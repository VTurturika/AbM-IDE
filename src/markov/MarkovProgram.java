package markov;

import alphabet.Alphabet;
import interpreter.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.*;

public class MarkovProgram implements Program {

    private ArrayList<MarkovCommand> program;
    private Alphabet mainAlphabet;
    private Alphabet inputAlphabet;
    private Alphabet outputAlphabet;

    public MarkovProgram() {
        this.program = new ArrayList<>();
        this.mainAlphabet = null;
        this.inputAlphabet = null;
        this.outputAlphabet = null;
    }

    @Override
    public void addCommand(Command c) {
        program.add( (MarkovCommand) c.getInstance() );
    }

    @Override
    public Command getCommand(CommandIndex index) {

        MarkovCommandIndex commandIndex = (MarkovCommandIndex) index.getIndexInstance();

        if(commandIndex.getIndex() >= program.size() || commandIndex.getIndex() < 0) {
            return new StopCommand();
        }

        return  program.get(commandIndex.getIndex());
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

    public Alphabet getMainAlphabet() {
        return mainAlphabet;
    }

    public void setMainAlphabet(Alphabet mainAlphabet) {
        this.mainAlphabet = mainAlphabet;
        this.inputAlphabet = mainAlphabet;
        this.outputAlphabet = mainAlphabet;
    }

    public Alphabet getInputAlphabet() {
        return inputAlphabet;
    }

    public void setInputAlphabet(Alphabet inputAlphabet) {

        if(hasMainAlphabet() && mainAlphabet.containSet(inputAlphabet)) {
            this.inputAlphabet = inputAlphabet;
        }
    }

    public Alphabet getOutputAlphabet() {
        return outputAlphabet;
    }

    public void setOutputAlphabet(Alphabet outputAlphabet) {

        if(hasMainAlphabet() && mainAlphabet.containSet(outputAlphabet)) {
            this.outputAlphabet = outputAlphabet;
        }
    }

    public boolean hasMainAlphabet() {
        return (mainAlphabet != null);
    }

    public boolean hasInputAlphabet() {
        return (inputAlphabet != null);
    }

    public boolean hasOutputAlphabet() {
        return (outputAlphabet != null);
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
