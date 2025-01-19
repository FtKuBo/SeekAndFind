package com.gjw9.matchingServer.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gjw9.matchingServer.database.entity.ObjTrouve;


@Repository
public interface foundObjRepository extends JpaRepository<ObjTrouve, Integer> {

}