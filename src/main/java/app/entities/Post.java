package app.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "User can't be empty")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;
	@NotNull(message = "Date can't be empty")
	private Date date = new Date();
	@NotNull(message = "Title can't be empty")
	private String title;
	@NotNull(message = "Content can't be empty")
	@Column(length = 500)
	private String content;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Like> likes;

	public Post() {}

	public Post(@NotNull(message = "User can't be empty") User user,
			@NotNull(message = "Title can't be empty") String title,
			@NotNull(message = "Content can't be empty") String content) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
	}

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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Like> getLikes() {
		return likes;
	}
	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
}
