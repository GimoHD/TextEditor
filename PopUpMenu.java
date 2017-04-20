import javax.swing.*;

class PopUpMenu extends JPopupMenu {

    private final JMenuItem undo;
    private final JMenuItem redo;
    private final JMenuItem autoComplete;
    private final JMenuItem checkDocument;

    public PopUpMenu(GUI gui) {
        undo = new JMenuItem("Undo (ctrl+z)");
        redo = new JMenuItem("Redo (ctrl+y)");
        autoComplete = new JMenuItem("Auto complete (F1)");
        checkDocument = new JMenuItem("Check document (F2)");
        undo.setAction(new UndoAction("Undo (ctrl+z)", gui));
        redo.setAction(new RedoAction("Redo (ctrl+y)", gui));
        autoComplete.setAction(new AutoCompleteAction("Auto complete (F1)", gui));
        checkDocument.setAction(new AutoCompleteAction("Check document (F2)", gui));
        add(undo);
        add(redo);
        add(autoComplete);
        add(checkDocument);
    }

    public void setUndo(boolean un) {
        undo.setEnabled(un);
    }

    public void setRedo(boolean re) {
        redo.setEnabled(re);
    }

}