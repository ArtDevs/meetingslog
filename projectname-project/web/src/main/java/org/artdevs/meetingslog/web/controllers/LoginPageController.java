package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.web.constants.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Artem L.V. on 20.12.14.
 */
@Controller
@RequestMapping("/login")
public class LoginPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String login (final Model model) {

        return WebConstants.LOGIN_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doLogin (final Model model){

        return WebConstants.LOGIN_PAGE;
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
