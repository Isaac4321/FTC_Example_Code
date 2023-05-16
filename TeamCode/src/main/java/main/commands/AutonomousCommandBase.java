package main.commands;

public abstract class AutonomousCommandBase {
    public void run() {
        start();
        end();
    }

    protected abstract void start();
    protected void end() {

    }
}
