package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User login(User user) {
		User findUser = userRepo.findById(user.getUsername()).orElse(null);
		if (findUser != null) {
			String currentPass = user.getPassword();
			String originalPass = findUser.getPassword();
			if (currentPass.equals(originalPass))
				return findUser;
		}
		return null;
	}

}
