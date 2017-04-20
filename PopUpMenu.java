import javax.swing.*;

/**
 * creates a pop up menu
 */
class PopUpMenu extends JPopupMenu {

    private final JMenuItem undo;
    private final JMenuItem redo;

    /**
     * creates a pop up menu
     *
     * @param gui an instance of the GUI
     */
    public PopUpMenu(GUI gui) {
        undo = new JMenuItem("Undo (ctrl+z)");
        redo = new JMenuItem("Redo (ctrl+y)");
        JMenuItem autoComplete = new JMenuItem("Auto complete (F1)");
        JMenuItem checkDocument = new JMenuItem("Check document (F2)");
        undo.setAction(new UndoAction("Undo (ctrl+z)", gui));
        redo.setAction(new RedoAction("Redo (ctrl+y)", gui));
        autoComplete.setAction(new AutoCompleteAction("Auto complete (F1)", gui));
        checkDocument.setAction(new CheckDocumentAction("Check document (F2)", gui));
        add(undo);
        add(redo);
        add(autoComplete);
        add(checkDocument);
    }

    /**
     * @param un true if undo has to be enabled
     */
    public void setUndo(boolean un) {
        undo.setEnabled(un);
    }

    /**
     * @param re true if redo has to be enabled
     */
    public void setRedo(boolean re) {
        redo.setEnabled(re);
    }

}