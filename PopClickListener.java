import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PopClickListener extends MouseAdapter {
    private final GUI gui;

    public PopClickListener(GUI gui) {
        this.gui = gui;
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        PopUpMenu menu = new PopUpMenu(gui);

        menu.setUndo(gui.getUrm().canUndo());
        menu.setRedo(gui.getUrm().canRedo());
        Debugger.print("" + gui.getUrm().canUndo());
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}