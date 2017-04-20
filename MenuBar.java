import javax.swing.*;

/**
 * creates a MenuBar for a GUI
 */
class MenuBar extends JMenuBar {
    private final JMenuItem undo;
    private final JMenuItem redo;

    /**
     * creates a menubar
     *
     * @param gui an instance of the GUI
     */
    public MenuBar(GUI gui) {
        JMenu mi = new JMenu("Undo & redo menu");
        undo = new JMenuItem("Undo (ctrl+z)");
        redo = new JMenuItem("Redo (ctrl+y)");
        JMenuItem autoComplete = new JMenuItem("Auto complete (F1)");
        JMenuItem checkDocument = new JMenuItem("Check document (F2)");
        undo.setAction(new UndoAction("Undo (ctrl+z)", gui));
        redo.setAction(new RedoAction("Redo (ctrl+y)", gui));
        autoComplete.setAction(new AutoCompleteAction("Auto complete (F1)", gui));
        checkDocument.setAction(new CheckDocumentAction("Check document (F2)", gui));
        mi.add(undo);
        mi.add(redo);
        mi.add(autoComplete);
        mi.add(checkDocument);
        this.add(mi);
        setUndo(gui.getUrm().canUndo());
        setRedo(gui.getUrm().canRedo());
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
