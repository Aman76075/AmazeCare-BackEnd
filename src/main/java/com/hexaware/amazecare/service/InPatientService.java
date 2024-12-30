package com.hexaware.amazecare.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.amazecare.model.InPatient;
import com.hexaware.amazecare.repository.InPatientRepository;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;


@Service
public class InPatientService {
	@Autowired
	private InPatientRepository inPatientRepository;

	public InPatient insert(InPatient inPatient) {
		return inPatientRepository.save(inPatient);
		
	}

	public List<InPatient> getall() {
		return inPatientRepository.findAll();
	}
	public void uploadInPatientThruExcel(MultipartFile file) throws IOException {
		    // Step 1: Convert file into InputStream
		    InputStream inputStream = file.getInputStream();

		    // Step 2: Give this inputStream to BufferedReader
		    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		    // Step 3: Read the content line by line using loop and save them in InPatient objects
		    br.readLine(); // This reads the header line, which we ignore
		    String data = "";

		    List<InPatient> inPatientList = new ArrayList<>();
		    while ((data = br.readLine()) != null) {
		        InPatient inPatient = new InPatient();
		        String[] str = data.split(",");

		        inPatient.setName(str[0]);
		        inPatient.setGender(str[1]);
		        inPatient.setAge(Integer.parseInt(str[2]));
		        inPatient.setEmail(str[3]);
		        inPatient.setContact(str[4]);
		        inPatient.setEmergency_contact(str[5]);
		        inPatient.setAadhar(str[6]);
		        inPatient.setAddress(str[7]);
		        inPatient.setAllergies(str[8]);
		        //generate inPatient ID 
				int id = (int)(Math.random()* 10000000);
				inPatient.setId(id);

		        inPatientList.add(inPatient);
		    }
			/*Step 5: Save inPatient in batch */

		    inPatientRepository.saveAll(inPatientList);

		}
	
	public InPatient validate(int id) throws ResourceNotFoundException {
		Optional<InPatient> optional = inPatientRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Invalid ID");
		return optional.get();
	}



	public Page<InPatient> getAllInpatient(Pageable pageable) {
		return inPatientRepository.findAll(pageable);
	}

	public void deleteInPatientById(int id) {
		inPatientRepository.deleteById(id);		
	}

	public InPatient getPatientDetilasByPid(int pid) {
		return inPatientRepository.getPatientByPid(pid);
	}

	public InPatient getPatientDetilas(int pid) {
		return inPatientRepository.findById(pid).get();
	}


}
