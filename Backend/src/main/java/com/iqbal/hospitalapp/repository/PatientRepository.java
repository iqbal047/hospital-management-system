package com.iqbal.hospitalapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

 
import com.iqbal.hospitalapp.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {

	Optional<Patient> findByPatientEmailIdAndPatientPassword(String EmailId,String Password);
	public List<Patient> findPatientByDoctorId(long doctorId);
	

}
 