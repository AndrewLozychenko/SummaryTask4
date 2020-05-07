package ua.nure.lozychenko.facultative.db.entity;

import ua.nure.lozychenko.facultative.constants.Messages;

public class User {
    private long id;
    private String login;
    private String password;
    private Type type;
    private boolean blocked;
    private String name;
    private String surname;
    private String contacts;

    public User(long id, String login, String password, Type type, boolean blocked,
                String name, String surname, String contacts) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.type = type;
        this.blocked = blocked;
        this.name = name;
        this.surname = surname;
        this.contacts = contacts;
    }

    public static User createUser(String login, String password, Type type, String name, String surname, String contacts) {
        return new User(0, login, password, type, false, name, surname, contacts);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", type=" + type +
                ", blocked=" + blocked +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", contacts='" + contacts + '\'' +
                '}';
    }

    public String validate() {
        if (login == null || "".equals(login)) {
            return "login" + Messages.ERROR_FIELD_CAN_NOT_BE_EMPTY;
        } else if (!login.matches("[a-z-A-Z-0-9]+")) {
            return "login" + Messages.ERROR_FIELD_CONTAINS_INAPPROPRIATE_CHARACTERS;
        } else if (login.length() < 6) {
            return "login" + Messages.ERROR_TOO_SHORT;
        }
        if (password == null || "".equals(password)) {
            return "password" + Messages.ERROR_FIELD_CAN_NOT_BE_EMPTY;
        } else if (!password.matches("[a-z-A-Z-0-9]+")) {
            return "password" + Messages.ERROR_FIELD_CONTAINS_INAPPROPRIATE_CHARACTERS;
        } else if (password.length() < 6) {
            return "password" + Messages.ERROR_TOO_SHORT;
        }
        if (name == null || "".equals(name)) {
            return "name" + Messages.ERROR_FIELD_CAN_NOT_BE_EMPTY;
        }
        if (surname == null || "".equals(surname)) {
            return "surname" + Messages.ERROR_FIELD_CAN_NOT_BE_EMPTY;
        }
        return null;
    }
}
