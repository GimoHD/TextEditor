import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The action that checks the html
 */
class CheckDocumentAction extends AbstractAction {
    private final GUI gui;

    /**
     * Creates an Action
     *
     * @param gui the GUI that gets passed trough
     */
    public CheckDocumentAction(GUI gui) {
        this.gui = gui;
    }

    /**
     * Creates an Action with the specified name.
     *
     * @param action the string to pass to the action
     * @param gui  the GUI that gets passed trough
     */
    public CheckDocumentAction(String redo, GUI gui) {
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
        gui.getParser().documentCheck();
    }
}
