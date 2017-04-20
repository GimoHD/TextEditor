import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Simple GUI for a text editor.
 */
public class GUI extends JFrame implements DocumentListener {
    private static final long serialVersionUID = 5514566716849599754L;
    private JTextArea textArea;
    private UndoRedoManager urm;
    private final MenuBar menu;
    private SuggestionMenu suggestion;
    private boolean active = true;
    private HtmlParser parse;
    /**
     * Constructs a new GUI: A TextArea on a ScrollPane
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addActionInputs();
    }


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
        //Check if the change is only a single character, otherwise return so it does not go in an infinite loop
        //if(active && ev.getLength()>0)
        //	SwingUtilities.invokeLater(new Task(this,ev));
        if (suggestion != null) {
            suggestion.setVisible(false);
        }
            suggestion = new SuggestionMenu(this);


    }

    public void disableEvents() {
        this.active = false;
    }

    public void enableEvents() {
        this.active = true;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    private void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public UndoRedoManager getUrm() {
        return urm;
    }

    private void setUrm(UndoRedoManager urm) {
        this.urm = urm;
    }

    public HtmlParser getParser() {
        return parse;
    }

    public void setParser(HtmlParser parse) {
        this.parse = parse;
    }

    /**
     * Runnable: change UI elements as a result of a callback
     * Start a new Task by invoking it through SwingUtilities.invokeLater
     */


}