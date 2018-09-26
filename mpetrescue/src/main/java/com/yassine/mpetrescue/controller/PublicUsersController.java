package com.yassine.mpetrescue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yassine.mpetrescue.entity.User;
import com.yassine.mpetrescue.service.CasService;
import com.yassine.mpetrescue.service.UserAuthenticationService;
import com.yassine.mpetrescue.service.UserService;
import com.yassine.mpetrescue.util.ConstantsSetting;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/public")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class PublicUsersController {

	@NonNull
	UserAuthenticationService authentication;
	@NonNull
	UserService users;

	@Autowired
	CasService cas;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping(value = "/users/register", produces = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
	String register(@RequestBody User user) {
		String xPassword = passwordEncoder.encode(user.getPassword());
		users.save(User.builder().username(user.getUsername()).password(xPassword).email(user.getEmail())
				.firstName(user.getFirstName()).lastName(user.getLastName()).Rang(ConstantsSetting.DEFAULT_RANG)
				.nbr_cas(0)
				.photo(ConstantsSetting.DEFAULT_USER_AVATAR).build());

		return login(user);
	}

	@PostMapping(value = "/users/login", produces = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
	String login(@RequestBody User user) {
		return authentication.login(user.getUsername(), user.getPassword())
				.orElseThrow(() -> new RuntimeException("invalid login and/or password"));
	}

	@GetMapping("/getUsersCount")
	Long getUsersCount() {
		return users.countUsers();
	}

	@GetMapping("/getCasCount")
	Long getCasCount() {
		return cas.countCas();
	}

	@GetMapping("/getRslvdCasCount")
	Long getRslvedCasesCount() {
		return cas.countResolvedCases();
	}

	@GetMapping("/getTopUsers")
	List<User> getTopUsers() {
		return users.getTopUsers(ConstantsSetting.VIEW_TOP_USERS_NUMBER);
	}
	
	@GetMapping("/users/getUser")
	Optional<User> findUserByToken(@RequestHeader("Authorization") String bearerToken) {
		return authentication.findByToken(bearerToken);
	}

}
