package fr.prozero.linkshare.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.prozero.linkshare.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
