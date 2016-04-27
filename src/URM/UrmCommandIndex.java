package URM;

import interpreter.*;

/**
 * Implements {@code CommandIndex} for URM's {@code Program}
 */
public class UrmCommandIndex extends CommandIndex {

    private int index;

    UrmCommandIndex(int i) {

        this.index = i;
    }

    UrmCommandIndex() {
        this.index = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void setIndex(CommandIndex c) {

        UrmCommandIndex commandIndex = (UrmCommandIndex) c.getIndexInstance();
        setIndex(commandIndex.getIndex());
    }

    /**
     * Increments internal value of CommandIndex
     */
    public void nextIndex() {
        index++;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
