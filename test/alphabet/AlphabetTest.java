package alphabet;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Implements tests for Alphabet class methods
 */
public class AlphabetTest {

    @Test
    public void containSet() throws Exception {

        Alphabet a = new Alphabet(new char[]{'a', 'b', 'c'});
        Alphabet b = new Alphabet(new char[]{'a'});

        assertTrue(a.containSet(b));
        assertFalse(b.containSet(a));
        assertTrue(a.containSet(a));
    }

    @Test
    public void union() throws Exception {

        Alphabet a = new Alphabet(new char[]{'a', 'b', 'c'});
        Alphabet b = new Alphabet(new char[]{'a', 'c', 'd'});
        Alphabet result = new Alphabet(new char[]{'a', 'b', 'c', 'd'});

        assertThat(result, AlphabetMatchers.isAlphabet(a.union(b)));
    }

    @Test
    public void intersection() throws Exception {

        Alphabet a = new Alphabet(new char[]{'a', 'b', 'c'});
        Alphabet b = new Alphabet(new char[]{'a', 'c', 'd'});
        Alphabet result = new Alphabet(new char[]{'a', 'c'});

        assertThat(result, AlphabetMatchers.isAlphabet(a.intersection(b)));

        a = a.difference(b); //a = {'b'}
        result.clear();
        assertThat(result, AlphabetMatchers.isAlphabet(a.intersection(b)));

        a.clear(); b.clear();
        assertThat(result, AlphabetMatchers.isAlphabet(a.intersection(b)));

        a = new Alphabet(); b = new Alphabet(); result = new Alphabet();
        assertThat(result, AlphabetMatchers.isAlphabet(a.intersection(b)));

    }

    @Test
    public void difference() throws Exception {

        Alphabet a = new Alphabet(new char[]{'a', 'b'});
        Alphabet b = new Alphabet(new char[]{'a', 'c'});
        Alphabet result = new Alphabet(new char[]{'b'});

        assertThat(result, AlphabetMatchers.isAlphabet(a.difference(b)));

        result.clear();
        assertThat(result, AlphabetMatchers.isAlphabet(a.difference(a)));
    }
}