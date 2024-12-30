package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.model.Executive;

public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

	@Query("Select e from Executive e where e.user.id=?1")
	Executive getExecutiveDetails(int id);

}
