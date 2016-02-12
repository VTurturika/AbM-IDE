import interpreter.*;
import URM.*;
import turing.*;
import markov.*;
import parsers.*;
import alphabet.*;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String args[]) {

        SetParser setParser = new SetParser();
        try {
            Alphabet alphabet = setParser.createAlphabet("{'1'} diff {'1'}");
            System.out.println(alphabet);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
