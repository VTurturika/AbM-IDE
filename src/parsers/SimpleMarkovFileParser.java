package parsers;

import interpreter.Command;
import markov.MarkovCommand;
import markov.MarkovProgram;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Turturika on 29.01.2016.
 */
public class SimpleMarkovFileParser extends SimpleFileParser {

    public SimpleMarkovFileParser(String filename) {
        super(filename);
        this.commandPattern = Pattern.compile("(\\S+)\\s?->(\\.)?\\s?(\\S+)");
        this.program = new MarkovProgram();
    }

    @Override
    public Command parseCommand(String str) {

        Matcher matcher = commandPattern.matcher(str);
        if(matcher.find()) {

            String pattern = createPartOfCommand(matcher.group(1));
            String replacer = createPartOfCommand(matcher.group(3));
            boolean isFinishCommand = matcher.group(2) != null;

            return new MarkovCommand(pattern, replacer, isFinishCommand);
        }
        else {
            throw new IllegalArgumentException("Error of parsing command");
        }
    }

    private String createPartOfCommand(String str) {

        return str.equals("$") ? "" : str;
    }

}
