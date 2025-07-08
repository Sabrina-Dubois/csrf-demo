package co.simplon.p_business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.p_business.entities.UserSession;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
	UserSession findBySessionId(String sessionId);

	Boolean existsBySessionId(String sessionId);

}