package com.invisibleprogrammer.invisibletirapi.domain.repository;

import com.invisibleprogrammer.invisibletirapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmailAddress(String emailAddress);
}
