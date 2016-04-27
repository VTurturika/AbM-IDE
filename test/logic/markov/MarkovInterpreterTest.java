package logic.markov;

import logic.interpreter.*;
import org.junit.Test;
import logic.parsers.SimpleFileParser;
import logic.parsers.SimpleMarkovFileParser;

public class MarkovInterpreterTest {

    @Test
    public void testMarkovInterpreter() throws Exception {

        SimpleFileParser parser = new SimpleMarkovFileParser();

        parser.setFilename(System.getProperty("user.dir") + "\\test\\logic\\markov\\programs\\test6.txt");
        Program program = parser.getProgram();

        Configuration configuration = new MarkovConfiguration();
        MarkovConfiguration markovConfiguration = ((MarkovConfiguration)configuration.getInstance());
        Interpreter interpreter = new MarkovInterpreter();

        markovConfiguration.setString("||#|||");
        interpreter.setInput(markovConfiguration);
        interpreter.loadProgram(program);
        interpreter.runProgram();

        System.out.println(markovConfiguration);
    }
}
