package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
