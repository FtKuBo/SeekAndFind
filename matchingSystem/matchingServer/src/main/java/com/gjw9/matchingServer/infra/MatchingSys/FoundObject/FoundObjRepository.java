package com.gjw9.matchingServer.infra.MatchingSys.FoundObject;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoundObjRepository extends JpaRepository<FoundObject, Long> {

    @Query("select obj from FoundObject obj where obj.userEmail != ?1 and obj.objectType = ?2 and obj.objectLocation = ?3 and obj.objectDate <= ?4")
    Collection<FoundObject> getAllMatchingObjects(String userEmail, String objectType, String objectLocation, LocalDate objectDate);

    @Modifying(flushAutomatically = true)
    @Query("delete from FoundObject obj where obj.userEmail = ?1 and obj.objectType = ?2 and obj.objectDescription = ?3 and obj.objectLocation = ?4 and obj.objectDate = ?5 ")
    void deleteClones(String userEmail, String objectType, String objectDescription, String objectLocation, LocalDate objectDate);
}
