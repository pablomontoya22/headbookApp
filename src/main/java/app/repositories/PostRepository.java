package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	public Iterable<Post> findAllByOrderByDateDesc();
}
