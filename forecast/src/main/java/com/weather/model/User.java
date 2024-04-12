package com.weather.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    // Getter method for the username field
    public String getUsername() {
        return username;
    }
    // Setter method for the username field
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for the password field
    public String getPassword() {
        return password;
    }
    // Setter method for the password field
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter method for the email field
    public String getEmail() {
        return email;
    }
    // Setter method for the email field
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter method for the id field
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for the id field
    public int getId() {
        return id;
    }
}
