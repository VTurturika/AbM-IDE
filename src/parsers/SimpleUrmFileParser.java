package parsers;

import interpreter.*;
import URM.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleUrmFileParser extends SimpleFileParser {


    public SimpleUrmFileParser(String filename) {
        super(filename);
        this.commandPattern = Pattern.compile("((\\d+)[\\)\\.])?\\s*([zZsStTjJ])\\s?\\(\\s?(\\d+)\\s?(\\,\\s?(\\d+)\\s?)?(\\,\\s?(\\d+)\\s?)?\\)");
        this.program = new UrmProgram();
    }

    public SimpleUrmFileParser() {
        this("");
    }

    public Command parseCommand(String str) {

        Matcher matcher = commandPattern.matcher(str);
        if(matcher.find()) {

            String type = matcher.group(3).toLowerCase();
            int firstArgument  = createArgument(matcher.group(4));
            int secondArgument = createArgument(matcher.group(6));
            int thirdArgument  = createArgument(matcher.group(8));

            return new UrmCommand(type, firstArgument, secondArgument, thirdArgument);
        }
        else {
            throw new IllegalArgumentException("Error of parsing command");
        }
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
