package ide.logic.parsers;

import ide.logic.interpreter.*;
import ide.logic.turing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTuringFileParser extends SimpleFileParser {

    private static final String turingCommand =
                                "q\\s?(\\d+)\\s?([\\S])\\s?->\\s?q\\s?((\\d+)|(\\*))\\s?([\\S])(\\s?([RL]))?";

    public SimpleTuringFileParser(String filename) {
        super(filename);
        this.commandPattern = Pattern.compile(
                              String.format("(?<command>%1$s(\\s*%2$s)?)|(?<comment>%2$s)", turingCommand, comment));
        this.program = new TuringProgram();
    }

    public SimpleTuringFileParser() {
        this("");
    }

    @Override
    public Command parseCommand(String str) {
        Matcher matcher = commandPattern.matcher(str);
        if(matcher.find()) {

            if(matcher.group("command") != null ) {

                int stateBefore = createState(matcher.group(2));
                char symbolBefore = matcher.group(3).charAt(0);
                int stateAfter = createState(matcher.group(4));
                char symbolAfter = matcher.group(7).charAt(0);
                Direction direction = createDirection(matcher.group(9));

                return new TuringCommand(stateBefore, symbolBefore, stateAfter, symbolAfter, direction);
            }
            else if(matcher.group("comment") != null) {
                return null;
            }
        }

        throw new IllegalArgumentException("Error of parsing command");
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
