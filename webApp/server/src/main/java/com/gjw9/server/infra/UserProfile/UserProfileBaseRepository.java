package com.gjw9.server.infra.UserProfile;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
 
@NoRepositoryBean
interface UserProfileBaseRepository<T, ID> extends Repository<T, ID> {
 
    void delete(T deleted);
     
    T findOne(ID id);
 
    T save(T persisted);
}
