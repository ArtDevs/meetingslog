package org.artdevs.meetingslog.web.controllers;

import org.artdevs.meetingslog.facades.UserFacade;
import org.artdevs.meetingslog.facades.data.UserData;
import org.artdevs.meetingslog.web.constants.WebConstants;
import org.artdevs.meetingslog.web.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Artem L.V. on 20.12.14.
 */
@Controller
@RequestMapping("/login")
public class LoginPageController {

	@Autowired
	UserFacade userFacade;

	private static String REDIRECT_TO_HOME = "redirect:/home";

	@RequestMapping(method = RequestMethod.GET)
	public String login(final Model model) {

		model.addAttribute("loginForm", new LoginForm());

		return WebConstants.LOGIN_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(final Model model,
	                      @ModelAttribute final LoginForm loginForm,
	                      final RedirectAttributes attributes) {

		attributes.addAttribute("login", loginForm.getLogin());
		attributes.addAttribute("pass", loginForm.getPass());
		try {
			UserData userData = userFacade.findUserByLogin(loginForm.getLogin());

			// TODO: reimplement the logic for password check
			if (loginForm.getPass().equals(userData.getPassword())) {
				attributes.addAttribute("msg1", "Successes");
				return REDIRECT_TO_HOME;
			} else {
				String messageForPass = "Invalid Password. Try Again";
				model.addAttribute("msgPass", messageForPass);
				return WebConstants.LOGIN_PAGE;
			}
		} catch (Exception e) {
			String messageForLogin = "There is no registered users with this login in DataBase";
			model.addAttribute("msgLogin", messageForLogin);
			return WebConstants.LOGIN_PAGE;
		}
	}

	@RequestMapping(value = "/fail2login", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return WebConstants.LOGIN_PAGE;

	}
}
