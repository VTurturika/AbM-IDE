import interpreter.*;
import URM.*;
import turing.*;
import markov.*;
import parsers.*;

public class Test {

    public static void main(String args[]) {

        SimpleFileParser parser = new SimpleMarkovFileParser("e:/program.txt");
        Program program = parser.getProgram();

        MarkovConfiguration configuration = new MarkovConfiguration("|||");

        Interpreter interpreter = new MarkovInterpreter();

        interpreter.setInput(configuration);
        interpreter.loadProgram(program);

        interpreter.runProgram();

        System.out.println(configuration);
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
