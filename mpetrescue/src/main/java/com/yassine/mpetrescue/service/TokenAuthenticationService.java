package com.yassine.mpetrescue.service;

import static org.apache.commons.lang3.StringUtils.removeStart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.yassine.mpetrescue.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class TokenAuthenticationService implements UserAuthenticationService {
	private static final String BEARER = "Bearer";

	@NonNull
	TokenService tokens;
	@NonNull
	UserService users;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Optional<String> login(final String username, final String password) {
		return users.findByUsername(username).filter(user -> passwordEncoder.matches(password, user.getPassword()))
				.map(user -> tokens.expiring(ImmutableMap.of("username", username)));
	}

	@Override
	public Optional<User> findByToken(String token) {
		token = removeStart(token, BEARER);
		return Optional.of(tokens.verify(token)).map(map -> map.get("username")).flatMap(users::findByUsername);
	}

	@Override
	public void logout(final User user) {
		// Nothing to do
	}

}
