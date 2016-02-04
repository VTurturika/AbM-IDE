package turing;

public enum Direction {
    LEFT, NONE, RIGHT;

    @Override
    public String toString() {

        switch (this.ordinal()) {
            case 0:
                return "L";
            case 1:
                return "";
            case 2:
                return "R";
        }
        return "";
    }
}
