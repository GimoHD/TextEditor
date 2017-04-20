import javax.swing.*;

/**
 * Created by Kristof on 4/16/2017.
 */
class MenuBar extends JMenuBar {
    private final JMenuItem undo;
    private final JMenuItem redo;
    private final JMenuItem autoComplete;
    private final JMenuItem checkDocument;

    public MenuBar(GUI gui) {
        JMenu mi = new JMenu("Undo & redo menu");
        undo = new JMenuItem("Undo (ctrl+z)");
        redo = new JMenuItem("Redo (ctrl+y)");
        autoComplete = new JMenuItem("Auto complete (F1)");
        checkDocument = new JMenuItem("Check document (F2)");
        undo.setAction(new UndoAction("Undo (ctrl+z)", gui));
        redo.setAction(new RedoAction("Redo (ctrl+y)", gui));
        autoComplete.setAction(new AutoCompleteAction("Auto complete (F1)", gui));
        checkDocument.setAction(new AutoCompleteAction("Check document (F2)", gui));
        mi.add(undo);
        mi.add(redo);
        mi.add(autoComplete);
        mi.add(checkDocument);
        this.add(mi);
        setUndo(gui.getUrm().canUndo());
        setRedo(gui.getUrm().canRedo());
    }

    public void setUndo(boolean un) {
        undo.setEnabled(un);
    }

    public void setRedo(boolean re) {
        redo.setEnabled(re);
    }


}
