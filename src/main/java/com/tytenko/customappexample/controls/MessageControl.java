package com.tytenko.customappexample.controls;

import com.tytenko.customappexample.data.Message;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageControl extends VBox {
    private Message message;
    public MessageControl(Message message) {
        super();
        this.message = message;
        setStyle("-fx-padding: 20;" +
                "-fx-background-color: #BCB9DF; " +
                "-fx-background-radius: 20; " +
                "-fx-border-radius: 20; ");
        getChildren().add(new Label(message.getContent()));

    }
}
