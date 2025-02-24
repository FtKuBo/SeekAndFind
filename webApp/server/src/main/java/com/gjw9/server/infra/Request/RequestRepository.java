package com.gjw9.server.infra.Request;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("select request from Request request where request.userEmail = ?1")
    Collection<Request> getAllRequestByUserEmail(String userEmail);
}
