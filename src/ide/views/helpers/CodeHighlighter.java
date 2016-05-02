package ide.views.helpers;

import ide.logic.alphabet.EmptySymbol;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeHighlighter {

    private static final String COMMENT = "(?<comment>\\/\\/.*)";
    private static final String URM_COMMAND_NUMBERS = "(?<commandNumber>(^|\\n)\\d+[\\)\\.])";
    private static final String URM_COMMAND_TYPE = "(?<commandType>[zZsStTjJ])";

    private Pattern urmPattern = Pattern.compile(String.format("(%1$s|%2$s|%3$s)",
                                                 COMMENT, URM_COMMAND_NUMBERS, URM_COMMAND_TYPE));

    private static final String ARROW = "(?<arrow>\\-\\>)";
    private static final String SYMBOL = "(?<symbol>\\S)";
    private static final String EMPTY_SYMBOL = String.format("(?<empty>\\%1$c)", EmptySymbol.get());

    private static final String TURING_STATE = "(?<state>q\\s?(\\d+)|(\\*))";
    private static final String TURING_DIRECTION = "(?<direction>L|R)";

    private Pattern turingPattern = Pattern.compile(String.format("(%1$s|%2$s|%3$s|%4$s|%5$s|%6$s)",
                                                    COMMENT,TURING_STATE,TURING_DIRECTION,ARROW,EMPTY_SYMBOL,SYMBOL));

    private static final String ARROW_WITH_DOT = "(?<arrowWithDot>\\-\\>\\.)";
    private Pattern markovPattern = Pattern.compile(String.format("(%1$s|%2$s|%3$s|%4$s|%5$s)",
                                                   COMMENT,ARROW_WITH_DOT,ARROW,EMPTY_SYMBOL,SYMBOL ));


    public void highlightUrmCode(CodeArea code) {

        code.clearStyle(0, code.getText().length());

        Matcher matcher = urmPattern.matcher(code.getText());
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(matcher.find()) {
            String styleClass = matcher.group("comment") != null ? "comment" :
                                matcher.group("commandNumber") != null ? "commandNumber" :
                                matcher.group("commandType") != null ? "commandType" : null;

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), code.getText().length() - lastKwEnd);

        code.setStyleSpans(0, spansBuilder.create());
    }

    public void highlightTuringCode(CodeArea code) {

        code.clearStyle(0, code.getText().length());

        Matcher matcher = turingPattern.matcher(code.getText());
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(matcher.find()) {
            String styleClass = matcher.group("comment") != null ? "comment" :
                                matcher.group("state") != null ? "state" :
                                matcher.group("direction") != null ? "direction" :
                                matcher.group("arrow") != null ? "arrow" :
                                matcher.group("empty") != null ? "emptySymbol" :
                                matcher.group("symbol") != null ? "symbol" : null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), code.getText().length() - lastKwEnd);

        code.setStyleSpans(0, spansBuilder.create());
    }

    public void highlightMarkovCode(CodeArea code) {

        code.clearStyle(0, code.getText().length());

        Matcher matcher = markovPattern.matcher(code.getText());
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(matcher.find()) {
            String styleClass = matcher.group("comment") != null ? "comment" :
                                matcher.group("arrowWithDot") != null ? "arrowWithDot" :
                                matcher.group("arrow") != null ? "arrow" :
                                matcher.group("empty") != null ? "emptySymbol" :
                                matcher.group("symbol") != null ? "symbol" : null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), code.getText().length() - lastKwEnd);

        code.setStyleSpans(0, spansBuilder.create());
    }
}
