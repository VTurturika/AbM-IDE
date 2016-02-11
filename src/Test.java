import interpreter.*;
import URM.*;
import turing.*;
import markov.*;
import parsers.*;

import java.util.Iterator;

public class Test {

    public static void main(String args[]) {

        SimpleFileParser parser = new SimpleTuringFileParser("e:/program.txt");
        Program program = parser.getProgram();

        for(Iterator<Command> i = program.iterator(); i.hasNext();) {
            TuringCommand turingCommand = (TuringCommand) i.next().getInstance();
            turingCommand.setDirection(Direction.RIGHT);
        }

        for(Command c : program) {
            System.out.println(c);
        }

    }

    public void readFromFile() {

        SimpleFileParser parser = new SimpleTuringFileParser();
        parser.setFilename("e:/program.txt");

        Program program = parser.getProgram();

        TuringConfiguration configuration = new TuringConfiguration();
        configuration.setTape("|||");
        configuration.setHeadToSymbol('|');

        Interpreter interpreter = new TuringInterpreter();
        interpreter.setInput(configuration);
        interpreter.loadProgram(program);

        interpreter.runProgram();

        System.out.println(configuration);
    }
}
