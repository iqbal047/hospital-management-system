package com.iqbal.hospitalapp.service;

import java.text.ParseException;
import java.util.List;

import javax.persistence.Tuple;

import com.iqbal.hospitalapp.exception.IdMismatchException;
import com.iqbal.hospitalapp.exception.PrescriptionNotValidException;
import com.iqbal.hospitalapp.model.Prescription;


public interface PrescriptionService {
	
	 
	
	List<Prescription> getAllPrescriptions();
	
	//Prescription getPrescriptionById(long prescriptionId,long patientId);
	
	void deletePrescription(long prescriptionId);
	
	Prescription getPrescriptionById(long prescriptionId);


	Prescription generatePrescription(Prescription prescription, long appointmentId) throws ParseException, IdMismatchException, PrescriptionNotValidException;


    Tuple getPrescriptionDetails(Long id);
}
