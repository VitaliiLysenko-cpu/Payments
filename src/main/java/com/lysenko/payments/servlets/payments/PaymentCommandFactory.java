package com.lysenko.payments.servlets.payments;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentCommandFactory extends CommandFactory {

    /**
     * @param request  HttpServletRequest object of the current request
     */
    public PaymentCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Defines an appropriate Command according to request URI
     *
     * @return Command entity
     */
    @Override
    public Command defineCommand() {
        String[] path = request.getRequestURI().split("/");
        String action = path[path.length - 1];

        switch (action) {
            case "new":
                return  new NewPaymentCommand();
            case "create":
                return new CreatePaymentCommand();
            default:
                return null;
        }

    }
}
