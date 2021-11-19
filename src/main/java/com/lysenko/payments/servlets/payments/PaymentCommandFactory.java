package com.lysenko.payments.servlets.payments;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentCommandFactory extends CommandFactory {

    public PaymentCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }


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
