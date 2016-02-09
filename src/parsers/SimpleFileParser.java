package parsers;


import interpreter.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


abstract public class SimpleFileParser {

    private String filename;
    protected List<String> strings;
    protected Pattern commandPattern;
    protected Program program;


    public SimpleFileParser(String filename) {

        this.filename = filename;
        this.strings = new ArrayList<>();
        this.commandPattern = null;
        this.program = null;
    }

    public SimpleFileParser() {
        this("");
    }

    protected void readFromFile() {

        strings.clear();
        String temp;

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            while( (temp = reader.readLine() )!= null ) {
                strings.add(temp);
            }
            fileReader.close();
            reader.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            strings.clear();
        }
    }

    public void setFilename(String filename) {

        this.filename = filename;
        readFromFile();

    }

    public void addToList(String str) {
        strings.add(str);
    }

    public Program getProgram() {

        if(strings.isEmpty()) {
            readFromFile();
        }
        program.clearProgram();
        for (String s : strings) {

            try {
                program.addCommand(parseCommand(s));
            } catch (IllegalArgumentException e) {
                program.clearProgram();
                throw new RuntimeException("Error of parsing command " + s);
            }
        }
        return program;
    }

    abstract public Command parseCommand(String str);
}
