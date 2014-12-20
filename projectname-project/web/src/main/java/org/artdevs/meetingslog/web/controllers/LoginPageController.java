package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.web.constants.WebConstants;
import org.artdevs.meetingslog.web.forms.LoginForm;
import org.artdevs.meetingslog.web.forms.NewUserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Artem L.V. on 20.12.14.
 */
@Controller
@RequestMapping("/login")
public class LoginPageController {

    private static String REDIRECT_TO_HOME = "redirect:/home";

    @RequestMapping(method = RequestMethod.GET)
    public String login (final Model model) {

        model.addAttribute("loginForm", new LoginForm());

        return WebConstants.LOGIN_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doLogin (final Model model,
                           @ModelAttribute final LoginForm loginForm,
                           final RedirectAttributes attributes) {

        attributes.addAttribute("login", loginForm.getLogin());
        attributes.addAttribute("pass", loginForm.getPass());

        return REDIRECT_TO_HOME;
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String forgotPassword(final Model model) {

        return WebConstants.FORGOT_PASSWORD_PAGE;
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String processForgot(final Model model) {

        return WebConstants.FORGOT_PASSWORD_PAGE;
    }
}