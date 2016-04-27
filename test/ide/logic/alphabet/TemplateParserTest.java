package ide.logic.alphabet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Implements tests for TemplateParser class methods
 */
public class TemplateParserTest {

    @Test
    public void hasTemplateSymbolDefinition() throws Exception {

        TemplateParser parser = new TemplateParser();

        assertTrue(parser.hasTemplateSymbolDefinition(": x in {'1', '2', '3'}"));
        assertTrue(parser.hasTemplateSymbolDefinition(": x,y in {'1','2','3'}"));
        assertTrue(parser.hasTemplateSymbolDefinition(": x,y,z in {'1','2','3'}"));

        assertTrue(parser.hasTemplateSymbolDefinition(": x,y,z in {'1','2','3'}, a in {'4', '5'}"));
        assertTrue(parser.hasTemplateSymbolDefinition(": x,y,z in {'1','2','3'}, a,b in {'4', '5'}"));

        assertTrue(parser.hasTemplateSymbolDefinition(": x,y,z in {'1','2','3'}, a,b in {'4', '5'}+, w in T*"));
    }


    @Test
    public void isValidSet() throws Exception {

        TemplateParser parser = new TemplateParser();

    //single set

        //variants
        assertTrue("enumerable set", parser.isValidSet("{'1','2','3'}"));
        assertTrue("enumerable set single item", parser.isValidSet("{'1'}"));
        assertTrue("Predefined T", parser.isValidSet("T"));
        assertFalse("Predefined small T", parser.isValidSet("t"));
        assertTrue("Predefined I", parser.isValidSet("I"));
        assertTrue("Predefined O", parser.isValidSet("O"));
    }


    @Test
    public void createTemplateSymbol() throws Exception {

        TemplateParser parser = new TemplateParser();

        Alphabet alphabet = new Alphabet();
        alphabet.addSymbols(new char[]{'a', 'b', 'c'});
        TemplateSymbol origin = new TemplateSymbol('x', alphabet);

        assertEquals("Just a symbol", origin, parser.createTemplateSymbol("x in {'a', 'b', 'c'}"));

        origin.setTemplateStringMode(TemplateSymbol.TemplateStringMode.STRING);
        assertEquals("Template string", origin, parser.createTemplateSymbol("x in {'a', 'b', 'c'}*"));

        origin.setTemplateStringMode(TemplateSymbol.TemplateStringMode.NONEMPTY_STRING);
        assertEquals("Nonempty template string", origin, parser.createTemplateSymbol("x in {'a', 'b', 'c'}+"));

        origin = new TemplateSymbol('X', new Alphabet(new char[]{'|'}));
        origin.setTemplateStringMode(TemplateSymbol.TemplateStringMode.NONEMPTY_STRING);

        assertEquals("Simulaton real Markov Command", origin, parser.createTemplateSymbol("X#->X**X#X# : X in {'|'}+"));

    }

    @Test
    public void createTemplateSymbols() throws Exception {

        TemplateParser parser = new TemplateParser();
        Alphabet alphabet1 = new Alphabet(new char[]{'1','2','3'});

        TemplateSymbol[] result1 = {new TemplateSymbol('x', alphabet1), new TemplateSymbol('y', alphabet1)};
        assertArrayEquals(result1, parser.createTemplateSymbols("x,y in {'1', '2', '3'}"));

        Alphabet alphabet2 = new Alphabet(new char[]{'4', '5'});
        TemplateSymbol[] result2 = {new TemplateSymbol('x', alphabet1),
                                    new TemplateSymbol('y', alphabet1),
                                    new TemplateSymbol('a', alphabet2, TemplateSymbol.TemplateStringMode.STRING),
                                    new TemplateSymbol('b', alphabet2, TemplateSymbol.TemplateStringMode.STRING)};

        assertArrayEquals(result2, parser.createTemplateSymbols("x,y in {'1', '2', '3'}, a,b in {'4', '5'}*"));
    }

    @Test
    public void hasMoreOneTemplateSymbol() throws Exception {

        TemplateParser parser = new TemplateParser();

        assertTrue(parser.hasMoreOneTemplateSymbol("x,y in {'a'}"));
        assertTrue(parser.hasMoreOneTemplateSymbol("x,y,z in {'a'}"));

        assertFalse(parser.hasMoreOneTemplateSymbol("x in {'b'}"));

    }
}