package markov;

import alphabet.Alphabet;

public class TemplateSymbol {

    private char alias;
    private Alphabet alphabet;
    private boolean isTemplateString;

    public TemplateSymbol(char alias, Alphabet alphabet, boolean isTemplateString) {
        this.alias = alias;
        this.alphabet = alphabet;
        this.isTemplateString = isTemplateString;
    }

    public TemplateSymbol(char alias, Alphabet alphabet) {
        this(alias, alphabet, false);
    }

    public char getAlias() {
        return alias;
    }

    public void setAlias(char alias) {
        this.alias = alias;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public void setTemplateString(boolean flag) {
        isTemplateString = flag;
    }

    public boolean isTemplateString() {
        return isTemplateString;
    }
}
