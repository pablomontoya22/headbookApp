package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.Like;
import app.entities.Post;
import app.entities.User;

public interface LikeRepository extends CrudRepository<Like, Long> {
	public Like findByUserAndPost(User user, Post post);
}
