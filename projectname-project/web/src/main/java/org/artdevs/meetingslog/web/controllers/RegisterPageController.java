package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.facades.data.UserData;
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
        model.addAttribute("newUser",new UserData());
        model.addAttribute("message","");
        return WebConstants.REGISTER_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("newUser") @Valid UserData newUser, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){

            model.addAttribute("message","Wrong input data, check form for errors.");
            return WebConstants.REGISTER_PAGE;
        }

        if(mapUser.verify(newUser)){
            User newUserModel=mapUser.mapToModel(newUser);
            userServices.insertUser(newUserModel);
            model.addAttribute("customMessage","Your login was successfully registered. Now you can join our community as guest user.");
            model.addAttribute("loginHeader", "Here you can log in.");
            model.addAttribute("loginLink","login");
            model.addAttribute("signUpHeader","Here you can sign up as new guest user.");
            model.addAttribute("signUpLink","register");
            return WebConstants.HOME_PAGE;
        }
        else {
            model.addAttribute("message","Login already used.");
            return WebConstants.REGISTER_PAGE;
        }
    }
}
