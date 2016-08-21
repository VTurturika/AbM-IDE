package ide.logic.parsers;

import ide.logic.alphabet.TemplateParser;
import ide.logic.alphabet.TemplateSymbol;
import ide.logic.interpreter.Command;
import ide.logic.markov.MarkovCommand;
import ide.logic.markov.MarkovProgram;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleMarkovFileParser extends SimpleFileParser {

    private final static String markovCommand = "(\\S+)\\s?->(\\.)?\\s?(\\S+)";
    private static TemplateParser parser = new TemplateParser();


    public SimpleMarkovFileParser(String filename) {
        super(filename);
        this.commandPattern = Pattern.compile(
                String.format("(?<command>\\s*%1$s(\\s*%2$s)?\\s*)|(?<comment>\\s*%2$s)", markovCommand, comment));
        this.program = new MarkovProgram();
    }

    public SimpleMarkovFileParser() {
        this("");
    }

    @Override
    public Command parseCommand(String str) throws Exception {

        Matcher matcher;
        String commandPart, definitionPart = "";

        if(parser.hasTemplateSymbolDefinition(str)) {

            int indexOfDefinition = parser.findStartOfTemplateSymbolDefinition(str);
            commandPart = str.substring(0, indexOfDefinition);
            definitionPart = str.substring(indexOfDefinition);
            matcher = commandPattern.matcher(commandPart);
        }
        else {
            matcher = commandPattern.matcher(str);
        }

        if (matcher.matches()) {

            if (matcher.group("command") != null) {
                String pattern = matcher.group(2);
                String replacement = matcher.group(4);
                boolean isFinishCommand = (matcher.group(3) != null);

                if (!definitionPart.equals("")) {

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
}
