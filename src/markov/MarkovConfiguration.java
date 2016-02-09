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

    public void replace(String pattern, String replacement) {

        if(pattern.equals("")){
            markovString = replacement + markovString;
        }
        else {
            markovString = markovString.replace(pattern, replacement);
        }
    }

    @Override
    public String toString() {
        return getString() +  " [" + getString().length() + "]";
    }
}