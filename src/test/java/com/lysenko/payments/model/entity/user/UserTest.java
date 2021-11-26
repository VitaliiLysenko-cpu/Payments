package com.lysenko.payments.model.entity.user;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;
    private int id = 1;
    private String password = "password";
    private String email = "email";
    private String name = "name";
    private String surname = "surname";
    private String phone = "phone";
    private Role role = Role.USER;
    private UserStatus status = UserStatus.BLOCKED;

    @BeforeEach
    void setup() {
        user = new User(
                id,
                password,
                email,
                name,
                surname,
                phone,
                role,
                status
        );
    }

    @Test
    void getIdTest() {
        int actual = user.getUserId();
        Assertions.assertEquals(id, actual);
    }

    @Test
    void getPasswordTest() {
        String actual = user.getPassword();
        Assertions.assertEquals(password, actual);
    }

    @Test
    void getEmailTest() {
        String actual = user.getEmail();
        Assertions.assertEquals(email, actual);
    }

    @Test
    void getNameTest() {
        String actual = user.getName();
        Assertions.assertEquals(name,actual);
    }

    @Test
    void getSurnameTest() {
        String actual = user.getSurname();
        Assertions.assertEquals(surname, actual);
    }

    @Test
    void getPhoneTest() {
        String actual = user.getPhone();
        Assertions.assertEquals(phone, actual);
    }

    @Test
    void getRoleTest() {
        Role actual = user.getRole();
        Assertions.assertEquals(role, actual);
    }

    @Test
    void getUserStatus() {
        UserStatus actual = user.getStatus();
        Assertions.assertEquals(status, actual);
    }
}