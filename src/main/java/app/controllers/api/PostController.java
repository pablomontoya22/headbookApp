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

import app.entities.Post;
import app.helpers.Messages;
import app.helpers.Response;
import app.repositories.PostRepository;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	protected PostRepository r;

	@GetMapping(value = "/{id}")
	private Response get(@PathVariable("id") Long id) {
		try {
			return id > 0 ? new Response(wrap(r.findById(id).get()))
					: new Response((List<Post>) r.findAll());
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	@PostMapping
	private Response save(@RequestBody Post entity) {
		try {
			return new Response(wrap(r.save(entity)));
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	@PutMapping
	private Response update(@RequestBody Post entity) {
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

	private List<Post> wrap(Post u) {
		return Arrays.asList(u);
	}

}
