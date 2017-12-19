package org.gl3.rentos.repository;

import org.gl3.rentos.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {
    public User findByEmail(String email);
}
