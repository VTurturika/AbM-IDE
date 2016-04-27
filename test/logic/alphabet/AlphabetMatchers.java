package logic.alphabet;

import org.hamcrest.*;
import static org.junit.Assert.assertThat;

/**
 * Implements special matchers for testing classes from logic.alphabet package
 */
public class AlphabetMatchers {

    /**
     * Returns {@code Matcher} that will be used for alphabets comparing by assertThat() method
     *
     * @param expected {@code Alphabet} that will be compared with actual {@code Alphabet}
     * @return {@code Matcher} that will be used for alphabets comparing by assertThat() method
     */
    static Matcher isAlphabet(final Alphabet expected) {

        return new BaseMatcher() {

            @Override
            public boolean matches(java.lang.Object o) {
                if(o instanceof Alphabet) {

                    Alphabet actual = ((Alphabet) o);
                    return actual.equals(expected);
                }
                else return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(expected.toString());
            }
        };
    }

    /**
     * Returns {@code Matcher} that will be used for TemplateSymbols comparing by assertThat() method
     *
     * @param expected {@code TemplateSymbol} that will be compared with actual {@code TemplateSymbol}
     * @return {@code Matcher} that will be used for TemplateSymbols comparing by assertThat() method
     */
    static Matcher isTemplateSymbol(TemplateSymbol expected) {

        return new BaseMatcher() {
            @Override
            public boolean matches(Object o) {
                if(o instanceof TemplateSymbol) {

                    TemplateSymbol actual = (TemplateSymbol) o;
                    return actual.equals(expected);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(expected.toString());
            }
        };
    }
}
