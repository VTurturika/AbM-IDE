package parsers;

import alphabet.TemplateParser;
import alphabet.TemplateSymbol;
import interpreter.Command;
import markov.MarkovCommand;
import markov.MarkovProgram;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleMarkovFileParser extends SimpleFileParser {

    private final static String markovCommand = "(\\S+)\\s?->(\\.)?\\s?(\\S+)";
    private static TemplateParser parser = new TemplateParser();


    public SimpleMarkovFileParser(String filename) {
        super(filename);
        this.commandPattern = Pattern.compile(
                String.format("(?<command>%1$s(\\s*%2$s)?)|(?<comment>%2$s)", markovCommand, comment));
        this.program = new MarkovProgram();
    }

    public SimpleMarkovFileParser() {
        this("");
    }

    @Override
    public Command parseCommand(String str) throws Exception {

        Matcher matcher = commandPattern.matcher(str);
        if (matcher.find()) {

            if (matcher.group("command") != null) {
                String pattern = createPartOfCommand(matcher.group(2));
                String replacement = createPartOfCommand(matcher.group(4));
                boolean isFinishCommand = (matcher.group(3) != null);

                if (parser.hasTemplateSymbolDefinition(str)) {

                    TemplateSymbol[] symbols;
                    if(parser.hasMoreOneTemplateSymbol(str)) {
                        symbols = parser.createTemplateSymbols(str);
                    }
                    else {
                        symbols = new TemplateSymbol[1];
                        symbols[0] = parser.createTemplateSymbol(str);
                    }
                    MarkovCommand command = new MarkovCommand(pattern, replacement, isFinishCommand);
                    for(TemplateSymbol t : symbols) {
                        command.addTemplateSymbol(t);
                    }
                    return command;
                }

                else {
                    return new MarkovCommand(pattern, replacement, isFinishCommand);
                }
            }
            if (matcher.group("comment") != null) {
                return null;
            }
        }

        throw new IllegalArgumentException("Error of parsing command");
    }

    private String createPartOfCommand(String str) {

        return str.equals("$") ? "" : str;
    }

}
