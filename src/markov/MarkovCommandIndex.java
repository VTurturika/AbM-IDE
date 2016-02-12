package markov;

import interpreter.*;

public class MarkovCommandIndex extends CommandIndex {

    private int index;

    public MarkovCommandIndex(int index) {
        this.index = index;
    }

    public MarkovCommandIndex() {
        this(0);
    }

    @Override
    public void setIndex(CommandIndex c) {
        MarkovCommandIndex commandIndex = (MarkovCommandIndex) c.getIndexInstance();
        setIndex( commandIndex.getIndex() );
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
