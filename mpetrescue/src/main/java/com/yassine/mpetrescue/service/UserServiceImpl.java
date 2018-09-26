package com.yassine.mpetrescue.service;

import static java.util.Optional.ofNullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yassine.mpetrescue.dao.CasDAO;
import com.yassine.mpetrescue.dao.UserDAO;
import com.yassine.mpetrescue.entity.Cas;
import com.yassine.mpetrescue.entity.User;
import com.yassine.mpetrescue.util.CollectionUtils;
import com.yassine.mpetrescue.util.DateUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	CasDAO casDao;

	@Override
	public User save(final User user) {
		return userDao.save(user);
	}

	@Override
	public Optional<Optional<User>> find(final Long id) {
		return ofNullable(userDao.findById(id));
	}

	@Override
	public Optional<User> findByUsername(final String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public Long countUsers() {
		return userDao.count();
	}

	@Override
	public List<User> getTopUsers(Integer UsersNumber) {
		List<User> topUsers = new ArrayList<User>();
		Iterable<Cas> allCas = casDao.findAll();
		HashMap<Long, Integer> userNbrCas = new HashMap<Long, Integer>();
		LocalDate localDate = LocalDate.now();

		for (Cas cas : allCas) {
			LocalDate dateCas = DateUtils.convertToDate(cas.getDate_creation());
			// Récupérer les cas créés durant les 30 derniers jours
			if (dateCas.isBefore(localDate) && dateCas.isAfter(localDate.minusDays(30))) {
				if (userNbrCas.get(cas.getRapporteur().getId()) == null) {
					userNbrCas.put(cas.getRapporteur().getId(), 1);
				} else {
					userNbrCas.replace(cas.getRapporteur().getId(), (userNbrCas.get(cas.getRapporteur().getId())) + 1);
				}
			}
		}

		LinkedHashMap<Long, Integer> sortedUserNbrCas = CollectionUtils.sortHashMapByValues(userNbrCas);

		int usrCounter = 0;

		for (Map.Entry<Long, Integer> entry : sortedUserNbrCas.entrySet()) {
			Long key = entry.getKey();
			topUsers.add((userDao.findById(key)).get());
			usrCounter++;

			if (usrCounter == UsersNumber) {
				break;
			}
		}

		return topUsers;
	}

}
