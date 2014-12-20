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
@RequestMapping("/register")
public class RegisterPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String register(final Model model) {

        return WebConstants.REGISTER_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(final Model model) {

        return WebConstants.REGISTER_PAGE;
    }
}
