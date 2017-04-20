import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kristof on 4/9/2017.
 */
class HtmlParser {
    private static final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
    private final Stack<HtmlTag> tagStack = new StackLL<>();
    private final GUI gui;
    private HTML.Tag[] allTags = HTML.getAllTags();

    HtmlParser(GUI gui) {
        this.gui = gui;
    }

    public HtmlTag check() {
        tagStack.reset();
        Matcher m = Pattern.compile(HTML_TAG_PATTERN).matcher(gui.getTextArea().getText());
        while (m.find()) {
            HtmlTag tag = containsTag(m);
            if (tag !=null) {
                if (!tag.isBlock()){
                    //do nothing
                }else if (!tag.isClosingTag()){
                    tagStack.push(tag);
                    System.out.println("adding");
                } else if (tag.isClosingTag()) {
                    if (tagStack.top() !=null ) {
                        if (tagStack.top().toString() == tag.toString()) {
                            tagStack.pop();
                        } else if (tagStack.top().isBlock()) {
                            //do nothing
                        }else {
                            tagStack.push(tag);
                            break;
                        }
                    }else{
                        tagStack.push(tag);
                        break;
                    }
                }
            }else{
                System.out.println("FOUNT NODIHG");


            }
        }
        if (!tagStack.isEmpty()){
           return tagStack.top();
        }

        System.out.printf("emp" + tagStack.isEmpty());
        return null;
    }

    public void documentCheck(){
        HtmlTag tag = check();
        if (tag !=null){  try {
            if(tag.isClosingTag()){
                gui.getTextArea().getDocument().insertString(tag.getEnd(),"<! --Tag closing overflow: "+tag.toString()+" >",null);
            }else{
                gui.getTextArea().getDocument().insertString(tag.getEnd(),"            <!-- Missing "+tagStack.top().toString()+" tag (Press F1 to insert closing tag at cursor)>",null);

            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        }
    }

    public void autoComplete(){
        HtmlTag tag = check();
        if (tag !=null){  try {
            if(tag.isClosingTag()){
            }else{

                gui.getTextArea().getDocument().insertString(gui.getTextArea().getCaretPosition(),"</ "+tag.toString()+" >",null);
                gui.getTextArea().setCaretPosition(tag.getEnd()+1);

            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        }
    }

    private HtmlTag containsTag(Matcher m){
        HtmlTag tag_ = null;
        String tagString = m.group().replace("<", " ").replace(">", " ").replace("/", " ").trim().split(" ")[0];
        System.out.println(tagString+ "lel");
        for (HTML.Tag tag:HTML.getAllTags()) {
            // Replace first char of the tag, trim it then split with space
            if (tagString.equals(tag.toString())){
              tag_ = new HtmlTag(tag,m.start(),m.end(),isClosingTag(m.group()));
              Debugger.print("FOUND IT");
            }
        }
        return tag_;
    }

    boolean removeTag(HtmlTag tag) {
        return tagStack.top().equals(tag);
    }


    private boolean isClosingTag(String tagText) {
        return tagText.replace(" ", "").contains("</");
    }

    private boolean isOpeningTag(String tagText) {
        return !tagText.replace(" ", "").contains("</");
    }

    boolean isSelfClosingTag(String tagText) {
        return tagText.replace(" ", "").contains("/>");
    }

}
