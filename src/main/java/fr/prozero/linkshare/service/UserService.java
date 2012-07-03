package fr.prozero.linkshare.service;

import fr.prozero.linkshare.model.LsUser;

public interface UserService {
	
	public LsUser getUserById(Long userId);

	public LsUser getUserByEmail(String email);
	
	public LsUser createUser(LsUser user);

	public void updateUser(LsUser user);
	
}
