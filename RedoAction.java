import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Kristof on 4/15/2017.
 */
class RedoAction extends AbstractAction {
    private final GUI gui;

    public RedoAction(GUI gui) {
        this.gui = gui;
    }

    public RedoAction(String redo, GUI gui) {
        super(redo);
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getUrm().redo();
        gui.undoRedoUpdate();
    }
}
