import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Kristof on 4/15/2017.
 */
class AutoCompleteAction extends AbstractAction {
    private final GUI gui;

    public AutoCompleteAction(GUI gui) {
        this.gui = gui;
    }

    public AutoCompleteAction(String redo, GUI gui) {
        super(redo);
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getParser().autoComplete();
    }
}
