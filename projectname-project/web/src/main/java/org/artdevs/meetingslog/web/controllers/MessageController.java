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
@RequestMapping("/msg")
public class MessageController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMessage(final Model model) {

        return WebConstants.MESSAGE_PAGE;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeMessage(final Model model) {

        return WebConstants.MESSAGE_PAGE;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editMessage(final Model model) {

        return WebConstants.MESSAGE_PAGE;
    }
}
