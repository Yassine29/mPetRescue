package com.yassine.mpetrescue.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yassine.mpetrescue.entity.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

	@Query("from User u where u.username = :username")
	Optional<User> findByUsername(@Param("username") String username);

	@Query("from User u where u.nbr_cas > 0")
	List<User> getActifUsers();
}
