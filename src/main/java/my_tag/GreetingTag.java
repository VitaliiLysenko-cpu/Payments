package my_tag;

import com.lysenko.payments.model.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class GreetingTag extends TagSupport {
private final Logger log = Logger.getLogger(GreetingTag.class);
    @Override
    public int doStartTag(){
        JspWriter greeting = pageContext.getOut();
        try {
            User user = (User) pageContext.getSession().getAttribute("user");
            String userName = user.getName();
            greeting.print(userName);
        } catch (IOException e) {
           log.error("userName was not read", e);
        }
        return SKIP_BODY;
    }
}
