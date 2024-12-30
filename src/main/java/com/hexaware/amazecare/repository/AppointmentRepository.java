
package com.hexaware.amazecare.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.enums.Appointment_Status;
import com.hexaware.amazecare.model.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query("SELECT a FROM Appointment a JOIN a.doctor d WHERE a.status = ?1 AND d.id = ?2")
	List<Appointment> getAllAppointmentsByDoctor(Appointment_Status status, int id);
	
    @Query("Select COALESCE(i.name, o.name) as name, " +
    	       "COALESCE(i.age, o.age) as age, " +
    	       "COALESCE(i.gender, o.gender) as gender, " +
    	       "p.patientType as patient_type, " +
    	       "a.date as date, " +
    	       "a.timeSlot as timeSlot, " +
    	       "a.status as status, " +
    	       "a.id as appointmentId, "+
    	       "p.id as patientId "+
    	       "FROM Appointment a " +
    	       "JOIN a.patient p " +
    	       "LEFT JOIN InPatient i ON p.id = i.patient.id " +
    	       "LEFT JOIN OutPatient o ON p.id = o.patient.id " +
    	       "WHERE a.status=?1 AND a.doctor.id=?2")
	List<Object[]> fetchAllAppointments(Appointment_Status as, int did);

}
