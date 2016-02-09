import interpreter.*;
import URM.*;
import turing.*;
import markov.*;
import parsers.*;

public class Test {

    public static void main(String args[]) {

        Alphabet alphabet = new Alphabet("{'#', '|'}");
        System.out.println(alphabet);
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
