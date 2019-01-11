package app.controllers.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.User;
import app.helpers.Messages;
import app.helpers.Response;
import app.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository r;

	@GetMapping(value = "/{id}")
	private Response get(@PathVariable("id") Long id) {
		try {
			return id > 0 ? new Response(wrap(r.findById(id).get()))
					: new Response((List<User>) r.findAll());
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	@PostMapping
	private Response save(@RequestBody User entity) {
		try {
			return new Response(wrap(r.save(entity)));
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	@PutMapping
	private Response update(@RequestBody User entity) {
		try {
			r.save(entity);
			return new Response(true, Messages.UPDATED);
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	@DeleteMapping
	private Response delete(@RequestBody Map<String, Long> data) {
		try {
			r.deleteById(data.get("id"));
			return new Response(true, Messages.DELETED);
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	private List<User> wrap(User u) {
		return Arrays.asList(u);
	}

}
