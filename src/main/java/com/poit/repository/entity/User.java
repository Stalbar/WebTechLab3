package com.poit.repository.entity;

import java.util.Objects;

public class User {
    String name;
    String password;
    Roles roles;

    public User(String name, String password, Roles roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    public Roles getRole() {
        return roles;
    }

    public void setRole(Roles roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }

        if (!Objects.equals(name, user.name)) {
            return false;
        }
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
