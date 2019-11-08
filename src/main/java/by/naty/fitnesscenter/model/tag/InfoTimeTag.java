package by.naty.fitnesscenter.model.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("serial")
public class InfoTimeTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        DateFormat dateFormat;
        if("en_US".equals(pageContext.getSession().getAttribute("changeLanguage")))
            dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM , Locale.US);
        else{
            dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("ru","RU"));
        }
        String time = "<b> " + dateFormat.format(new Date()) + " </b>";

        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
