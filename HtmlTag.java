import javax.swing.text.html.HTML;

/**
 * Created by Kristof on 4/9/2017.
 */
class HtmlTag extends HTML.Tag {
    private boolean closingTag;
    private int start;
    private  int end;

    public HtmlTag(HTML.Tag tag,int start, int end, boolean closingTag) {
        super(tag.toString(), tag.breaksFlow(), tag.isBlock());
        this.setClosingTag(closingTag);
        this.setEnd(end);
        this.setStart(start);

    }

    public static boolean parse(String tagText) {
        tagText = tagText.trim();
        boolean isOpenTag = !tagText.contains("</");
        String element = tagText.replaceAll("[^a-zA-Z!-?]+", "");
        if (element.contains("!--")) {
            element = "!--";  // HTML comments
        }
        return true;
    }

    void checkTag() {

    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString()) && obj instanceof HtmlTag;
    }

    public boolean isClosingTag() {
        return closingTag;
    }

    public void setClosingTag(boolean closingTag) {
        this.closingTag = closingTag;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
