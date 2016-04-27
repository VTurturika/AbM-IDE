package logic.parsers;

import logic.interpreter.*;
import logic.turing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTuringFileParser extends SimpleFileParser {

    public SimpleTuringFileParser(String filename) {
        super(filename);
        this.commandPattern = Pattern.compile("q\\s?(\\d+)\\s?([\\S])\\s?->\\s?q\\s?((\\d+)|(\\*))\\s?([\\S])(\\s?([RL]))?");
        this.program = new TuringProgram();
    }

    public SimpleTuringFileParser() {
        this("");
    }

    @Override
    public Command parseCommand(String str) {
        Matcher matcher = commandPattern.matcher(str);
        if(matcher.find()) {
            int stateBefore = createState(matcher.group(1));
            char symbolBefore = matcher.group(2).charAt(0);
            int stateAfter = createState(matcher.group(3));
            char symbolAfter = matcher.group(6).charAt(0);
            Direction direction = createDirection(matcher.group(8));

            return new TuringCommand(stateBefore, symbolBefore, stateAfter, symbolAfter, direction);
        }
        else {
            throw new IllegalArgumentException("Error of parsing command");
        }

    }

    private int createState(String str) {
        if(str.equals("*")) {
            return -1;
        }
        else {
            return Integer.valueOf(str);
        }
    }

    private Direction createDirection(String str) {

        if(str == null) {
            return Direction.NONE;
        }
        else if( str.equals("L") ){
            return Direction.LEFT;
        }
        else {
            return Direction.RIGHT;
        }
    }
}
