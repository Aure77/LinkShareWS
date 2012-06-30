package fr.prozero.linkshare.service.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fr.prozero.linkshare.model.LsLink;
import fr.prozero.linkshare.model.LsUser;

@Transactional
public interface LinkShareRepository extends JpaRepository<LsLink, Long> {
		
	@Query("From LsLink l WHERE l.user = ?1 ORDER BY l.createDate DESC")
	public List<LsLink> findLastUserLinksFromUser(LsUser user, Pageable page);
}
