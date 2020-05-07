package ua.nure.lozychenko.facultative.db.entity;

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
            return "login" + ",empty";
        } else if (!login.matches("[a-z-A-Z-0-9]+")) {
            return "login" + ",chars";
        } else if (login.length() < 6) {
            return "login" + ",short";
        }
        if (password == null || "".equals(password)) {
            return "password" + ",empty";
        } else if (!password.matches("[a-z-A-Z-0-9]+")) {
            return "password" + ",chars";
        } else if (password.length() < 6) {
            return "password" + ",short";
        }
        if (name == null || "".equals(name)) {
            return "name" + ",empty";
        }
        if (surname == null || "".equals(surname)) {
            return "surname" + ",empty";
        }
        return null;
    }
}
