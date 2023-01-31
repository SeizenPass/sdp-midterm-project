package com.team.handler;

public class UserAuthenticatedHandler extends BaseHandler {
    public UserAuthenticatedHandler(Handler handler) {
        super(handler);
    }

    @Override
    public void handle(Request request) {
        if (request.getCommand().getAtm().getCurrentAccount() == null) {
            System.out.println("You have to log in to account.");
        }
        else super.handle(request);
    }
}
