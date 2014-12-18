package org.artdevs.meetingslog.web.controllers;

import com.sun.javafx.sg.PGShape;
import org.artdevs.meetingslog.core.CoreComponent;
import org.artdevs.meetingslog.facades.FacadeComponent;
import org.artdevs.meetingslog.services.ServiceComponent;
import org.artdevs.meetingslog.services.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by Artem L.V. on 09.12.14.
 */
@Controller
@RequestMapping(value = "/")
public class HelloWorldController {

	@Resource(name = "coreComponent")
	CoreComponent coreComponent;

	@Autowired
	FacadeComponent facadeComponent;

	@Autowired
	ServiceComponent serviceComponent;

	@RequestMapping(method = RequestMethod.GET)
	public String helloWorld (final Model model) {

		String message = coreComponent.getMessage() + "<br>" + facadeComponent.getMessage() + "<br>" + serviceComponent.getMessage();

		model.addAttribute("customMessage", message);
		model.addAttribute("loginHeader","Log in");
		model.addAttribute("loginLink","/?login");
		model.addAttribute("signUpHeader","Sign up");
		model.addAttribute("signUpLink","?signup");
		return "hello";
	}

	@RequestMapping(method = RequestMethod.GET, params = "login")
	public String askForLogin(Model model){
		model.addAttribute("login","login");
		model.addAttribute("password","password");
		return "login";
	}
}
