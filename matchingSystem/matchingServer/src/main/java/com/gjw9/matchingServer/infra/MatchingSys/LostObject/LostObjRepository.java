package com.gjw9.matchingServer.infra.MatchingSys.LostObject;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LostObjRepository extends JpaRepository<LostObject, Long> {

    @Query("select obj from LostObject obj where obj.userEmail != ?1 and obj.objectType = ?2 and obj.objectBrand = ?3 and obj.objectLocation = ?4 and obj.objectDate <= ?5")
    Collection<LostObject> getAllMatchingObjects(String userEmail, String objectType, String objectBrand, String objectLocation, LocalDate objectDate);

    @Modifying(flushAutomatically = true)
    @Query("delete from LostObject obj where obj.userEmail = ?1 and obj.objectType = ?2 and obj.objectBrand =?3 and obj.objectDescription = ?4 and obj.objectLocation = ?5 and obj.objectDate = ?6 ")
    void deleteClones(String userEmail, String objectType, String objectBrand, String objectDescription, String objectLocation, LocalDate objectDate);
}
