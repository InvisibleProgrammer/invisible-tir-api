package com.invisibleprogrammer.invisibletirapi.domain.repository;

import com.invisibleprogrammer.invisibletirapi.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsUserByEmailAddress(String emailAddress);
}
