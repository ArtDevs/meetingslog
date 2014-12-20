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
@RequestMapping("/account")
public class AccountPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String account(final Model model) {

        return WebConstants.ACCOUNT_PAGE;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editAccount(final Model model) {

        return WebConstants.ACCOUNT_PAGE;
    }
}
