package alphabet;

import org.hamcrest.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Implements tests for TemplateParser class methods
 */
public class TemplateParserTest {

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

        //with extra chars
        assertTrue("enumerable set + extra", parser.isValidSet("qwerty{'1','2','3'}"));
        assertTrue("Predefined T + extra T", parser.isValidSet("TTTT")); /// ???

    //with set operation

        //simple tests (PS = predefined set)

        //simple unions
        assertTrue("union PS 1", parser.isValidSet("T union O"));
        assertTrue("union PS 2", parser.isValidSet("T or O"));
        assertTrue("union PS 3", parser.isValidSet("T OR O"));
        assertTrue("union PS 4", parser.isValidSet("T | O"));
        assertTrue("union PS 5", parser.isValidSet("T ∪ O"));

        //simple intersections
        assertTrue("intersection PS 1", parser.isValidSet("T intersect O"));
        assertTrue("intersection PS 2", parser.isValidSet("T and O"));
        assertTrue("intersection PS 3", parser.isValidSet("T AND O"));
        assertTrue("intersection PS 4", parser.isValidSet("T & O"));
        assertTrue("intersection PS 5", parser.isValidSet("T ∩ O"));

        //simple difference
        assertTrue("difference PS 1", parser.isValidSet("T \\ O"));
        assertTrue("difference PS 1", parser.isValidSet("T diff O"));

        //with extra chars
        assertTrue("union + extra", parser.isValidSet("qwertyTT or \tO"));
        assertTrue("intersection + extra", parser.isValidSet("\n\nqwerty\tT    AND \tO"));
        assertTrue("difference + extra", parser.isValidSet("T\t\t\b\tdiff \tO"));

        //hard tests
        assertTrue("hard 1", parser.isValidSet("{'a'} union {'b'} diff {'c'}"));
        assertTrue("hard 2", parser.isValidSet("{'a'} union ( {'b'} diff {'c'} )"));
    }

    @Test
    public void createAlphabet() throws Exception {

        TemplateParser parser = new TemplateParser();
        Alphabet alphabet = new Alphabet();

        alphabet.addSymbols(new char[]{'1', '2', '3'});

        assertThat(alphabet, AlphabetMatchers.isAlphabet(parser.createAlphabet("{'1','2','3'}")));

        alphabet.clear();
        alphabet.addSymbols(new char[]{'a', 'b', 'c'});

        //assertThat(alphabet, alphabetMatcher(parser.createAlphabet("({'a'} union {'b'}) union {c}")));

        alphabet.deleteSymbol('c');
        assertThat("union test",
                   alphabet, AlphabetMatchers.isAlphabet(parser.createAlphabet("{'a'} union {'b'}")));

        alphabet.deleteSymbol('b');
        assertThat("intersection test",
                   alphabet, AlphabetMatchers.isAlphabet(parser.createAlphabet("{'a', 'b'} and {'a', 'c'}")));

        assertThat("difference test",
                   alphabet, AlphabetMatchers.isAlphabet(parser.createAlphabet("{'a', 'b'} diff {'b', 'c'}")));

    }
    @Test
    public void createTemplateSymbol() throws Exception {

        TemplateParser parser = new TemplateParser();
        Alphabet alphabet = new Alphabet();

        //case 1
        alphabet.addSymbols(new char[]{'a', 'b', 'c'});
        TemplateSymbol t = new TemplateSymbol('x', alphabet);
        assertThat("Simple definition", t, AlphabetMatchers.isTemplateSymbol(
                   parser.createTemplateSymbol(": x in {'a', 'b', 'c'}")));

        //case 2
        t.getAlphabet().addSymbols(new char[]{'d', 'e'});
        assertThat("Definition with operation", t, AlphabetMatchers.isTemplateSymbol(
                   parser.createTemplateSymbol(": x in {'a', 'b', 'c'} union {'d', 'e'}")));

        t.getAlphabet().clear();
        t.getAlphabet().addSymbol('a');
        t.setTemplateString(true);
        t.setTemplateStringMode(TemplateSymbol.TemplateStringMode.STRING);
        assertThat("Simple Kleene Star", t, AlphabetMatchers.isTemplateSymbol(
                   parser.createTemplateSymbol(": x in ({'a'})*")));

        t.getAlphabet().addSymbol('b');
        assertThat("Kleene Star with union", t, AlphabetMatchers.isTemplateSymbol(
                parser.createTemplateSymbol(": x in ({'a'} or {'b'})*")));

        t.getAlphabet().deleteSymbol('b');
        assertThat("Kleene Star with difference", t, AlphabetMatchers.isTemplateSymbol(
                parser.createTemplateSymbol(": x in ( {'a','b'} \\ {'b'} )*")));

        t.setTemplateStringMode(TemplateSymbol.TemplateStringMode.NONEMPTY_STRING);
        assertThat("Simple Kleene Plus", t, AlphabetMatchers.isTemplateSymbol(
                parser.createTemplateSymbol(": x in ({'a'})+")));

        t.getAlphabet().addSymbol('b');
        assertThat("Kleene Star with union", t, AlphabetMatchers.isTemplateSymbol(
                parser.createTemplateSymbol(": x in ({'a'} or {'b'})+")));

        t.getAlphabet().deleteSymbol('b');
        assertThat("Kleene Star with difference", t, AlphabetMatchers.isTemplateSymbol(
                parser.createTemplateSymbol(": x in ( {'a','b'} \\ {'b'} )+")));

        t = new TemplateSymbol('X', new Alphabet(new char[]{'|'}));
        t.setTemplateString(true);
        t.setTemplateStringMode(TemplateSymbol.TemplateStringMode.NONEMPTY_STRING);

        assertThat("Simulaton real Markov Command", t, AlphabetMatchers.isTemplateSymbol(
                parser.createTemplateSymbol("X#->X**X#X# : X in ({'|'})+ ")));
    }
}