package com.yassine.mpetrescue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yassine.mpetrescue.dao.CasDAO;
import com.yassine.mpetrescue.entity.Cas;
import com.yassine.mpetrescue.util.ConstantsSetting;

@Service
public class CasServiceImpl implements CasService {

	@Autowired
	CasDAO casDao;

	@Override
	public Cas save(Cas cas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Optional<Cas>> find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Cas>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countCas() {
		return casDao.count();
	}

	@Override
	public Long countResolvedCases() {
		return casDao.countCasesByStatut(ConstantsSetting.STATUT_CAS_TRAITE);
	}

}
