package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("auth")
public class LoginController {
	protected static Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {

		logger.debug("Received request to show login page");

		if (error == true) {
			// Assign an error message
			model.put("error",
					"You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		return "loginpage";
	}

	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {

		logger.debug("Received request to show denied page");

		return "deniedpage";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "loginpage";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
}
