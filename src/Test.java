import interpreter.*;
import URM.*;
import turing.*;
import markov.*;
import parsers.*;
import alphabet.*;

import java.util.Iterator;

public class Test {

    public static void main(String args[]) {

        testMarkovInterpreter();
    }

    public static void testTuringInterpreter() {

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

    public static void testMarkovInterpreter() {

        SimpleFileParser fileParser = new SimpleMarkovFileParser("e:/program.txt");
        Program program = fileParser.getProgram();

        MarkovConfiguration configuration= new MarkovConfiguration();
        configuration.setString("|||");

        Interpreter interpreter = new MarkovInterpreter();

        interpreter.setInput(configuration);
        interpreter.loadProgram(program);

        interpreter.runProgram();

        System.out.println(configuration);
    }
}
