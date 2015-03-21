package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.core.model.UserModel;
import org.artdevs.meetingslog.facades.UserFacade;
import org.artdevs.meetingslog.facades.data.UserData;
import org.artdevs.meetingslog.web.forms.NewUserForm;
import org.artdevs.meetingslog.services.UserService;
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
@SessionAttributes("newUserForm")
public class RegisterPageController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("newUserForm", new NewUserForm());
        model.addAttribute("message","");
        return WebConstants.REGISTER_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("newUserForm") @Valid NewUserForm newUserForm, BindingResult bindingResult, Model model) {



        if(bindingResult.hasErrors()){
            model.addAttribute("message","Wrong input data, check form for errors.");
            return WebConstants.REGISTER_PAGE;
        }

        UserData userData = populateDataObjectFromForm(newUserForm);
        if(userFacade.verify(userData)){
            userFacade.insertUser(userData);
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

    private UserData populateDataObjectFromForm(NewUserForm form){
        UserData data = new UserData();

        data.setLogin(form.getLogin());
        data.setLogin(form.getPassword());
        data.setLogin(form.getFirstName());
        data.setLogin(form.getSecondName());
        data.setLogin(form.getEmail());
        data.setLogin(form.getAddress());
        data.setLogin(form.getPhoneNumber1());
        data.setLogin(form.getPhoneNumber2());
        data.setLogin(form.getComment());

        return data;
    }
}
