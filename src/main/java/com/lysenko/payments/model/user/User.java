package com.lysenko.payments.model.user;

import java.io.Serializable;

public class User implements Serializable {

    private final int userId;
    private final String password; //TODO do we need it here?
    private final String email;
    private final String name;
    private final String surname;
    private final String phone;
    private final Role role;
    private final UserStatus status;

    public User(int userId, String password, String email, String name, String surname, String phone, Role role,UserStatus status) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }
}
