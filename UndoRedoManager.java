import javax.swing.*;

/**
 * Created by Kristof on 4/9/2017.
 */
public class UndoRedoManager {
    final private int MAX_SIZE = 10;
    private Stack<Command> undoStack = new StackLL<>(MAX_SIZE);
    private Stack<Command> redoStack = new StackLL<>(MAX_SIZE);

    public void undo() {
        Debugger.print(getUndoStack().size() + "");
        if (getUndoStack().size() > 0) {
            Command command = getUndoStack().pop();
            getRedoStack().push(command);
            SwingUtilities.invokeLater(new Task(true, command));
        }
    }

    public void redo() {
        if (getRedoStack().size() > 0) {
            Command command = getRedoStack().pop();
            getUndoStack().push(command);
            SwingUtilities.invokeLater(new Task(false, command));
        }
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    public void addToUndoStack(Command command) {
        getUndoStack().push(command);
        getRedoStack().reset();
        Debugger.print(getUndoStack().size() + "");
    }

    public void addToRedoStack(Command command) {
        getRedoStack().push(command);
    }

    private Stack<Command> getUndoStack() {
        return undoStack;
    }

    public void setUndoStack(Stack<Command> undoStack) {
        this.undoStack = undoStack;
    }

    private Stack<Command> getRedoStack() {
        return redoStack;
    }

    public void setRedoStack(Stack<Command> redoStack) {
        this.redoStack = redoStack;
    }
}
