package ide.logic.turing;

import ide.logic.interpreter.CommandIndex;

/**
 * Implements {@code CommandIndex} for {@code TuringProgram}
 */
public class TuringCommandIndex extends CommandIndex {

    /**
     * Allows get Command from Program using state and symbol of tape
     */
    private int state;
    private char symbol;

    public TuringCommandIndex(int state, char symbol) {
        this.state = state;
        this.symbol = symbol;
    }

    public TuringCommandIndex() {
        this(0, '$');
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void setIndex(CommandIndex c) {
        TuringCommandIndex commandIndex = (TuringCommandIndex) c.getIndexInstance();
        setState(commandIndex.getState());
        setSymbol(commandIndex.getSymbol());
    }

    @Override
    public int hashCode() {
        return Character.hashCode(symbol) + Integer.hashCode(state);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TuringCommandIndex) {

            TuringCommandIndex i = (TuringCommandIndex) obj;
            return state == i.getState() && symbol == i.getSymbol();
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {

        return "q" + getState() + " " + getSymbol();
    }
}
