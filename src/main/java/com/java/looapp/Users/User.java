package com.java.looapp.Users;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
public class User {
    @Id
    @NotEmpty(message = "Id has been existed.")
    @Column(length=45,unique = true,nullable = false)
    private String id;

    @Column(length=70, nullable= false)
    @Size(min=8,max=70)
    private String password;

    @Column(length=45, nullable= false)
    private String username;

    @NotEmpty(message = "Email has been existed.")
    @Column(nullable=false,unique = true,length=45)
    private String email;

    @Column(length=14, nullable = false)
    private String phone_num;

    @Column(length=45, nullable = false)
    private String address;

    @Column(length=64, nullable = false)
    private String emoji;

    @Column(length=5, nullable = false)
    private boolean isActive;

    @Transient
    public String getEmojiPath() {
        if (emoji == null || id == null) return null;

        return "/user-emoji/" + id + "/" + emoji;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

