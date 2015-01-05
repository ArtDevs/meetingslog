package org.artdevs.meetingslog.facades.data;


import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Slava on 25.12.2014.
 */
@Component
public class UserData {
    @Size(min=5, max = 25, message = "Login must be from 5 to 25 characters long.")
    @Pattern(regexp = "^[a-z,A-Z,0-9,_]{5,}$", message = "Login can include only letters, numbers and _")
    private String login;

    @Size(min=5, max = 25, message = "Password must be from 5 to 25 characters long.")
    private String password;

    @Size(min=2, max = 50, message = "First name must be from 5 to 50 characters long.")
    @Pattern(regexp = "^[^0-9]{2,}$", message = "First name can't include such set of characters.")
    private String firstName;

    @Size(min=2, max = 50, message = "Second name must be from 5 to 50 characters long.")
    @Pattern(regexp = "^[^0-9]{2,}$", message = "Second name can't include such set of characters.")
    private String secondName;

    @Size(min=5, max = 255, message = "E-mail address must be from 5 to 25 characters long.")
    @Pattern(regexp = "^[a-z,A-Z,0-9,_.+%-]+@[a-z,A-Z,0-9,_.-]+\\.[a-z,A-Z]{2,4}$",
            message = "E-mail can't include such set of characters.")
    private String email;

    @Size(max = 500, message = "Comment must up to 500 characters long.")
    private String comment;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
