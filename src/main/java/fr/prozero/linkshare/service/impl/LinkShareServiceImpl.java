package fr.prozero.linkshare.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.prozero.linkshare.model.LsLink;
import fr.prozero.linkshare.model.LsUser;
import fr.prozero.linkshare.service.LinkShareService;
import fr.prozero.linkshare.service.repo.LinkShareRepository;

@Service
public class LinkShareServiceImpl implements LinkShareService {

	@Autowired
	private LinkShareRepository linkshareRepo;
	
	private static Logger LOGGER = LoggerFactory.getLogger(LinkShareServiceImpl.class);

	@Override
	public List<LsLink> getLastUserLinks(LsUser user) {
		LOGGER.info("Get Links for User :" + user.getId());
		return linkshareRepo.findLastUserLinksFromUser(user, new PageRequest(0, 30));
	}

	@Override
	public LsLink addLinkToUser(LsUser user, String link) {
		LsLink l = new LsLink(link, new Date(), user);
		LOGGER.info("Add new Link for User :" + user.getId());
		return linkshareRepo.save(l);
	}
}
