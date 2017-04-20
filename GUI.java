import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Simple GUI for a text editor.
 */
public class GUI extends JFrame implements DocumentListener {
    private static final long serialVersionUID = 5514566716849599754L;
    private final MenuBar menu;
    private JTextArea textArea;
    private UndoRedoManager urm;
    private SuggestionMenu suggestion;
    private HtmlParser parse;

    /**
     * Creates a GUI
     */
    public GUI() {
        super();

        Debugger.turnOn();
        setUrm(new UndoRedoManager());

        setParser(new HtmlParser(this));
        setTitle("GUI: simple text editor");
        setBounds(800, 800, 600, 600);
        setTextArea(new JTextArea(30, 80));
        getTextArea().setLineWrap(true);
        getTextArea().setWrapStyleWord(true);
        getTextArea().setText("<html>\n\n\n</html>");
        JScrollPane scrollPane = new JScrollPane(getTextArea());
        menu = new MenuBar(this);
        setJMenuBar(menu);
        add(scrollPane);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addActionInputs();
    }


    /**
     * Adds all input listeners
     */
    private void addActionInputs() {
        getTextArea().addMouseListener(new PopClickListener(this));

        getTextArea().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (suggestion != null && suggestion.isVisible()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ENTER:
                        case KeyEvent.VK_TAB: {
                            e.consume();
                            suggestion.insertSelection();
                            break;
                        }

                        case KeyEvent.VK_UP: {
                            e.consume();
                            suggestion.moveUp();
                            break;
                        }
                        case KeyEvent.VK_DOWN: {
                            suggestion.moveDown();

                        }

                        default: {
                            break;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        getTextArea().getDocument().addDocumentListener(this);
        getTextArea().getDocument().addUndoableEditListener(new MyUndoableEditListener(this));

        Action ctrl_z = new UndoAction(this);
        getTextArea().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK), "ctrl_z");
        getTextArea().getActionMap().put("ctrl_z", ctrl_z);

        Action ctrl_y = new RedoAction(this);
        getTextArea().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK), "ctrl_y");
        getTextArea().getActionMap().put("ctrl_y", ctrl_y);

        Action autoComplete = new AutoCompleteAction(this);
        getTextArea().getInputMap().put(KeyStroke.getKeyStroke("F1"), "autoComplete");
        getTextArea().getActionMap().put("autoComplete", autoComplete);

        Action checkDocument = new CheckDocumentAction(this);
        getTextArea().getInputMap().put(KeyStroke.getKeyStroke("F2"), "checkDocument");
        getTextArea().getActionMap().put("checkDocument", checkDocument);
    }

    /**
     * Updates the undo and redo from the menu
     */
    public void undoRedoUpdate() {
        menu.setUndo(urm.canUndo());
        menu.setRedo(urm.canRedo());
    }

    /**
     * Callback when changing an element
     */
    public void changedUpdate(DocumentEvent ev) {
        if (suggestion != null) {
            suggestion.setVisible(false);
        }

    }

    /**
     * Callback when deleting an element
     */
    public void removeUpdate(DocumentEvent ev) {
        if (suggestion != null) {
            suggestion.setVisible(false);
        }
    }

    /**
     * Callback when inserting an element
     */
    public void insertUpdate(DocumentEvent ev) {
        if (suggestion != null) {
            suggestion.setVisible(false);
        }
        suggestion = new SuggestionMenu(this);


    }

    /**
     * Gives the textarea where html is typed
     *
     * @return the JTextArea
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Sets the textarea
     *
     * @param textArea the textArea
     */
    private void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Gives back the UndoRedoManager that manages the undoing and redoing of the textarea
     *
     * @return an UndoRedoManager
     */
    public UndoRedoManager getUrm() {
        return urm;
    }

    /**
     * Sets the UndoRedoManager
     *
     * @param urm an UndoRedoManager
     */
    private void setUrm(UndoRedoManager urm) {
        this.urm = urm;
    }

    /**
     * Returns the HTML parser
     *
     * @return a HTML parser
     */
    public HtmlParser getParser() {
        return parse;
    }

    /**
     * sets the HTML parser
     *
     * @param parse the HTML parser
     */
    private void setParser(HtmlParser parse) {
        this.parse = parse;
    }


}