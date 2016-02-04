package interpreter;

abstract public class Command {

    boolean stop;

    public Command() {
        stop = false;
    }

    public boolean isStop() {
        return stop;
    }
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Object getInstance() {
        return this;
    }
}
