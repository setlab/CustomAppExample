package com.tytenko.customappexample.controls;

import com.tytenko.customappexample.data.Chat;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ChatControl extends VBox {
    private Chat chat;
    private ChatClickHandler chatClickHandler;
    public ChatControl(Chat chat) {
        super();
        this.chat = chat;
        setStyle("-fx-padding: 2 5;");
        getChildren().add(new Label(chat.getName()));
        setOnMouseClicked(event -> {
            if (chatClickHandler != null) {
                chatClickHandler.onClick(this);
            }
        });
    }

    public Chat getChat() {
        return chat;
    }
    public void setChatClickHandler(ChatClickHandler chatClickHandler) {
        this.chatClickHandler = chatClickHandler;
    }
}
