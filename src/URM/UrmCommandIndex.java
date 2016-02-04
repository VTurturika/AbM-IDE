package URM;

import interpreter.*;

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

    public void nextIndex() {
        index++;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
