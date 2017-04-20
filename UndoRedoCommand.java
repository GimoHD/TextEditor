import javax.swing.undo.UndoableEdit;

/**
 * Created by Kristof on 4/15/2017.
 */
public class UndoRedoCommand implements Command {

    private final UndoableEdit ue;

    public UndoRedoCommand(UndoableEdit ue) {
        this.ue = ue;
    }

    @Override
    public void undo() {
        ue.undo();
    }

    @Override
    public void redo() {
        ue.redo();
    }
}
