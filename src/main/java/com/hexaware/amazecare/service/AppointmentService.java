package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.Appointment;
import com.hexaware.amazecare.repository.AppointmentRepository;
@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	public Appointment addAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	public List<Appointment> getAllAppointment() {
		return appointmentRepository.findAll();
	}

	public Appointment getAppointment(int id) {
		Optional<Appointment>optional=appointmentRepository.findById(id);
		return optional.get();
	}

}
