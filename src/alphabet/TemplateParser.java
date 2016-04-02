package alphabet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Realized parsing of set's definitions, creates Alphabet and TemplateSymbol from string of set's definition
 */
public class TemplateParser {

    private static final String templateSymbol = "(?<template>\\w)";    //pattern of TemplateSymbol alias
    private static final String elementOf = "(?<elementOf>(in)|(∈))";   //pattern of elementOf operation

    //patterns of union, intersection and difference operations
    private static final String union = "((union)|(or)|(OR)|(\\|)|(∪))";
    private static final String intersection = "((intersect)|(and)|(AND)|(&)|(∩))";
    private static final String difference = "((\\\\)|(diff))";

    //general pattern for set operations
    private static final String setOperation =
                                String.format("(?<operation>(%1$s)|(%2$s)|(%3$s))",union,intersection,difference);

    private static final String setItem = "('\\S')";                //patten of item of set
    private static final String predefinedSet = "((T)|(I)|(O))";    //patten of Predefined Sets

    //pattern of enumerable set ( like this {'1','2','3'} )
    private static final String enumerableSet = String.format("(\\{\\s?%1$s(,\\s?%1$s)*\\s?\\})",setItem);

    //patten of non empty set ( like this {'1','2','3'} or this T, I )
    private static final String nonEmptySet = String.format("((%1$s)|(%2$s))", enumerableSet, predefinedSet);

    //general pattern of set, allows set's definitions:
    // 1) all available nonEmptySet's definitions
    // 2) set's definitions like this <Set> <SetOperator> <Set>
    private static final String alphabetSet =
                         String.format("(((?<leftSet>%1$s)\\s%2$s\\s(?<rightSet>%1$s))|(?<singleSet>%1$s))",
                                         nonEmptySet, setOperation);

    //main pattern of definition of template symbol
    private static final String templateDefinition =
                                String.format(":\\s?%1$s\\s?%2$s\\s?(?<set>%3$s)",
                                              templateSymbol,elementOf, alphabetSet);

    public boolean isValidSet(String stringSet) {

        return Pattern.compile(alphabetSet).matcher(stringSet).find();
    }

    /**
     * Creates {@code Alphabet} from set's definition string
     *
     * @param stringSet set definition as string
     * @return {@code Alphabet} that defined by string of set's definition
     * @throws Exception if specified set's definition isn't valid
     */
    public Alphabet createAlphabet(String stringSet) throws Exception {

        Pattern pattern = Pattern.compile(alphabetSet);
        Matcher matcher = pattern.matcher(stringSet);

        if( matcher.find() ) {

            if( matcher.group("singleSet") != null ) {   //single set definition
                return parseSingleSet(matcher.group("singleSet"));
            }
            else { // definition like this: "<Set> <Operation> <Set>"
                Alphabet leftSet  = parseSingleSet(matcher.group("leftSet"));
                Alphabet rightSet = parseSingleSet(matcher.group("rightSet"));

                try {
                    switch (getSetOperation(matcher.group("operation"))) {
                        case "union":
                            return leftSet.union(rightSet);
                        case "intersection":
                            return leftSet.intersection(rightSet);
                        case "difference":
                            return leftSet.difference(rightSet);
                        default:
                            throw new UnsupportedOperationException("Wrong operation");
                    }
                }
                catch (Exception e) {
                    throw new Exception("Set Operation Exception: " + e.getMessage());
                }
            }
        }
        throw new Exception();
    }

    /**
     * Parses and creates {@code Alphabet} that defined by string without set's operations
     *
     * @param singleSet string of set's definition
     * @return {@code Alphabet} that defined by {@code singleSet} string
     */
    private Alphabet parseSingleSet(String singleSet) {

        singleSet = singleSet.replaceAll("\\s", "");

        if(isPredefinedSet(singleSet)) {
            //do something interesting
            return null;
        }
        else {
            singleSet = singleSet.substring(1, singleSet.length()-1); // erase {}

            singleSet = singleSet.replace("'","")            //erase '
                                 .replace(",{3}",",comma,")  //save comma symbol within setString
                                 .replace(",$", "comma");    //save comma symbol in the end of setString

            String[] symbols = singleSet.split(",");

            Alphabet result = new Alphabet();

            for(String s : symbols) {
                if(s.equals("comma")) {
                    result.addSymbol(',');
                }
                else {
                    result.addSymbol(s.charAt(0));
                }
            }

            return result;
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
     * Service method that determines type of set operations
     * @param setOperation string of variant of set operation
     * @return string that uniquely defines set operation
     */
    private String getSetOperation(String setOperation) {
        if (setOperation.matches(union)) {
            return "union";
        }
        else if (setOperation.matches(intersection)) {
            return "intersection";
        }
        else if (setOperation.matches(difference)) {
            return "difference";
        }
        else {
            return "wrongOperation";
        }
    }

    /**
     * Creates and returns {@code TemplateSymbol} from string of set's definition
     *
     * @param definition string of set's definition
     * @return {@code TemplateSymbol} from string of set's definition
     * @throws Exception if the string of set's definition is invalid
     */
    public TemplateSymbol createTemplateSymbol(String definition) throws Exception {

        Pattern pattern = Pattern.compile(templateDefinition);
        Matcher matcher = pattern.matcher(definition);

        if(matcher.find()) {

            try {
                return new TemplateSymbol(matcher.group("template").charAt(0),createAlphabet(definition));
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }

        }
        else {
            throw new Exception();
        }
    }
}
