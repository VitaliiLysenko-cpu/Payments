package com.lysenko.payments.servlets.payments;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentCommandFactory extends CommandFactory {
    private final Logger log = Logger.getLogger(PaymentCommandFactory.class);

    public PaymentCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        String[] path = request.getRequestURI().split("/");
        log.debug("try to get action from path");
        String action = path[path.length - 1];
        log.debug("coll method with action: " + action);
        switch (action) {
            case "new":
                return new NewPaymentCommand();
            case "create":
                return new CreatePaymentCommand();
            default:
                return null;
        }
    }
}
