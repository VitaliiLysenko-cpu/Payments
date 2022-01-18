package com.lysenko.payments.servlet.authorization;

import com.google.common.annotations.VisibleForTesting;
import com.lysenko.payments.PasswordStorage;
import com.lysenko.payments.model.dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(urlPatterns = "/create_user")
public class CreateNewUserServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(CreateNewUserServlet.class);
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);
    private UserDao userDao = new UserDao();

    @VisibleForTesting
    void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("try to get parameters");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phoneNum = request.getParameter("phone");
        String password = request.getParameter("password");
        String passwordHash = password;
        try {
           passwordHash = PasswordStorage.createHash(password);
        } catch (PasswordStorage.CannotPerformOperationException e) {
                 log.error("Unable to perform password hashing operation", e);
        }


        log.debug("email: " + email + "firstname: " + firstname + "lastname: " + lastname + "phone: " + phoneNum
                + "password: " + passwordHash);
        if(validateEmail(email)) {

            if(validatePhoneNumber(phoneNum)) {
                log.debug("coll \"registration\"");
                boolean res = userDao.registration(email, firstname, lastname, phoneNum, passwordHash);
                if (res) {
                    response.sendRedirect("/registration?info=infoRegistration");
                } else {
                    log.debug("redirect to \"login\"");
                    response.sendRedirect("/registration?error=errorRegistration");
                }
            }else {
                response.sendRedirect("/registration?error=errorPhoneNumber");
            }
        }else{
            response.sendRedirect("/registration?error=errorEmail");
        }
    }
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    public static boolean validatePhoneNumber(String phone) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        return matcher.find();
    }
}
