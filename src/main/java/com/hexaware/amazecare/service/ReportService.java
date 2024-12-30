
package com.hexaware.amazecare.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.ReportDetailsDto;
import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;

	public Report addReport(Report report) {
		// TODO Auto-generated method stub
		return reportRepository.save(report);
	}

	public Page<ReportDetailsDto> fetchReport(int pid,Pageable pageable){
		Page<Object[]> pageObjArray = reportRepository.fetchReport(pid, pageable);

	    // Convert Object[] to ReportDetailsDto
	    Page<ReportDetailsDto> dtoPage = pageObjArray.map(obj -> {
	        String name = (String) obj[0];
	        int age = (int) obj[1];
	        String gender = (String) obj[2];
	        String patient_type = obj[3].toString();
	        String doctorName = (String) obj[4];
	        String labOperatorName = (String) obj[5];
	        String department = obj[6].toString();
	        LocalDate generatedOn = (LocalDate) obj[7];
	        String scanTestType = obj[8].toString();
	        String description = (String) obj[9];

	        ReportDetailsDto dto = new ReportDetailsDto();
	        dto.setName(name);
	        dto.setAge(age);
	        dto.setGender(gender);
	        dto.setPatient_type(patient_type);
	        dto.setDoctorName(doctorName);
	        dto.setLabOperatorName(labOperatorName);
	        dto.setDepartment(department);
	        dto.setGeneratedOn(generatedOn);
	        dto.setScanTestType(scanTestType);
	        dto.setDescription(description);
	        return dto;
	    });

	    return dtoPage;
	}

}
