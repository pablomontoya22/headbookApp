package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.Like_;
import app.entities.Post;
import app.entities.User;

public interface LikeRepository extends CrudRepository<Like_, Long> {
	public Like_ findByUserAndPost(User user, Post post);
}
