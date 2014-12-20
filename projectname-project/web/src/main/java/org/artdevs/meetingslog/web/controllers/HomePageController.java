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
@RequestMapping("/home")
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String homePage(final Model model) {

        return WebConstants.HOME_PAGE;
    }
}
