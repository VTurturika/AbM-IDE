package alphabet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents container of symbols for MarkovCommand, MarkovProgram and TemplateSymbol classes
 */
public class Alphabet implements Iterable<Character> {

    /**Contains symbols of Alphabet*/
    private Set<Character> alphabet;
    /**Means special "empty symbol"*/
    private char emptySymbol;

    public Alphabet(Set<Character> alphabet) {
        this.alphabet = alphabet;
        this.emptySymbol = '$';
        addSymbol(emptySymbol);
    }

    public Alphabet() {
        this.alphabet = new HashSet<>();
        this.emptySymbol = '$';
        addSymbol(emptySymbol);
    }

    /**
     * Adds symbol to {@code Alphabet}
     *
     * @param c symbol that will be added
     */
    public void addSymbol(char c) {
        if(!contains(c)) {
            alphabet.add(c);
        }
    }

    /**
     * Removes specified symbol from {@code Alphabet}
     *
     * @param c symbol that will be removed
     */
    public void deleteSymbol(char c) {
        if(contains(c)) {
            alphabet.remove(c);
        }
    }

    /**
     * Checks that alphabet contains specified symbol
     *
     * @param c specified symbol
     * @return {@code true} if alphabet contains symbol, else {@code false}
     */
    public boolean contains(char c) {
        return alphabet.contains(c);
    }

    /**
     * Gets character that means "empty symbol"
     *
     * @return character that means "empty symbol"
     */
    public char getEmptySymbol() {
        return emptySymbol;
    }

    /**
     * Sets specified character as "empty symbol"
     *
     * @param emptySymbol character that will be "empty symbol"
     */
    public void setEmptySymbol(char emptySymbol) {
        this.emptySymbol = emptySymbol;
    }

    @Override
    public String toString() {
        String result = "";

        for(Character c : alphabet) {
            result += c + " ";
        }

        result += "[" + alphabet.size() + "]";

        return result;
    }

    /**
     * Implements {@code Iterable} interface. Allows use foreach loops with {@code Alphabet}
     *
     * @return iterator over {@code Alphabet}
     */
    @Override
    public Iterator<Character> iterator() {
        return alphabet.iterator();
    }

    /**
     * Checks that specified {@code Alphabet} is subset of equal current {@code Alphabet}
     *
     * @param a {@code Alphabet} that will be checked
     * @return true if specified  {@code Alphabet} is subset or equal current {@code Alphabet}
     */
    public boolean containSet(Alphabet a) {

        return alphabet.containsAll(a.getCharactersSet());
    }

    /**
     * Returns regex pattern that matches any symbol from this {@code Alphabet}
     *
     * @return regex pattern of symbol from {@code Alphabet}
     */
    public String getSymbolPattern() {

        String result = "[";

        for(Character c : alphabet) {
            if("-^[]&|{}".indexOf(c) != -1) {
                result += "\\";
            }
            result += c;
        }

        return result + "]";
    }

    /**
     * Returns regex pattern that matches any non empty string of symbols from this {@code Alphabet}
     *
     * @return regex pattern for non empty strings from {@code Alphabet}
     */
    public String getNonEmptyStringPattern() {
        return getSymbolPattern() + "+";
    }

    /**
     * Returns regex pattern that matches any string of symbols from this {@code Alphabet}
     *
     * @return regex pattern for strings from {@code Alphabet}
     */
    public String getStringPattern() {
        return getSymbolPattern() + "*";
    }

    /**
     * Performs and returns union of specified {@code Alphabet} and this {@code Alphabet}
     *
     * @param a Alphabet-operand for union
     * @return union of specified {@code Alphabet} and this {@code Alphabet}
     */
    public Alphabet union(Alphabet a) {

        Set<Character> temp = new HashSet<>(alphabet);
        temp.addAll(a.getCharactersSet());
        return new Alphabet(temp);
    }

    /**
     * Performs and returns intersection of specified {@code Alphabet} and this {@code Alphabet}
     *
     * @param a Alphabet-operand for intersection
     * @return intersection of specified {@code Alphabet} and this {@code Alphabet}
     */
    public Alphabet intersection(Alphabet a) {

        Set<Character> temp = new HashSet<>(alphabet);
        temp.retainAll(a.getCharactersSet());

        if(temp.isEmpty()) {
            throw new IllegalArgumentException("Wrong result of intersection");
        }
        else {
            return new Alphabet(temp);
        }
    }

    /**
     * Performs and returns difference of this {@code Alphabet} and specified {@code Alphabet}
     *
     * @param a Alphabet-operand for difference
     * @return difference of this {@code Alphabet} and specified {@code Alphabet}
     */
    public Alphabet difference(Alphabet a) {

        Set<Character> temp = new HashSet<>(alphabet);
        temp.removeAll(a.getCharactersSet());

        if(temp.isEmpty()) {
            throw new IllegalArgumentException("Wrong result of difference");
        }
        else {
            return new Alphabet(temp);
        }
    }

    /**
     * Returns unmodified set of Alphabet's symbols
     *
     * @return unmodified set of Alphabet's symbols
     */
    public Set<Character> getCharactersSet() {
        return Collections.unmodifiableSet(alphabet);
    }
}
