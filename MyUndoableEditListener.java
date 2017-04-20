import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

/**
 * An undoable listener to check for undo events to happen in the textarea
 */
class MyUndoableEditListener
        implements UndoableEditListener {
    private final GUI gui;

    /**
     * makes an instance of the MyUndoableEditListener
     * @param gui an instance of the GUI
     */
    public MyUndoableEditListener(GUI gui) {
        this.gui = gui;
    }

    /**
     * If an edit happens this will be triggered
     * @param e the edit that happened
     */
    public void undoableEditHappened(UndoableEditEvent e) {
        //Remember the edit and update the menus.
        gui.getUrm().addToUndoStack(new UndoRedoCommand(e.getEdit()));
        gui.undoRedoUpdate();
    }
}