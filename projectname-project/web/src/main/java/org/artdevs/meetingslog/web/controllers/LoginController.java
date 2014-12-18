package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.services.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Slava on 18.12.2014.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController{

    @Autowired
    UserRegistration userRegistration;

    @RequestMapping(method = RequestMethod.GET)
    public String checkCredentials(@RequestParam("login") String login,
                                   @RequestParam("password") String password,
                                   @RequestParam("command") String command,
                                   Model model){
        switch (command){
            case "signin": {
                if(userRegistration.checkPassword(login,password)){
                    model.addAttribute("user",userRegistration.findUserByName(login));
                    return "welcome";
                }
                else{
                    model.addAttribute("message","Login incorrect.");
                    model.addAttribute(("suggestion"),"Restore password?");
                    return "login";
                }
            }
            case "restorepass":{
                return "registernew";
            }
            default: return "hello";
        }
    }
}