package com.gjw9.matchingServer.infra.MatchingSys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingObjRepository extends JpaRepository<MatchingObj, Long> {
}
