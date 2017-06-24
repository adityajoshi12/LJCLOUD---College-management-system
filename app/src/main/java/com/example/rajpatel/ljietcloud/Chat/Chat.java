package com.example.rajpatel.ljietcloud.Chat;

/**
 * @author greg
 * @since 6/21/13
 */
public  class Chat {


    private String message;
    private String author;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public Chat() {
    }

    Chat(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
