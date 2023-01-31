package com.team.handler;

import lombok.Data;

@Data
public abstract class BaseHandler implements Handler {
    private Handler next;

    @Override
    public void handle(Request request) {
        if (next != null) next.handle(request);
        else request.getCommand().execute();
    }

    public BaseHandler(Handler next) {
        this.next = next;
    }
}
