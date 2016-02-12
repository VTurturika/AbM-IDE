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

        String[] testStrings = {"T", "I", "O", "T diff I", "{'1', '2'} union {'3'}", "{'t'} & T"};

        for(String test : testStrings) {
            System.out.println("String: " + test);
            SetParser.isValidSet(test);
            System.out.println();
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
