package com.juliodev.taxihaiti.model;

/**
 * Created by Julio on 8/11/2016.
 */
public class ChatMessage {
    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    String message;
    String name;


    public ChatMessage() {
    }

    public ChatMessage(String message, String name) {
        this.message = message;
        this.name = name;
    }
}
