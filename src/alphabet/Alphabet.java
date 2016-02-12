package alphabet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alphabet implements Iterable<Character> {

    private Set<Character> alphabet;
    private char emptySymbol;

    public Alphabet() {
        this.alphabet = new HashSet<>();
        this.emptySymbol = '$';
        alphabet.add(emptySymbol);
    }

    public void addSymbol(char c) {
        if(!contains(c)) {
            alphabet.add(c);
        }
    }

    public boolean contains(char c) {
        return alphabet.contains(c);
    }

    public void deleteSymbol(char c) {
        if(contains(c)) {
            alphabet.remove(c);
        }
    }

    public boolean elementOf(char c) {
        return alphabet.contains(c);
    }

    public char getEmptySymbol() {
        return emptySymbol;
    }

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

    @Override
    public Iterator<Character> iterator() {
        return alphabet.iterator();
    }

    public boolean containSet(Alphabet a) {

        for(Character c : a) {

            if(!alphabet.contains(c)) {
                return false;
            }
        }
        return true;
    }

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

    public String getNonEmptyStringPattern() {
        return getSymbolPattern() + "+";
    }

    public String getStringPattern() {
        return getSymbolPattern() + "*";
    }
}
