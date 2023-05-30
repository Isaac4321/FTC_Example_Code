package main.auto;

public abstract class InstructionBase   {
    protected void init() {

    }

    protected void finished() {

    }

    protected void run() {
        init();
        finished();
        execute();
    }

    protected abstract void execute();

}
