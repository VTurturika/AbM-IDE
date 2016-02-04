package markov;

import interpreter.*;

public class MarkovCommandIndex extends CommandIndex {

    private String pattern;

    public MarkovCommandIndex(String pattern) {
        this.pattern = pattern;
    }

    public MarkovCommandIndex() {
        this("");
    }

    @Override
    public void setIndex(CommandIndex c) {
        MarkovCommandIndex commandIndex = (MarkovCommandIndex) c.getIndexInstance();
        setPattern( commandIndex.getPattern() );
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return getPattern();
    }
}
