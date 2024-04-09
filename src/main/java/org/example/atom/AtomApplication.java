package org.example.atom;

import org.example.atom.controller.AdminController;
import org.example.atom.controller.MessageController;
import org.example.atom.controller.TopicController;
import org.example.atom.controller.UserController;
import org.example.atom.exceptions.AuthException;
import org.example.atom.exceptions.UserAlreadyRegisteredException;
import org.example.atom.exceptions.UserNotFoundException;
import org.example.atom.model.Message;
import org.example.atom.model.Role;
import org.example.atom.model.Topic;
import org.example.atom.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AtomApplication {

    private static final String ADMIN_KEY_WORD = "ATOM";

    private static ConfigurableApplicationContext context;
    private static AdminController adminController;
    private static UserController userController;
    private static TopicController topicController;
    private static MessageController messageController;

    private static User currentUser;

    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        context = SpringApplication.run(AtomApplication.class, args);
        init();
        reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            while (currentUser == null) {
                System.out.println("Please, enter your username and password in two lines: ");
                String username = reader.readLine();
                String password = reader.readLine();
                try {
                    currentUser = login(username, password);
                    System.out.println("Access granted");
                } catch (AuthException e) {
                    System.out.print("Looks like there is no user with your username or password is incorrect, if you want to register, please enter \"register\"\nOr try again (press enter)");
                    String reg = reader.readLine();
                    if (reg.equals("register")) {
                        try {
                            currentUser = register(username, password);
                            System.out.println("You was successfully registered with this username: " + username + " and this password: " + password);
                        } catch (UserAlreadyRegisteredException ex) {
                            System.out.println("Looks like user with this username already exist.");
                        }
                    } else if (reg.equals(ADMIN_KEY_WORD)) {
                        try {
                            currentUser = regAdmin(username, password);
                            System.out.println("You was successfully registered admin profile with this username: " + username + " and this password: " + password);
                        } catch (UserAlreadyRegisteredException ex) {
                            System.out.println("Looks like user with this username already exist.");
                        }
                    }
                }
            }

            while (currentUser != null) {
                System.out.println();
                System.out.print("List of available commands: createTopic, readAllTopics, addMessage, readMessages, updateMessage, deleteMessage");
                if (currentUser.getRole().equals(Role.ADMIN)) System.out.print(", deleteTopic");
                System.out.println();
                System.out.println();
                System.out.print("Please, enter command: ");
                String command = reader.readLine();
                System.out.println();
                switch (command) {
                    case "createTopic" -> {
                        System.out.print("Please, enter topic title: ");
                        String title = getMultiStringItem();
                        System.out.print("Please, enter first message: ");
                        String messageText = getMultiStringItem();
                        System.out.println(createTopic(
                                title,
                                createMessage(currentUser, messageText))
                                ? "Topic was successfully created!"
                                : "Something went wrong :(, topic wasn't created");
                    }

                    case "readAllTopics" -> System.out.println(readAllTopics());
                    case "addMessage" -> {
                        System.out.print("Please, enter topic title, where you want to add message: ");
                        String title = getMultiStringItem();
                        System.out.print("Please, enter your message: ");
                        String messageText = getMultiStringItem();
                        System.out.println(addMessage(
                                topicController.getTopicByTitle(title),
                                createMessage(currentUser, messageText))
                                ? "Message was successfully added!"
                                : "Message is invalid :(");
                    }
                    case "readMessages" -> {
                        System.out.print("Please, enter topic title, where you want to read messages: ");
                        String title = getMultiStringItem();
                        System.out.println(readMessages(topicController.getTopicByTitle(title)));
                    }
                    case "updateMessage" -> {
                        System.out.print("Please, enter topic title, where you want to update message: ");
                        String title = getMultiStringItem();
                        int topicId = topicController.getTopicByTitle(title).getTopicId();
                        System.out.print("Please, enter id of message, which you want to update: ");
                        int messageId = Integer.parseInt(reader.readLine());
                        System.out.print("Please, enter your message: ");
                        String messageText = getMultiStringItem();
                        System.out.println(updateMessage(topicId, messageId,
                                createMessage(currentUser, messageText))
                                ? "Message was successfully updated!"
                                : "Error, check cause below");
                    }

                    case "deleteMessage" -> {
                        System.out.print("Please, enter id of message, which you want to delete: ");
                        int messageId = Integer.parseInt(reader.readLine());
                        System.out.println(deleteMessage(messageId)
                                ? "Message was successfully deleted!"
                                : "Something went wrong :(, check cause below");
                    }
                    case "deleteTopic" -> {
                        System.out.print("Please, enter id of topic, which you want to delete: ");
                        int topicId = Integer.parseInt(reader.readLine());
                        System.out.println(deleteTopic(topicId)
                                ? "Topic was successfully deleted!"
                                : "Something went wrong :(, check cause below");
                    }
                    default -> System.out.println("Invalid command, try again");
                }
            }
        }
    }

    private static String getMultiStringItem() throws IOException {
        StringBuilder value = new StringBuilder();
        String tempValue = reader.readLine();
        while (!tempValue.isEmpty()) {
            value.append(tempValue);
            tempValue = reader.readLine();
        }
        return value.toString();
    }

    private static User login(String username, String password) throws AuthException {
        User user = adminController.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new UserNotFoundException("No user exist by username = " + username + " or password is incorrect");
    }

    private static User register(String username, String password) throws UserAlreadyRegisteredException {
        if (adminController.findUserByUsername(username) != null) {
            throw new UserAlreadyRegisteredException(UserAlreadyRegistered(username));
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        userController.registration(user);
        return user;
    }

    private static User regAdmin(String username, String password) throws UserAlreadyRegisteredException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.ADMIN);
        userController.registration(user);
        return user;
    }

    private static String UserAlreadyRegistered(String username) {
        return "User with this name: " + username + " already exist";
    }

    private static boolean createTopic(String title, Message message) {
        if (topicController.getTopicByTitle(title) != null) {
            System.err.println(TopicExist(title));
            return false;
        }
        if (title.isEmpty()) {
            System.err.println("Title cant be empty!");
            return false;
        }
        if (!isMessageValid(message)) {
            System.err.println("Message is invalid!");
            return false;
        }
        Topic topic = new Topic();
        topic.setTitle(title);
        topicController.create(topic);
        topic = topicController.getTopicByTitle(title);
        messageController.addMessage(topic.getTopicId(), message);
        return true;
    }

    private static String TopicExist(String title) {
        return "Topic with this title: " + title + " already exist";
    }

    private static boolean addMessage(Topic topic, Message message) {
        if (topic == null) {
            System.err.println("Topic invalid");
            return false;
        }
        if (isMessageValid(message)) {
            messageController.addMessage(topic.getTopicId(), message);
            return true;
        }
        System.err.println("message invalid");
        return false;
    }

    private static boolean updateMessage(int topicId, int messageId, Message newMessage) {
        if (currentUser.getUsername().equals(
                messageController.readMessage(messageId).getAuthorName())
                || currentUser.getRole().equals(Role.ADMIN)) {
            if (isMessageValid(newMessage)) {
                messageController.updateMessage(topicId, messageId, newMessage);
                return true;
            }
            System.err.println("message invalid");
        } else {
            System.err.println("auth failed");
        }
        return false;
    }

    private static boolean deleteMessage(int messageId) {
        if (currentUser.getUsername().equals(
                messageController.readMessage(messageId).getAuthorName())
                || currentUser.getRole().equals(Role.ADMIN)) {
            messageController.deleteMessage(messageId);
            return true;
        }
        System.err.println("Seems you are not an owner of this message!");
        return false;
    }

    private static boolean deleteTopic(int topicId) {
        if (currentUser.getRole().equals(Role.ADMIN)) {
            topicController.deleteTopic(topicId);
            return true;
        }
        System.err.println("Seems you are not an admin!");
        return false;
    }

    private static List<Message> readMessages(Topic topic) {
        if (topic == null) {
            return new ArrayList<>();
        }
        return messageController.readMessages(topic.getTopicId());
    }

    public static List<Topic> readAllTopics() {
        return topicController.readAll();
    }

    private static Message createMessage(User author, String text) {
        Message message = new Message();
        message.setText(text);
        message.setAuthor(author.getUsername());
        message.setDate(new Date(System.currentTimeMillis()));
        return message;
    }

    private static boolean isMessageValid(Message message) {
        return message != null
                && !message.getText().isEmpty()
                && !message.getAuthorName().isEmpty()
                && message.getDate() != null;
    }

    private static void init() {
        adminController = context.getBean(AdminController.class);
        userController = context.getBean(UserController.class);
        topicController = context.getBean(TopicController.class);
        messageController = context.getBean(MessageController.class);
    }
}
