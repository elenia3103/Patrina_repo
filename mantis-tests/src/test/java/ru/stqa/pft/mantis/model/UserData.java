package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "username")
    private String username;

    @Expose
    @Column(name = "email")
    private String email;

    @Expose
    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users")

    public int getId() {
        return id;
    }

    public void withId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void withUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void withEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void withPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(username, userData.username) &&
                Objects.equals(email, userData.email) &&
                Objects.equals(password, userData.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
