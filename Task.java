class Task implements Runnable {
    private final Command ev;
    private final Boolean undo;
    private String text;

    /**
     * Pass parameters in the Runnable constructor to pass data from the callback
     *
     * @param text which will be appended with every character
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