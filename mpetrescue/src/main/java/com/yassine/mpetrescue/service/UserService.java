package com.yassine.mpetrescue.service;

import java.util.List;
import java.util.Optional;

import com.yassine.mpetrescue.entity.User;

/**
 * User security operations like login and logout, and CRUD operations on
 * {@link User}.
 * 
 * @author yassine
 *
 */

public interface UserService {

	User save(User user);

	Optional<User> findByUsername(String username);

	Long countUsers();

	// Récupère les utilisateurs les plus actifs durant le dernier mois
	List<User> getTopUsers(Integer UsersNumber);

	Optional<Optional<User>> find(Long id);
}
