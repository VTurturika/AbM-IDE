package ide.logic.markov;

import ide.logic.interpreter.*;
import org.junit.Test;
import ide.logic.parsers.SimpleFileParser;
import ide.logic.parsers.SimpleMarkovFileParser;

public class MarkovInterpreterTest {

    @Test
    public void testMarkovInterpreter() throws Exception {

        SimpleFileParser parser = new SimpleMarkovFileParser();

        parser.setFilename(System.getProperty("user.dir") + "\\test\\ide\\logic\\markov\\programs\\test6.txt");
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
