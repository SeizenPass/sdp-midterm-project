package com.team.handler;

public interface Handler {
    void setNext(Handler handler);
    void handle(Request request);
}
