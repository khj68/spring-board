package com.jun.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import com.jun.tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}