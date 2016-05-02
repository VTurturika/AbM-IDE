package ide.logic.parsers;

import ide.logic.interpreter.*;
import ide.logic.URM.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleUrmFileParser extends SimpleFileParser {

    private final static String urmCommand =
    "((\\d+)[\\)\\.])?\\s*([zZsStTjJ])\\s?\\(\\s?(\\d+)\\s?(\\,\\s?(\\d+)\\s?)?(\\,\\s?(\\d+)\\s?)?\\)";

    public SimpleUrmFileParser(String filename) {
        super(filename);
        this.commandPattern = Pattern.compile(
                              String.format("(?<command>%1$s(\\s*%2$s)?)|(?<comment>%2$s)", urmCommand, comment));
        this.program = new UrmProgram();
    }

    public SimpleUrmFileParser() {
        this("");
    }

    public Command parseCommand(String str) {

        Matcher matcher = commandPattern.matcher(str);
        if(matcher.find()) {

            if(matcher.group("command") != null && matcher.group(2) != null) {

                String type = matcher.group(4).toLowerCase();
                int firstArgument  = createArgument(matcher.group(5));
                int secondArgument = createArgument(matcher.group(7));
                int thirdArgument  = createArgument(matcher.group(9));

                return new UrmCommand(type, firstArgument, secondArgument, thirdArgument);
            }
            else if(matcher.group("comment") != null) {
                return null;
            }

        }

        throw new IllegalArgumentException("Error of parsing command");
    }

    private int createArgument(String str) {

        if( str == null ) {
            return -1;
        }
        else {
            return Integer.valueOf(str);
        }
    }
}
