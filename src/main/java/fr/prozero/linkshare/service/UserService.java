package fr.prozero.linkshare.service;

import fr.prozero.linkshare.model.LsUser;

public interface UserService {
	
	public LsUser getUserById(Long userId);

	public LsUser getUserByName(String username);
	
	public LsUser createUser(LsUser user);
	
}
