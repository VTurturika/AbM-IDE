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

    public void replace(String pattern, String replacer) {

        if(pattern.equals("")){
            markovString = replacer + markovString;
        }
        else {
            markovString = markovString.replace(pattern, replacer);
        }
    }

    @Override
    public String toString() {
        return getString();
    }
}
