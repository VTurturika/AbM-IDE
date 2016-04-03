package alphabet;

/**
 * Represents "template symbol" for Markov Algorithms command
 */
public class TemplateSymbol {

    /**Alias of template symbol*/
    private char alias;
    /**Alphabet that includes this template symbol*/
    private Alphabet alphabet;
    /**Shows that template symbol means string from {@code Alphabet}*/
    private boolean isTemplateString;

    /**Determines type of string mode of {@code TemplateSymbol}*/
    public enum TemplateStringMode {
        /**Means that this {@code TemplateSymbol} isn't "Template string", it's just a symbol from {@code Alphabet}*/
        NONE,
        /**Means that this {@code TemplateSymbol} is a "Template string" and empty string allowed*/
        STRING,
        /**Means that this {@code TemplateSymbol} is a "Template string" and empty string not allowed*/
        NONEMPTY_STRING;

        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0:
                    return "NONE";
                case 1:
                    return "STRING";
                case 2:
                    return "NONEMPTY_STRING";

                default:  return "";
            }
        }
    }

    private TemplateStringMode mode;

    /**
     * Fully constructor of {@code TemplateSymbol}
     *
     * @param alias alias of {@code TemplateSymbol}
     * @param alphabet {@code Alphabet} that includes this symbol
     * @param isTemplateString  shows that template symbol means string from {@code Alphabet}
     * @param mode if {@code TemplateSymbol} means TemplateString determines its type
     */
    public TemplateSymbol(char alias, Alphabet alphabet,
                          boolean isTemplateString, TemplateSymbol.TemplateStringMode mode) {
        this.alias = alias;
        this.alphabet = alphabet;
        this.isTemplateString = isTemplateString;
        this.mode = mode;

    }

    public TemplateSymbol(char alias, Alphabet alphabet, boolean isTemplateString) {
        this(alias, alphabet, isTemplateString, TemplateStringMode.STRING);
    }

    public TemplateSymbol(char alias, Alphabet alphabet) {
        this(alias, alphabet, false, TemplateStringMode.NONE);
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

    public TemplateSymbol.TemplateStringMode getTemplateStringMode() {
        return mode;
    }

    public void setTemplateStringMode(TemplateSymbol.TemplateStringMode mode) {

        if(isTemplateString()) {
            this.mode = mode;
        }
    }

    public boolean isTemplateString() {
        return isTemplateString;
    }

    @Override
    public String toString() {

        return "Alias: " + getAlias() + "\n" +
               "Alphabet: " + getAlphabet() + "\n" +
               "isTemplateString: " + isTemplateString() + "\n" +
                "templateStringMode: " + getTemplateStringMode() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TemplateSymbol) {

            TemplateSymbol another = (TemplateSymbol) obj;
            return this.getAlphabet().equals(another.getAlphabet()) &&
                   this.isTemplateString() == another.isTemplateString() &&
                   this.getTemplateStringMode() == another.getTemplateStringMode();
        }
        else return false;
    }
}
