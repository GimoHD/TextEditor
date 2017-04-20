import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

class MyUndoableEditListener
        implements UndoableEditListener {
    private final GUI gui;

    public MyUndoableEditListener(GUI gui) {
        this.gui = gui;
    }

    public void undoableEditHappened(UndoableEditEvent e) {
        //Remember the edit and update the menus.
        gui.getUrm().addToUndoStack(new UndoRedoCommand(e.getEdit()));
        gui.undoRedoUpdate();
    }
}