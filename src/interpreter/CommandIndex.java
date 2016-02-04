package interpreter;

abstract public class CommandIndex {

    public Object getIndexInstance() {
        return this;
    }
    abstract public void setIndex(CommandIndex c);

}
