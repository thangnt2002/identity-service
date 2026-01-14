package com.thangnt.identity_service.repositories;

import com.thangnt.identity_service.entities.InvalidateJWT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidateJWTRepository extends JpaRepository<InvalidateJWT, String> {
}
