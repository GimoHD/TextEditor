import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The action that autocompletes an opening tag
 */
class AutoCompleteAction extends AbstractAction {
    private final GUI gui;

    /**
     * Creates an Action
     *
     * @param gui the GUI that gets passed trough
     */
    public AutoCompleteAction(GUI gui) {
        this.gui = gui;
    }

    /**
     * Creates an Action with the specified name.
     *
     * @param action the string to pass to the action
     * @param gui  the GUI that gets passed trough
     */
    public AutoCompleteAction(String action, GUI gui) {
        super(action);
        this.gui = gui;
    }

    /**
     * The action that gets performed
     *
     * @param e the event that is performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getParser().autoComplete();
    }
}
