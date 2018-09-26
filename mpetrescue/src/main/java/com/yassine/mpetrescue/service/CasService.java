package com.yassine.mpetrescue.service;

import java.util.List;
import java.util.Optional;

import com.yassine.mpetrescue.entity.Cas;

public interface CasService {

	Cas save(Cas cas);

	Optional<Optional<Cas>> find(String id);

	Optional<List<Cas>> getAll();

	Long countCas();

	Long countResolvedCases();

}
