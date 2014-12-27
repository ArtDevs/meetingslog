package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.facades.facademodel.UserFacade;
import org.artdevs.meetingslog.facades.impl.MapUser;
import org.artdevs.meetingslog.services.UserServices;
import org.artdevs.meetingslog.web.constants.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

/**
 * Created by Artem L.V. on 20.12.14.
 */
@Controller
@RequestMapping("/register")
@SessionAttributes("newUser")
public class RegisterPageController {

    @Autowired
    private MapUser mapUser;

    @Autowired
    private UserServices userServices;

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("newUser",new UserFacade());
        return WebConstants.REGISTER_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("newUser") UserFacade newUser, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            //model.addAttribute("newUser",newUser);
            return WebConstants.REGISTER_PAGE;
        }

        if(mapUser.verify(newUser)){
            User newUserModel=mapUser.mapToModel(newUser);
            userServices.insertUser(newUserModel);
            return WebConstants.ACCOUNT_PAGE;
        }
        else {
            //model.addAttribute("newUser",newUser);
            return WebConstants.REGISTER_PAGE;
        }

    }
}
