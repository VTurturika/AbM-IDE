package alphabet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetParser {

    private static final String templateSymbol = "(\\w)";
    private static final String elementOf = "((in)|(∈))";

    private static final String union = "((union)|(or)|(OR)|(\\|)|(∪))";
    private static final String intersection = "((intersect)|(and)|(AND)|(&)|(∩))";
    private static final String difference = "((\\\\)|(diff))";
    private static final String setOperation =
                                String.format("(?<operation>(%1$s)|(%2$s)|(%3$s))",union,intersection,difference);

    private static final String setItem = "('\\S')";
    private static final String predefinedSet = "((T)|(I)|(O))";
    private static final String enumerableSet = String.format("(\\{\\s?%1$s(,\\s?%1$s)*\\s?\\})",setItem);

    private static final String nonEmptySet = String.format("((%1$s)|(%2$s))", enumerableSet, predefinedSet);
    private static final String alphabetSet =
                         String.format("(((?<leftSet>%1$s)\\s%2$s\\s(?<rightSet>%1$s))|(?<singleSet>%1$s))",
                                         nonEmptySet, setOperation);

    public static boolean isValidSet(String stringSet) {

        Pattern pattern = Pattern.compile(alphabetSet);
        Matcher matcher = pattern.matcher(stringSet);

        if(matcher.find()) {

            String[] matchedGroups = new String[4];
            matchedGroups[0] = "singleSet: " + ( matcher.group("singleSet") != null ? matcher.group("singleSet") : "<empty>");
            matchedGroups[1] = "leftSet: " + (matcher.group("leftSet") != null ? matcher.group("leftSet") : "<empty>");
            matchedGroups[2] = "rightSet: " + (matcher.group("rightSet") != null ? matcher.group("rightSet") : "<empty>");
            matchedGroups[3] = "opertion: " + (matcher.group("operation") != null ? matcher.group("operation") : "<empty>");

            for(String group : matchedGroups) {
                System.out.println(group);
            }

            return true;
        }
        else {
            System.out.println("Invalid string");
            return false;
        }
    }

    public static Alphabet createAlphabet(String stringSet) {
        return null;
    }
}
