package alphabet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alphabet implements Iterable<Character> {

    private Set<Character> alphabet;
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

    public void addSymbol(char c) {
        if(!contains(c)) {
            alphabet.add(c);
        }
    }

    public void deleteSymbol(char c) {
        if(contains(c)) {
            alphabet.remove(c);
        }
    }

    public boolean contains(char c) {
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

        return alphabet.containsAll(a.getCharactersSet());
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

    public Alphabet union(Alphabet a) {

        Set<Character> temp = new HashSet<>(alphabet);
        temp.addAll(a.getCharactersSet());
        return new Alphabet(temp);
    }
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

    public Set<Character> getCharactersSet() {
        return Collections.unmodifiableSet(alphabet);
    }
}
