package com.hexaware.amazecare.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.InPatient;
import com.hexaware.amazecare.model.OutPatient;
import com.hexaware.amazecare.repository.OutPatientRepository;

@Service
public class OutPatientService {
	@Autowired
	private OutPatientRepository outPatientRepository;

	public OutPatient insert(OutPatient outPatient) {
		return outPatientRepository.save(outPatient);
	}

	public List<OutPatient> getall() {
		return outPatientRepository.findAll();
	}
	public void uploadOutPatientThruExcel(MultipartFile file) throws IOException {
	    // Step 1: Convert file into InputStream
	    InputStream inputStream = file.getInputStream();

	    // Step 2: Give this inputStream to BufferedReader
	    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

	    // Step 3: Read the content line by line using loop and save them in OutPatient objects
	    br.readLine(); // This reads the header line, which we ignore
	    String data;

	    List<OutPatient> outPatientList = new ArrayList<>();
	    while ((data = br.readLine()) != null) {
	        OutPatient outPatient = new OutPatient();
	        String[] str = data.split(",");

	        outPatient.setName(str[0]);
	        outPatient.setContact(str[1]);
	        outPatient.setGender(str[2]);
	        outPatient.setAge(Integer.parseInt(str[3]));

	        // Generate OutPatient ID
	        int id = (int) (Math.random() * 10000000);
	        outPatient.setId(id);

	        outPatientList.add(outPatient);
	    }

	    // Step 5: Save OutPatient in batch
	    outPatientRepository.saveAll(outPatientList);
	}
	
	public OutPatient validate(int id) throws ResourceNotFoundException {
		Optional<OutPatient> optional = outPatientRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Invalid ID");
		return optional.get();
	}

	public void deleteOutPatientById(int id) {
		outPatientRepository.deleteById(id);
		
	}

	public Page<OutPatient> getAllOutpatient(Pageable pageable) {
		return outPatientRepository.findAll(pageable);
	}

}
