package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home controller
 */
@Controller
public class HomeController
{
	/**
	 * Redirects to home page
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	/**
	 * Redirects to bidList list
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}


}
