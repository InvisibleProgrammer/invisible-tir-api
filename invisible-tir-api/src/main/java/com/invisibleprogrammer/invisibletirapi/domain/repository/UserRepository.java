package com.invisibleprogrammer.invisibletirapi.domain.repository;

import com.invisibleprogrammer.invisibletirapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmailAddress(String emailAddress);

    Optional<User> findUserByEmailAddress(String emailAddress);
}
