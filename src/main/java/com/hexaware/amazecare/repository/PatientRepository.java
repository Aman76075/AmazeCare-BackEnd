package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("Select p from Patient p where p.user.id=?1")
	Patient getPatientByUserId(int id);

}
