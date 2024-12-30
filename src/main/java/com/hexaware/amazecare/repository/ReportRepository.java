
package com.hexaware.amazecare.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{

	@Query("SELECT COALESCE(i.name, o.name) AS name, " +
		       "COALESCE(i.age, o.age) AS age, " +
		       "COALESCE(i.gender, o.gender) AS gender, " +
		       "p.patientType AS patient_type, " +
		       "d.name AS doctorName, " +
		       "l.name AS labOperatorName, " +
		       "d.department AS department, " +
		       "r.generatedOn AS generatedOn, " +
		       "r.scanTestType AS scanTestType, " +
		       "r.description AS description " +
		"FROM Report r " +
		"JOIN r.doctor d " +
		"JOIN r.labOperator l " +
		"JOIN r.patient p " +
		"LEFT JOIN InPatient i ON i.id = p.id " +
		"LEFT JOIN OutPatient o ON o.id = p.id " +
		"WHERE p.id = ?1"
)
	Page<Object[]> fetchReport(int pid,Pageable pageable);

}
