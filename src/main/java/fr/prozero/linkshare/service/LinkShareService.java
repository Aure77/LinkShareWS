package fr.prozero.linkshare.service;

import java.util.List;

import fr.prozero.linkshare.model.LsLink;
import fr.prozero.linkshare.model.LsUser;

public interface LinkShareService {
	
	public List<LsLink> getLastUserLinks(LsUser user);
	
	public LsLink addLinkToUser(LsUser user, String link);
	
}
