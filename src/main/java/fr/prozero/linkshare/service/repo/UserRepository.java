package fr.prozero.linkshare.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.prozero.linkshare.model.LsUser;

public interface UserRepository extends JpaRepository<LsUser, Long> {

	@Query("From LsUser u WHERE u.email = ?1")
	public LsUser findUserByEmail(String username);
	
}
