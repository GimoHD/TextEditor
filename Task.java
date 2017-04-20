/**
 * A task to undo and redo
 */
class Task implements Runnable {
    private final Command ev;
    private final Boolean undo;

    /**
     * A task to undo or redo
     * @param undo true if it is an undo task
     * @param ev a command
     */
    Task(Boolean undo, Command ev) {
        this.ev = ev;
        this.undo = undo;
    }


    /**
     * The entry point of the runnable
     */
    public void run() {
        if (undo) {
            ev.undo();
        } else {
            ev.redo();
        }
    }


}