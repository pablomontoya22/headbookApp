package app.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import app.repositories.PostRepository;
import app.repositories.UserRepository;

@Controller
public class MainWebController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@RequestMapping("/")
	public String welcome(ModelMap model) {
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("posts", postRepository.findAllByOrderByDateDesc());
		return "welcome";
	}
}
