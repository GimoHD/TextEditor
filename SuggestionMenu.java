import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class SuggestionMenu extends JPopupMenu {
    private JList<String> list;
    private String subWord;
    private int insertionPosition;
    private GUI gui;

    public SuggestionMenu(GUI gui) {
        JTextArea textArea = gui.getTextArea();
        int position = textArea.getCaretPosition() + 1;
        if (position > 0) {
            this.insertionPosition = position;
            this.gui = gui;
            removeAll();
            setOpaque(false);
            setBorder(null);
            String text = textArea.getText();
            int start = Math.max(0, position - 1);
            while (start > 0) {
                if (!Character.isWhitespace(text.charAt(start)) && text.charAt(start) != '>') {
                    start--;
                    Debugger.print("whit" + start);
                } else {
                    start++;
                    break;
                }
            }
            if (start > position) {
                return;
            }
            subWord = text.substring(start, position);
            if (subWord.length() < 2) {
                return;
            }

            Point location = null;
            try {
                location = textArea.modelToView(position).getLocation();

                add(list = createSuggestionList(position, subWord), BorderLayout.CENTER);
                this.setVisible(true);
                show(textArea, location.x, textArea.getBaseline(0, 0) + location.y);
            } catch (BadLocationException e) {
                System.out.println("Czn't place suggestonbar here");
            }

        }
        SwingUtilities.invokeLater(textArea::grabFocus);
    }


    private JList<String> createSuggestionList(final int position, final String subW) {
        String subWord = subW.replace("<", " ").replace(">", " ").trim();
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        HTML.Tag[] tags = HTML.getAllTags();

        for (HTML.Tag tag : tags) {
            if (tag.toString().contains(subWord) && tag.isBlock()) {
                model.addElement("<" + tag.toString() + ">" + "\n\n</" + tag.toString() + ">");
            } else if (tag.toString().contains(subWord) && !tag.toString().equals("html")) {
                model.addElement("<" + tag.toString() + ">");
            } else if (tag.toString().contains(subWord.replace("/", ""))) {
                model.addElement("</" + tag.toString() + ">");
            }
        }
        list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    insertSelection();
                }
            }
        });
        return list;
    }

    public void insertSelection() {
        if (list.getSelectedValue() != null) {
            try {
                final String selectedSuggestion = (list.getSelectedValue());
                gui.getTextArea().getDocument().remove(insertionPosition - subWord.length(), subWord.length());
                gui.getTextArea().getDocument().insertString(insertionPosition - subWord.length(), selectedSuggestion, null);
                gui.getTextArea().setCaretPosition(insertionPosition - subWord.length() + (selectedSuggestion.length() / 2));
                return;
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            setVisible(false);
        }
    }

    public void moveUp() {
        int index = Math.min(list.getSelectedIndex() - 1, 0);
        selectIndex(index);
    }

    public void moveDown() {
        int index = Math.min(list.getSelectedIndex() + 1, list.getModel().getSize() - 1);
        selectIndex(index);
    }

    private void selectIndex(int index) {
        final int position = gui.getTextArea().getCaretPosition();
        list.setSelectedIndex(index);
        SwingUtilities.invokeLater(() -> gui.getTextArea().setCaretPosition(position));
    }
}
