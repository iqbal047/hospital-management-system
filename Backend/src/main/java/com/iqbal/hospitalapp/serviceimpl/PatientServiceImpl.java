package com.iqbal.hospitalapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbal.hospitalapp.exception.ResourceNotFoundException;
import com.iqbal.hospitalapp.model.Patient;
import com.iqbal.hospitalapp.repository.PatientRepository;
import com.iqbal.hospitalapp.service.PatientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	public PatientRepository patientRepository;

	@Override
	public Patient loginPatient(Patient patient) {
		return this.patientRepository.findByPatientEmailIdAndPatientPassword(patient.patientEmailId,patient.patientPassword).orElseThrow(()->new ResourceNotFoundException("Patient ", "EmaildId",patient.patientEmailId+"and password "+patient.patientPassword)); 
	}

	@Override
	public Patient savePatient(Patient patient) {
		System.out.println("Admin Registration Succesfull "+patient);
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatient(Patient patient, long patientId) {
		Patient oldPatient =patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Patient ","PatientId",patientId));
		
		oldPatient.setAddress(patient.getAddress());
		oldPatient.setAge(patient.getAge());
		oldPatient.setBloodGroup(patient.getBloodGroup());
		oldPatient.setContactNumber(patient.getContactNumber());
		
		oldPatient.setFirstName(patient.getFirstName());
		oldPatient.setGender(patient.getGender());
		oldPatient.setLastName(patient.getLastName());
		oldPatient.setMedicalBackground(patient.getMedicalBackground());
		oldPatient.setPatientEmailId(patient.getPatientEmailId());
		oldPatient.setPatientPassword(patient.getPatientPassword());
		
		patientRepository.save(oldPatient);
		
		return oldPatient;
	}

	@Override
	public Patient getPatientById(long patientId) {
		 
		return patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Patient","PatientId",patientId));
	}
	
	 

}
