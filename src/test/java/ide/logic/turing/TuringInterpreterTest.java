package ide.logic.turing;


import ide.logic.interpreter.Configuration;
import ide.logic.interpreter.Interpreter;
import ide.logic.interpreter.Program;
import ide.logic.parsers.SimpleFileParser;
import ide.logic.parsers.SimpleTuringFileParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TuringInterpreterTest {

    @Test
    public void testTuringInterpreter() throws Exception {

        SimpleFileParser parser = new SimpleTuringFileParser();

        parser.setFilename(getClass().getResource("/test/turing/test3.txt").getPath());
        Program program = parser.getProgram();

        Configuration configuration = new TuringConfiguration("||");
        TuringConfiguration turingConfiguration = ((TuringConfiguration)configuration.getInstance());
        turingConfiguration.setHeadToSymbol('|');

        Interpreter interpreter = new TuringInterpreter();

        interpreter.loadProgram(program);
        interpreter.setInput(configuration);

        interpreter.runProgram();

        //System.out.println(interpreter.getLogger());
    }
}
