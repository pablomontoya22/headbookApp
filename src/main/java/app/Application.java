package app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Like;
import app.entities.Post;
import app.entities.User;
import app.repositories.LikeRepository;
import app.repositories.PostRepository;
import app.repositories.UserRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private LikeRepository likeRepository;

	@Override
	protected SpringApplicationBuilder
		configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void stating() {
    	System.out.
    	println("***************** Iniciando SPRING BOOT APP *****************");
    }

    @Transactional
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	String[][] defaultUsers = new String[][] {
    		{"Ana", "Garcia", "https://www.webconsultas.com/sites/default/"
    				+ "files/styles/encabezado_articulo/public/migrated/"
    				+ "caracteristicas-timidez.jpg"},
    		{"Jorge", "Lozada", "https://www.vbout.com/images/persona/"
    				+ "buyer-persona-image1.png"},
    		{"Felipe", "Oropeza", "https://previews.123rf.com/images/grgroup/"
    				+ "grgroup1611/grgroup161108917/65491764-icono-de-dibujos-animados"
    				+ "-chico-ni%C3%B1ez-ni%C3%B1o-peque%C3%B1o-persona-personas"
    				+ "-y-el-tema-dise%C3%B1o-aislado-ilustraci%C3%B3n-vectorial.jpg"},
    		{"Roberto", "Muñoz", "https://cdn-images-1.medium.com/max/1200/"
    				+ "1*XKpA4-JcY06QcMOiPB1zaQ.jpeg"},
    		{"Eduardo", "Alvarado", "https://www.lacasaencendida.es/sites/default/files/"
    				+ "styles/full/public/primera_persona_2017_carlos_zanon_nt.jpg?itok=WDqEa7R4"},
    		{"Carolina", "Escalona", "http://www.medicinahiperbarica.com.mx/wp-content/"
    				+ "uploads/2015/11/persona-png.png"}};
        if (!userRepository.findAll().iterator().hasNext()) {
        	for (String[] user : defaultUsers)
        		userRepository.save(new User(user[0], user[1], user[2]));
        }
        Object[][] defaultPosts = new Object[][] {
        	{1L, "Hola Mundo!",
        		"Este es un post generado al início de la aplicación.", new Long[]{2L, 3L}},
        	{5L, "Hola Mundo de Eduardo!",
        			"Este es un post generado al início de la aplicación Eduardo.",
        			new Long[]{2L, 3L, 6L}}};
    	List<Like> likes;
        Post postLoop;
        for (Object[] post : defaultPosts) {
        	likes = new ArrayList<Like>(0);
        	postLoop = postRepository.save(new Post(userRepository.findById((Long) post[0]).get(),
        			post[1].toString(), post[2].toString()));
        	for (Long idUser : (Long [])post[3])
        		likes.add(likeRepository.save(new Like(userRepository.findById(idUser).get(), postLoop)));
        	postLoop.setLikes(likes);
        	postRepository.save(postLoop);
        }
        System.out.
    	println("***************** Corriendo SPRING BOOT APP *****************");
    }
}
