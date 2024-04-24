package com.tytenko.customappexample.data;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String name;
    private List<Message> messages;

    public Chat(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        return name;
    }
}
