package com.yassine.mpetrescue.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yassine.mpetrescue.entity.Cas;

public interface CasDAO extends CrudRepository<Cas, Long> {

	@Query("select Count(*) from Cas c where c.statut = :statut")
	Long countCasesByStatut(@Param("statut") String statut);

}
