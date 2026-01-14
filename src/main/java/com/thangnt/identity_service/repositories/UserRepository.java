package com.thangnt.identity_service.repositories;

import com.thangnt.identity_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String id);
    User findByUsername(String username);
    boolean existsByUsername(String username);

}
