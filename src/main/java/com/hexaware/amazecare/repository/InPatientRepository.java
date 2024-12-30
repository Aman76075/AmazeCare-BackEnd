package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.model.InPatient;

public interface InPatientRepository extends JpaRepository<InPatient, Integer>{

	@Query("Select i from InPatient i where i.patient.id=?1")
	InPatient getPatientByPid(int pid);

}
