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

import app.entities.Like_;
import app.entities.Post;
import app.entities.User;
import app.helpers.Messages;
import app.helpers.Response;
import app.repositories.LikeRepository;
import app.repositories.PostRepository;
import app.repositories.UserRepository;

@RestController
@RequestMapping("/api/like")
public class LikeController {
	
	@Autowired
	private LikeRepository r;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/{id}")
	private Response get(@PathVariable("id") Long id) {
		try {
			return id > 0 ? new Response(wrap(r.findById(id).get()))
					: new Response((List<Like_>) r.findAll());
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	@PostMapping
	private Response save(@RequestBody Like_ entity) {
		try {
			return new Response(wrap(r.save(entity)));
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}

	@PutMapping
	private Response update(@RequestBody Like_ entity) {
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

	private List<Like_> wrap(Like_ u) {
		return Arrays.asList(u);
	}

	@PostMapping(value = {"/add"})
	private Response addLike(@RequestBody Map<String, Long> data) {
		try {
			Post post = postRepository.findById(data.get("postId")).get();
			User user = userRepository.findById(data.get("userId")).get();
			Like_ previous = r.findByUserAndPost(user, post);
			if (previous == null) {
				post.getLikes().add(r.save(new Like_(user, post)));
				postRepository.save(post);
				return new Response(true);
			} else {
				/*r.delete(previous);
				post.setLikes(post.getLikes().stream().
					filter(l -> l.getId() != previous.getId()).
					collect(Collectors.toList()));
				postRepository.save(post);*/
				return new Response(false);
			}
		} catch (Exception e) {
			return new Response(false, Messages.ERROR);
		}
	}
}
