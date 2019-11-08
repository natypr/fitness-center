package by.naty.fitnesscenter.model.tag;

import by.naty.fitnesscenter.model.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class HelloUserTag extends TagSupport {
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        String locale = (String) pageContext.getSession().getAttribute("changeLanguage");
        Locale current;
        if("en_US".equals(locale)){
           current = new Locale("en", "US");
        } else {
           current = new Locale("ru", "RU");
        }

        ResourceBundle bundle = ResourceBundle.getBundle("locale", current);
        StringBuffer buffer = new StringBuffer();
        if(user != null) {
            try {
                buffer.append("<b>").append(user.getName()).append(" ")
                        .append(user.getSurname()).append("</b>").append("<br/>");
                System.out.println(user.getRole());
                if (user.getRole().equals("admin")) {
                    buffer.append("<i>").append(bundle.getString("label.userRoleAdmin")).append("</i>");
                } else if (user.getRole().equals("trainer")){
                    buffer.append("<i>").append(bundle.getString("label.userRoleTrainer")).append("</i>");
                } else{
                    buffer.append("<i>").append(bundle.getString("label.userRoleClient")).append("</i>");
                }
                pageContext.getOut().write(buffer.toString());
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
        }
    return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
