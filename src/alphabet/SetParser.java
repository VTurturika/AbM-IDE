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

    public boolean isValidSet(String stringSet) {

        return Pattern.compile(alphabetSet).matcher(stringSet).find();
    }

    public Alphabet createAlphabet(String stringSet) {

        Pattern pattern = Pattern.compile(alphabetSet);
        Matcher matcher = pattern.matcher(stringSet);

        if( matcher.find() ) {

            stringSet = matcher.group(0).replaceAll("\\s", "");
            if( matcher.group("singleSet") != null ) {   //single set definition
                return parseSingleSet(stringSet);
            }
        }

        return null;
    }

    private Alphabet parseSingleSet(String singleSet) {

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

    private boolean isPredefinedSet(String stringSet) {
        return Pattern.compile(predefinedSet).matcher(stringSet).find();
    }
}
