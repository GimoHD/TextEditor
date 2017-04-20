/**
 * Thee interface for a command to do undo and redo
 */
interface Command {

    /**
     * An undo action as the interface
     */
    void undo();

    /**
     * A redo action as the interface
     */
    void redo();
}