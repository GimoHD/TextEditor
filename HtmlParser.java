import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Html parser
 * Parses the html text and checks for closing tags
 */
class HtmlParser {
    private static final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
    private final Stack<HtmlTag> tagStack = new StackLL<>();
    private final GUI gui;
    private final HTML.Tag[] allTags = HTML.getAllTags();

    /**
     * Creates a HTML parser
     *
     * @param gui an instace of the GUI
     */
    HtmlParser(GUI gui) {
        this.gui = gui;
    }

    /**
     * Checks if all tags have been closed correctly (null if it is correct html)
     *
     * @return a HtmlTag that is not closed (null if everything is closed)
     */
    private HtmlTag check() {
        tagStack.reset();
        Matcher m = Pattern.compile(HTML_TAG_PATTERN).matcher(gui.getTextArea().getText());
        while (m.find()) {
            HtmlTag tag = containsTag(m);
            if (tag != null) {
                if (!tag.isBlock()) {
                    //do nothing
                } else if (!tag.isClosingTag()) {
                    tagStack.push(tag);
                    System.out.println("adding");
                } else if (tag.isClosingTag()) {
                    if (tagStack.top() != null) {
                        if (tagStack.top().toString() == tag.toString()) {
                            tagStack.pop();
                        } else if (tagStack.top().isBlock()) {
                            //do nothing
                        } else {
                            tagStack.push(tag);
                            break;
                        }
                    } else {
                        tagStack.push(tag);
                        break;
                    }
                }
            } else {
                System.out.println("FOUNT NODIHG");


            }
        }
        if (!tagStack.isEmpty()) {
            return tagStack.top();
        }

        System.out.printf("emp" + tagStack.isEmpty());
        return null;
    }

    /**
     * checks the complete document for opening tags that are not closed or if there are too many closing tags
     */
    public void documentCheck() {
        HtmlTag tag = check();
        if (tag != null) {
            try {
                if (tag.isClosingTag()) {
                    gui.getTextArea().getDocument().insertString(tag.getEnd(), "<! --Tag closing overflow: " + tag.toString() + " >", null);
                } else {
                    gui.getTextArea().getDocument().insertString(tag.getEnd(), "            <!-- Missing " + tagStack.top().toString() + " tag (Press F1 to insert closing tag at cursor)>", null);

                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Completes the last missing tag
     */
    public void autoComplete() {
        HtmlTag tag = check();
        if (tag != null) {
            try {
                if (tag.isClosingTag()) {
                } else {

                    gui.getTextArea().getDocument().insertString(gui.getTextArea().getCaretPosition(), "</ " + tag.toString() + " >", null);
                    gui.getTextArea().setCaretPosition(tag.getEnd() + 1);

                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the regex match m is contained in a HtmlTag
     *
     * @param m a match from the regex
     * @return A HtmlTag matching the tag
     */
    private HtmlTag containsTag(Matcher m) {
        HtmlTag tag_ = null;
        String tagString = m.group().replace("<", " ").replace(">", " ").replace("/", " ").trim().split(" ")[0];
        System.out.println(tagString + "lel");
        for (HTML.Tag tag : allTags) {
            // Replace first char of the tag, trim it then split with space
            if (tagString.equals(tag.toString())) {
                tag_ = new HtmlTag(tag, m.start(), m.end(), isClosingTag(m.group()));
                Debugger.print("FOUND IT");
            }
        }
        return tag_;
    }

    /**
     * checks if the tag is a closing tag
     *
     * @param tagText the tag that has to be checked
     * @return if true it is a closing tag
     */
    private boolean isClosingTag(String tagText) {
        return tagText.replace(" ", "").contains("</");
    }

}
