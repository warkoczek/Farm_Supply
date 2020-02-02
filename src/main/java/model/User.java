package model;

import java.util.Objects;

public class User {

    private Long id;
    private String login;
    private String password;
    private UserAddress uAddress;


    public User() {
    }

    public User(Long id, String login, String password, UserAddress uAddress) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.uAddress = uAddress;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserAddress getUAddress(){return uAddress;}

    public void setUAddress(UserAddress userAddress){this.uAddress=userAddress;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
