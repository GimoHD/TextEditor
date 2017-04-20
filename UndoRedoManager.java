import javax.swing.*;

/**
 * Manages the undo and redo commands
 */
public class UndoRedoManager {
    final private int MAX_SIZE = 10;
    private Stack<Command> undoStack = new StackLL<>(MAX_SIZE);
    private Stack<Command> redoStack = new StackLL<>(MAX_SIZE);

    /**
     * undo the last added command
     */
    public void undo() {
        Debugger.print(getUndoStack().size() + "");
        if (getUndoStack().size() > 0) {
            Command command = getUndoStack().pop();
            getRedoStack().push(command);
            SwingUtilities.invokeLater(new Task(true, command));
        }
    }

    /**
     * redo the last added command
     */
    public void redo() {
        if (getRedoStack().size() > 0) {
            Command command = getRedoStack().pop();
            getUndoStack().push(command);
            SwingUtilities.invokeLater(new Task(false, command));
        }
    }

    /**
     * @return true if undoable
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * @return true if redoable
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * @param command add the command to the stack
     */
    public void addToUndoStack(Command command) {
        getUndoStack().push(command);
        getRedoStack().reset();
        Debugger.print(getUndoStack().size() + "");
    }

    /**
     * @param command add to the redo stack
     */
    public void addToRedoStack(Command command) {
        getRedoStack().push(command);
    }

    /**
     * get the undo stack
     * @return the undo stack
     */
    private Stack<Command> getUndoStack() {
        return undoStack;
    }

    /**
     * set the undo stack
     * @param undoStack the undo stack
     */
    public void setUndoStack(Stack<Command> undoStack) {
        this.undoStack = undoStack;
    }

    /**
     * get the redo stack
     * @return the redo stack
     */
    private Stack<Command> getRedoStack() {
        return redoStack;
    }

    /**
     * set the redo stack
     * @param redoStack the redo stack
     */
    public void setRedoStack(Stack<Command> redoStack) {
        this.redoStack = redoStack;
    }
}
