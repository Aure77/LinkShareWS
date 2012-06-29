package fr.prozero.linkshare.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.prozero.linkshare.model.Link;
import fr.prozero.linkshare.model.User;
import fr.prozero.linkshare.service.LinkShareService;
import fr.prozero.linkshare.service.repo.LinkShareRepository;

@Service
public class LinkShareServiceImpl implements LinkShareService {

	@Autowired
	private LinkShareRepository linkshareRepo;

	@Override
	public List<Link> getLastUserLinks(User user) {
		return linkshareRepo.findLastUserLinksFromUser(user, new PageRequest(0, 30));
	}

	@Override
	public Link addLinkToUser(User user, String link) {
		Link l = new Link(link, new Date(), user);
		return linkshareRepo.save(l);
	}
}
