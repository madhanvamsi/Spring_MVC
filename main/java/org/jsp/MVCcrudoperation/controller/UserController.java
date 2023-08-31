package org.jsp.MVCcrudoperation.controller;

import org.jsp.MVCcrudoperation.dao.UserDao;
import org.jsp.MVCcrudoperation.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	private UserDao udao;

	@RequestMapping("/open")
	public String openView(String view) {
		return view;
	}

	@PostMapping("/register")
	public ModelAndView saveUser(@ModelAttribute User u) {
		u = udao.saveUser(u);
		ModelAndView view = new ModelAndView();
		view.addObject("msg", "user saved with id :" + u.getId());
		view.setViewName("print");
		return view;
	}

	@RequestMapping("/edit")
	public ModelAndView editUser(@RequestParam int id) {
		User u = udao.FindUserById(id);
		if (u != null) {
			ModelAndView view = new ModelAndView();
			view.addObject("u", u);
			view.setViewName("update");
			return view;

		} else {
			ModelAndView view = new ModelAndView();
			view.addObject("msg", "invalid user");
			view.setViewName("print");
			return view;

		}
	}

	@PostMapping("/update")
	public ModelAndView updateUser(@ModelAttribute User u) {
		u = udao.updateUser(u);
		ModelAndView view = new ModelAndView();
		view.addObject("msg", "user updatede with id :" + u.getId());
		view.setViewName("print");
		return view;
	}

	@RequestMapping(value = "/delete")
	public ModelAndView DeleteUser(@RequestParam int id, ModelAndView view) {
		boolean delete = udao.deleteUser(id);
		if (delete) {
			view.addObject("msg", "user deleted");
			view.setViewName("print");
			return view;
		} else {

			view.addObject("msg", "invalid user id");
			view.setViewName("print");
			return view;
		}
	}

	@RequestMapping("/verifybyemail")
	public ModelAndView VerifyByEmail(@RequestParam String email, @RequestParam String password) {
		User u = udao.verifyByEmail(email, password);
		if (u != null) {
			ModelAndView view = new ModelAndView();
			view.addObject("u", u);
			view.setViewName("view");
			return view;
		} else {
			ModelAndView view = new ModelAndView();
			view.addObject("msg", "invalid email id");
			view.setViewName("print");
			return view;

		}
	}

	@RequestMapping("/verifybyid")
	public ModelAndView VerifyById(@RequestParam int id, @RequestParam String password) {
		User u = udao.verifyById(id, password);
		if (u != null) {
			ModelAndView view = new ModelAndView();
			view.addObject("u", u);
			view.setViewName("view");
			return view;
		} else {
			ModelAndView view = new ModelAndView();
			view.addObject("msg", "invalid user id");
			view.setViewName("print");
			return view;

		}
	}

	@RequestMapping("/verifybyphone")
	public ModelAndView VerifyByPhone(@RequestParam long phone, @RequestParam String password) {
		User u = udao.verifyByPhone(phone, password);
		if (u != null) {
			ModelAndView view = new ModelAndView();
			view.addObject("u", u);
			view.setViewName("view");
			return view;
		} else {
			ModelAndView view = new ModelAndView();
			view.addObject("msg", "invalid phone number");
			view.setViewName("print");
			return view;

		}
	}

}
