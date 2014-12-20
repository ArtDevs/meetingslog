package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.web.constants.WebConstants;
import org.artdevs.meetingslog.web.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Artem L.V. on 20.12.14.
 */
@Controller
@RequestMapping("/home")
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String homePage(final Model model, final HttpServletRequest request,
                           @RequestParam("login") String login,
                           @RequestParam("pass") String pass) {
        String message = request.getParameter("msg1");
        model.addAttribute("login", login);
        model.addAttribute("pass", pass);
        model.addAttribute("msg1", message);

        return WebConstants.HOME_PAGE;
    }
}
