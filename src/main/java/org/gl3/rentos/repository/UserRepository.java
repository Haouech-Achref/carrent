package org.gl3.rentos.repository;

import org.gl3.rentos.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
