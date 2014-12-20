package org.artdevs.meetingslog.web.forms;

//import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

/**
 * Created by Artem L.V. on 20.12.14.
 */
public class LoginForm {
//    @NotEmpty
    private String login;
//    @NotEmpty
    private String pass;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
