import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Kristof on 4/15/2017.
 */
class UndoAction extends AbstractAction {
    private final GUI gui;

    public UndoAction(GUI gui) {
        this.gui = gui;
    }

    public UndoAction(String redo, GUI gui) {
        super(redo);
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getUrm().undo();
        gui.undoRedoUpdate();
    }
}
