package com.gjw9.server.infra.Notification;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificartionRepository extends JpaRepository<Notification, Long> {
}
