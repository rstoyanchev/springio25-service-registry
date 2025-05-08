package demo;

import demo.github.User;
import demo.github.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	final UserService users;

	public UserController(UserService users) {
		this.users = users;
	}

	@GetMapping("/")
	String index() {
		return "redirect:/user";
	}

	@ResponseBody
	@GetMapping("/user")
	User user() {
		return this.users.getAuthenticatedUser();
	}

}
