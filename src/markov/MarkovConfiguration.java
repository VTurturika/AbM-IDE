package markov;

import interpreter.*;

public class MarkovConfiguration extends Configuration {

    private String markovString;

    public MarkovConfiguration(String markovString) {
        this.markovString = markovString;
    }

    public MarkovConfiguration() {
        this("");
    }

    public String getString() {
        return markovString;
    }

    public void setString(String markovString) {
        this.markovString = markovString;
    }

    @Override
    public String toString() {
        return getString() +  " [" + getString().length() + "]";
    }
}