package ide.views.helpers;

import ide.logic.interpreter.*;
import ide.logic.parsers.*;

import ide.logic.URM.UrmProgram;
import ide.logic.turing.TuringProgram;
import ide.logic.markov.MarkovProgram;

import org.fxmisc.richtext.CodeArea;

import java.util.ArrayList;

public class CodeAnalyzer {

    public Program analyzeAllCode(CodeArea code, String type) {

        int numOfRows = code.getParagraphs().size();
        SimpleFileParser parser = chooseParserType(type);
        Program program = chooseProgramType(type);

        Command command = null;
        String str = "";
        for (int i = 0; i <numOfRows ; i++) {

            str = code.getParagraph(i).toString();
            if(!str.equals("")) {

                try {
                    command = parser.parseCommand(str);
                    if(command != null) {
                        program.addCommand(command);
                    }
                }
                catch (Exception e) {
                    ArrayList<String> errorStyle = new ArrayList<>();
                    errorStyle.add("error");
                    code.setStyle(i, errorStyle);
                    return null;
                }
            }
        }

        return program;
    }

    public boolean analyzeCode(CodeArea code, String type) {

        int currentParagraph = code.getCurrentParagraph();
        String str = code.getText(currentParagraph);

        if(!str.equals("")) {

            SimpleFileParser parser = chooseParserType(type);

            try {
                parser.parseCommand(str);
            }
            catch (Exception e) {
                ArrayList<String> errorStyle = new ArrayList<>();
                errorStyle.add("error");
                code.setStyle(currentParagraph, errorStyle);
                return false;
            }
        }

        return true;
    }

    private Program chooseProgramType(String type) {

        switch (type) {
            case "URM"    : return new UrmProgram();
            case "Turing" : return new TuringProgram();
            case "Markov" : return new MarkovProgram();
            default: return null;
        }
    }

    private SimpleFileParser chooseParserType(String type) {

        switch (type) {
            case "URM"    : return new SimpleUrmFileParser();
            case "Turing" : return new SimpleTuringFileParser();
            case "Markov" : return new SimpleMarkovFileParser();
            default: return null;
        }
    }
}
