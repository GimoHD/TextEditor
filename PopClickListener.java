import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * the PopClickListener to bring up a popup menu
 */
class PopClickListener extends MouseAdapter {
    private final GUI gui;

    /**
     * Creates an instance of the PopClickListener
     * @param gui an instance of the gui
     */
    public PopClickListener(GUI gui) {
        this.gui = gui;
    }

    /**
     * Event when the mouse is pressed
     * @param e the mouse event
     */
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    /**
     * Event when the mouse is released
     * @param e the mouse event
     */
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    /**
     * Pops up a Pop up menu
     * @param e the mouse event
     */
    private void doPop(MouseEvent e) {
        PopUpMenu menu = new PopUpMenu(gui);

        menu.setUndo(gui.getUrm().canUndo());
        menu.setRedo(gui.getUrm().canRedo());

        Debugger.print("" + gui.getUrm().canUndo());

        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}