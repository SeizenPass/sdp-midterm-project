package com.team.handler;

public class BankChosenHandler extends BaseHandler {
    public BankChosenHandler(Handler handler) {
        super(handler);
    }

    @Override
    public void handle(Request request) {
        if (request.getCommand().getAtm().getCurrentService() == null) {
            System.out.println("Bank wasn't chosen.");
        }
        else super.handle(request);
    }
}
