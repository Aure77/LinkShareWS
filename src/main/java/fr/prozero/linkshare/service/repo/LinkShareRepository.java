package fr.prozero.linkshare.service.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fr.prozero.linkshare.model.Link;
import fr.prozero.linkshare.model.User;

@Transactional
public interface LinkShareRepository extends JpaRepository<Link, Long> {
		
	@Query("From Link l WHERE l.user = ?1 ORDER BY l.createDate DESC")
	public List<Link> findLastUserLinksFromUser(User user, Pageable page);
}
