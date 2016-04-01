package parsers;

import alphabet.TemplateParser;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Turturika on 01.04.2016.
 */
public class TemplateParserTest {

    @Test
    public void isValidSet() throws Exception {

        TemplateParser parser = new TemplateParser();

    //single set

        //variants
        assertEquals("enumerable set", true, parser.isValidSet("{'1','2','3'}"));
        assertEquals("enumerable set single item", true, parser.isValidSet("{'1'}"));
        assertEquals("Predefined T", true, parser.isValidSet("T"));
        assertEquals("Predefined small T", false, parser.isValidSet("t"));
        assertEquals("Predefined I", true, parser.isValidSet("I"));
        assertEquals("Predefined O", true, parser.isValidSet("O"));

        //with extra chars
        assertEquals("enumerable set + extra", true, parser.isValidSet("qwerty{'1','2','3'}"));
        assertEquals("enumerable set single item + extra", false, parser.isValidSet("\n{    '1'}\nqw\terty"));
        assertEquals("Predefined T + extra T", true, parser.isValidSet("TTTT")); /// ???

    //with set operation

        //simple unions
        assertEquals("union PS 1", true, parser.isValidSet("T union O"));
        assertEquals("union PS 2", true, parser.isValidSet("T or O"));
        assertEquals("union PS 3", true, parser.isValidSet("T OR O"));
        assertEquals("union PS 4", true, parser.isValidSet("T | O"));
        assertEquals("union PS 5", true, parser.isValidSet("T ∪ O"));

        //simple intersections
        assertEquals("intersection PS 1", true, parser.isValidSet("T intersect O"));
        assertEquals("intersection PS 2", true, parser.isValidSet("T and O"));
        assertEquals("intersection PS 3", true, parser.isValidSet("T AND O"));
        assertEquals("intersection PS 4", true, parser.isValidSet("T & O"));
        assertEquals("intersection PS 5", true, parser.isValidSet("T ∩ O"));

        //simple difference
        assertEquals("intersection PS 1", true, parser.isValidSet("T intersect O"));



    }

    @Test
    public void createAlphabet() throws Exception {

    }

    @Test
    public void createTemplateSymbol() throws Exception {

    }
}