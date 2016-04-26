package alphabet;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Realized parsing of set's definitions, creates Alphabet and TemplateSymbol from string of set's definition
 */
public class TemplateParser {

    //pattern of TemplateSymbol alias for matching
    private static final String templateSymbol = "(\\w)";
    private static final String elementOf = "((in)|(∈))";   //pattern of elementOf operation

    private static final String setItem = "('\\S')";                //patten of item of set
    private static final String predefinedSet = "((T)|(I)|(O))";    //patten of Predefined Sets

    //pattern of enumerable set ( like this {'1','2','3'} )
    private static final String enumerableSet = String.format("(\\{\\s?%1$s(,\\s?%1$s)*\\s?\\})", setItem);

    //patten of non empty set ( like this {'1','2','3'} or this T, I )
    private static final String nonEmptySet = String.format("((%1$s)|(%2$s))", enumerableSet, predefinedSet);

    private static final String kleeneStar = String.format("((\\s?%1$s\\s?)\\*)",nonEmptySet);
    private static final String kleenePlus = String.format("((\\s?%1$s\\s?)\\+)",nonEmptySet);

    private static final String correctSet= String.format("((%1$s)|(%2$s)|(%3$s))",
                                                                kleenePlus, kleeneStar, nonEmptySet);

    private static final String simpleDefinition =  String.format("(%1$s(\\,\\s?%1$s)*\\s%2$s\\s%3$s)",
                                                     templateSymbol, elementOf, correctSet);
    private static final String parsingDefinition = String.format("((?<elem>%1$s(\\,\\s?%1$s)*)\\s%2$s\\s(?<set>%3$s))",
                                                                templateSymbol, elementOf, correctSet);

    private static final String correctDefinition =
            String.format(":\\s%1$s(\\,\\s?%1$s)*", simpleDefinition);

    /**
     * Checks if specified string is correct set definition
     *
     * @param stringSet string that will be checked
     * @return {@code true} if specified string is correct, else returns {@code false}
     */
    public boolean isValidSet(String stringSet) {

        return Pattern.compile(correctSet).matcher(stringSet).find();
    }

    /**
     * Checks if specified string is {@code TemplateSymbol} definition
     *
     * @param stringSet string that will be checked
     * @return {@code true} if specified string is correct, else returns {@code false}
     */
    public boolean isValidDefinition(String stringSet) {

        return Pattern.compile(correctDefinition).matcher(stringSet).find();
    }

    /**
     * Checks definition has more than one template symbol
     *
     * @param definition definition of {@code TemplateSymbol}
     * @return {@code true} if definition has more than one template symbol, else return false
     * @throws Exception if specified definition of {@code TemplateSymbol} isn't valid
     */
    public boolean hasMoreOneTemplateSymbol(String definition) throws Exception {

        Pattern pattern = Pattern.compile(String.format("%1$s(\\,\\s?%1$s)+\\s%2$s", templateSymbol, elementOf));
        Matcher matcher = pattern.matcher(definition);

        if(matcher.find()) {
            return true;
        }
        else {
            pattern = Pattern.compile(templateSymbol + "\\s" + elementOf);
            matcher = pattern.matcher(definition);
            int count = 0;

            while(matcher.find()) count++;

            if(count == 0) {
                throw new Exception("Wrong syntax");
            }
            else {
                return count != 1;
            }
        }
    }


    /**
     * Parses and creates {@code Alphabet} that defined by string
     *
     * @param setDefinition string of set's definition
     * @return {@code Alphabet} that defined by {@code setDefinition} string
     */
    private Alphabet parseSet(String setDefinition) {

        setDefinition = setDefinition.replaceAll("\\s", "");

        if (isPredefinedSet(setDefinition)) {
            //do something interesting
            return null;
        } else {
            if(chooseStringMode(setDefinition) == TemplateSymbol.TemplateStringMode.NONE) {
                setDefinition = setDefinition.substring(1, setDefinition.length() - 1); // erase {}
            }
            else {
                setDefinition = setDefinition.substring(1, setDefinition.length() - 2); // erase {}* or {}+
            }

            setDefinition = setDefinition.replace("'", "")            //erase '
                    .replace(",{3}", ",comma,")  //save comma symbol within setString
                    .replace(",$", "comma");    //save comma symbol in the end of setString

            String[] symbols = setDefinition.split(",");

            Alphabet result = new Alphabet();

            for (String s : symbols) {
                if (s.equals("comma")) {
                    result.addSymbol(',');
                } else {
                    result.addSymbol(s.charAt(0));
                }
            }

            return result;
        }
    }

    private TemplateSymbol.TemplateStringMode chooseStringMode(String definition) {

        if(definition.contains("}*")) {
            return TemplateSymbol.TemplateStringMode.STRING;
        }
        else if(definition.contains("}+")) {
            return TemplateSymbol.TemplateStringMode.NONEMPTY_STRING;
        }
        else {
            return TemplateSymbol.TemplateStringMode.NONE;
        }
    }

    /**
     * Checks if specified set is predefined set
     *
     * @param stringSet specified set that will be checked
     * @return {@code true} if set is a predefined set, else returns {@code false}
     */
    private boolean isPredefinedSet(String stringSet) {
        return Pattern.compile(predefinedSet).matcher(stringSet).find();
    }


    /**
     * Creates and returns {@code TemplateSymbol} from string of set's definition
     *
     * @param definition string of set's definition
     * @return {@code TemplateSymbol} from string of set's definition
     * @throws Exception if the string of set's definition is invalid
     */
    public TemplateSymbol createTemplateSymbol(String definition) throws Exception {

        if(hasMoreOneTemplateSymbol(definition))
            throw new Exception("Has more than one TemplateSymbol");

        Pattern pattern = Pattern.compile(parsingDefinition);
        Matcher matcher = pattern.matcher(definition);

        if(matcher.find()) {

            if(matcher.group("elem") != null && matcher.group("set") != null) {

                Alphabet alphabet = parseSet(matcher.group("set"));
                TemplateSymbol.TemplateStringMode mode = chooseStringMode(definition);
                char alias = matcher.group("elem").charAt(0);

                return new TemplateSymbol(alias, alphabet, mode);
            }
        }

        throw new Exception();
    }

    public TemplateSymbol[] createTemplateSymbols (String definition) throws Exception {

        Pattern pattern = Pattern.compile(parsingDefinition);
        Matcher matcher = pattern.matcher(definition);
        ArrayList<TemplateSymbol> result = new ArrayList<>();
        Alphabet alphabet;
        TemplateSymbol.TemplateStringMode mode;

        while (matcher.find()) {

            if(matcher.group("elem") != null && matcher.group("set") != null) {

                alphabet = parseSet(matcher.group("set"));
                mode = chooseStringMode(matcher.group());

                String elems = matcher.group("elem").replaceAll("\\s", "");
                String[] symbols = elems.split(",");

                for(String s : symbols) {

                    result.add(new TemplateSymbol(s.charAt(0), alphabet, mode));
                }
            }
        }

        if( !result.isEmpty() ) {

            return result.toArray(new TemplateSymbol[result.size()]);
        }
        else {
            throw new Exception("Wrong syntax!");
        }
    }
}
