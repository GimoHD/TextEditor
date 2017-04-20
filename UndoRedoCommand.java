import javax.swing.undo.UndoableEdit;

/**
 * The command to undo and redo the actions in the text area
 */
public class UndoRedoCommand implements Command {

    private final UndoableEdit ue;

    /**
     * Creates an undoable edit command
     * @param ue an undoable edit
     */
    public UndoRedoCommand(UndoableEdit ue) {
        this.ue = ue;
    }

    /**
     * Undo's the command
     */
    @Override
    public void undo() {
        ue.undo();
    }

    /**
     * Redo's the command
     */
    @Override
    public void redo() {
        ue.redo();
    }
}
