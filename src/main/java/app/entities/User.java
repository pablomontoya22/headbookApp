package app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Name can't be empty")
	private String name;
	@NotNull(message = "Last name can't be empty")
	private String surname;
	@URL(message = "Invalid URL")
	private String avatar;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Post> posts;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Like> likes;

	public User() {}

	public User(@NotNull(message = "Name can't be empty") String name,
			@NotNull(message = "Last name can't be empty") String surname,
			@URL(message = "Invalid URL") String avatar) {
		super();
		this.name = name;
		this.surname = surname;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public List<Like> getLikes() {
		return likes;
	}
	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
}
