import javax.swing.text.html.HTML;

/**
 * A html tag extending a HTML.Tag
 */
class HtmlTag extends HTML.Tag {
    private boolean closingTag;
    private int start;
    private int end;

    /**
     * Creates a HTML tag
     *
     * @param tag        a HTML.Tag
     * @param start      the start location where the tag is located in a JTextArea
     * @param end        the end location where the tag is located in a JTextArea
     * @param closingTag true if it is a closing tag
     */
    public HtmlTag(HTML.Tag tag, int start, int end, boolean closingTag) {
        super(tag.toString(), tag.breaksFlow(), tag.isBlock());
        this.setClosingTag(closingTag);
        this.setStart(start);
        this.setEnd(end);
        this.setStart(start);
    }

    /**
     * it is a closing tag
     *
     * @return true if it is a closing tag
     */
    public boolean isClosingTag() {
        return closingTag;
    }

    /**
     * sets if it is a closing tag or not
     *
     * @param closingTag
     */
    private void setClosingTag(boolean closingTag) {
        this.closingTag = closingTag;
    }

    /**
     * gets the start location of the tag
     *
     * @return the start location of the tag
     */
    public int getStart() {
        return start;
    }

    /**
     * sets the start location
     *
     * @param start the start location
     */
    private void setStart(int start) {
        this.start = start;
    }

    /**
     * gets the end location
     *
     * @return the end location
     */
    public int getEnd() {
        return end;
    }

    /**
     * sets the end location
     *
     * @param end the end location
     */
    private void setEnd(int end) {
        this.end = end;
    }
}
