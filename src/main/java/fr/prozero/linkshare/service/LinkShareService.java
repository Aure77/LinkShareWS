package fr.prozero.linkshare.service;

import java.util.List;

import fr.prozero.linkshare.model.Link;
import fr.prozero.linkshare.model.User;

public interface LinkShareService {
	
	public List<Link> getLastUserLinks(User user);
	
	public Link addLinkToUser(User user, String link);
	
}
