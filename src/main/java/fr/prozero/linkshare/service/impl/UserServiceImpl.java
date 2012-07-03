package fr.prozero.linkshare.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.prozero.linkshare.model.LsUser;
import fr.prozero.linkshare.service.UserService;
import fr.prozero.linkshare.service.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public LsUser getUserById(Long userId) {
		LOGGER.info("Get User : " + userId);
		return userRepo.findOne(userId);
	}

	@Override
	public LsUser getUserByEmail(String email) {
		LOGGER.info("Get User by Email : " + email);
		return userRepo.findUserByEmail(email);
	}

	@Override
	public LsUser createUser(LsUser user) {
		LOGGER.info("Create User : " + user.getId());
		return userRepo.save(user);
	}

	@Override
	public void updateUser(LsUser user) {
		LOGGER.info("Update User : " + user.getId());
		userRepo.save(user);		
	}
}
