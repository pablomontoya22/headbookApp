package app.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.controllers.api.PostController;
import app.entities.Post;
import app.repositories.UserRepository;

@Controller
@RequestMapping("/posts")
public class PostWebController extends PostController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public ModelAndView add(ModelMap model) {
		model.addAttribute("users", userRepository.findAll());
		return new ModelAndView("addPost");
	}

	@PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ModelAndView save(@RequestBody MultiValueMap<String, String> form) {
		r.save(new Post(userRepository.findById(Long.parseLong(form.getFirst("user"))).get(),
				form.getFirst("title"), form.getFirst("content")));
		return new ModelAndView("redirect:/");
	}
}
