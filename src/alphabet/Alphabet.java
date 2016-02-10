package alphabet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alphabet implements Iterable<Character> {

    private Set<Character> alphabet;
    private char emptySymbol;

    public Alphabet(String set) {
        this.alphabet = new HashSet<>();
        this.emptySymbol = '$';
        alphabet.add(emptySymbol);
        parseString(set);
    }

    public void addSymbol(char c) {
        alphabet.add(c);
    }

    public void deleteSymbol(char c) {
        alphabet.remove(c);
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

    private void parseString(String str) {

        Pattern pattern = Pattern.compile("\\{\\s?(('(\\S)'(,\\s?'(\\S)')*)|\\s?)\\s?\\}");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            if(matcher.group(2).length() == 0) {  // str == {\s?}
                return;
            }
            str = str.substring(1, str.length()-1);
            str = str.replaceAll("\\s+", "").replace("'", "").replaceAll(",", " ");
            String[] elements = str.split("\\s");

            for(String s : elements) {
                alphabet.add(s.charAt(0));
            }
        }
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
}
