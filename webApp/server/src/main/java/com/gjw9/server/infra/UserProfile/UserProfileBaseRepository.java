package com.gjw9.server.infra.UserProfile;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
 
@NoRepositoryBean
interface UserProfileBaseRepository<T, ID> extends Repository<T, ID> {
 
    void deleteById(ID deleted);
     
    Optional<T> findById(ID id);

    T save(T persisted);
}
