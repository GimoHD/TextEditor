import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Creates an action to redo an action in the text area
 */
class RedoAction extends AbstractAction {
    private final GUI gui;

    /**
     * Creates a RedoAction
     * @param gui an instance of the GUI
     */
    public RedoAction(GUI gui) {
        this.gui = gui;
    }

    /**
     * Creates an Action with the specified name.
     *
     * @param redo the string to
     * @param gui  the GUI that gets passed trough
     */
    public RedoAction(String redo, GUI gui) {
        super(redo);
        this.gui = gui;
    }

    /**
     * The action that gets performed
     *
     * @param e the event that is performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getUrm().redo();
        gui.undoRedoUpdate();
    }
}
