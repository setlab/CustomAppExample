package com.tytenko.customappexample;

import com.tytenko.customappexample.controls.ChatClickHandler;
import com.tytenko.customappexample.controls.ChatControl;
import com.tytenko.customappexample.controls.MessageControl;
import com.tytenko.customappexample.data.Chat;
import com.tytenko.customappexample.data.Message;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomApp extends Application {
    private final List<Chat> chats = new ArrayList<>();
    private VBox chatList;
    private VBox messageList;

    ChatClickHandler chatClickHandler = sender -> {
        messageList.getChildren().clear();
        // click handler for chat
        sender.getChat().getMessages().forEach((message) -> {
            // update message list
            messageList.getChildren().add(new MessageControl(message));
        });
    };


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeData();
        BorderPane root = new BorderPane();

        messageList = new VBox(10);
        messageList.setStyle("-fx-padding: 20; -fx-background-color: #dddddd; -fx-pref-width: 570;");

        chatList = new VBox(5);
        chatList.setStyle("-fx-padding: 15; -fx-pref-width: 130;");

        chats.forEach( chat -> {
            ChatControl chatControl = new ChatControl(chat);
            chatControl.setChatClickHandler(chatClickHandler);
            chatList.getChildren().add(chatControl);
        });

        HBox bottom = new HBox(5);
        bottom.setStyle("-fx-padding: 10; -fx-background-color: #cccccc;");

        TextField chatNameField = new TextField();
        chatNameField.setPromptText("Enter chat name");

        Button addChatButton = new Button("Add Chat");
        addChatButton.setOnAction(event -> {
            Chat newChat = new Chat(chatNameField.getText());
            newChat.addMessage(new Message("Hello!"));
            newChat.addMessage(new Message("How are you?"));
            chats.add(newChat);
            ChatControl newChatControl = new ChatControl(newChat);
            newChatControl.setChatClickHandler(chatClickHandler);
            chatList.getChildren().add(newChatControl);
        });

        bottom.getChildren().addAll(addChatButton, chatNameField);


        root.setLeft(chatList);
        ScrollPane scrollPane = new ScrollPane(messageList);
        scrollPane.setFitToWidth(true);  // Ensures the scroll pane width fits the content
        scrollPane.setFitToHeight(true);
        root.setCenter(scrollPane);
        root.setBottom(bottom);

        primaryStage.setTitle("Custom Messenger App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    private void initializeData() {
        // read from file here

        // Sample users and a pool of sample messages
        String[] users = {"Alice", "Bob", "Charlie", "Dana", "Eli", "Fiona", "George", "Hannah", "Ivan", "Jenny"};
        String[] sampleMessages = {
                "Hey, how's it going?", "Did you see that last email?", "Lunch tomorrow?",
                "Thanks for the help!", "Can't believe how cold it is.", "That's hilarious 😂",
                "On my way", "Let's catch up later!", "Did you finish the project?", "What are you up to this weekend?"
        };
        Random rand = new Random();

        for (String user : users) {
            Chat chat = new Chat(user);
            // Randomly add 5 to 10 messages per chat
            int messagesCount = 5 + rand.nextInt(6);
            for (int i = 0; i < messagesCount; i++) {
                chat.addMessage(new Message(sampleMessages[rand.nextInt(sampleMessages.length)]));
            }
            chats.add(chat);
        }
    }
}
