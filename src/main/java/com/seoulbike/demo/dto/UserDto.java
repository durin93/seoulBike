package com.seoulbike.demo.dto;

import com.seoulbike.demo.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserDto {

    @Size(min = 3, max = 20)
    private String userId;

    @Size(min = 3, max = 20)
    private String password;

    @Size(min = 3, max = 20)
    private String name;

    @Email
    private String email;

    public UserDto() {
    }

    public UserDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toUser() {
        return new User(userId, password, name, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userId, userDto.userId) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, password, name, email);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
