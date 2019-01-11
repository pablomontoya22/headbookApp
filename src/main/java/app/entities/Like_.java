package app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Like_ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@NotNull(message = "User can't be empty")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;
	@JsonIgnore
	@NotNull(message = "Post can't be empty")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Post post;

	public Like_(@NotNull(message = "User can't be empty") User user,
			@NotNull(message = "Post can't be empty") Post post) {
		super();
		this.user = user;
		this.post = post;
	}

	public Like_() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
}
