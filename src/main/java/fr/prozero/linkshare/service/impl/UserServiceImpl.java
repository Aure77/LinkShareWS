package fr.prozero.linkshare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.prozero.linkshare.model.User;
import fr.prozero.linkshare.service.UserService;
import fr.prozero.linkshare.service.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User getUserById(Long userId) {
		return userRepo.findOne(userId);
	}

}
