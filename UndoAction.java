import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * An action to undo
 */
class UndoAction extends AbstractAction {
    private final GUI gui;

    public UndoAction(GUI gui) {
        this.gui = gui;
    }

    /**
     * Creates an Action with the specified name.
     *
     * @param undo the string to
     * @param gui  the GUI that gets passed trough
     */
    public UndoAction(String undo, GUI gui) {
        super(undo);
        this.gui = gui;
    }

    /**
     * The action that gets performed
     *
     * @param e the event that is performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getUrm().undo();
        gui.undoRedoUpdate();
    }
}
