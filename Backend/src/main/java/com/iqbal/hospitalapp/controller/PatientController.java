package com.iqbal.hospitalapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iqbal.hospitalapp.model.Patient;
import com.iqbal.hospitalapp.model.Payment;
import com.iqbal.hospitalapp.model.Prescription;
import com.iqbal.hospitalapp.service.PatientService;
import com.iqbal.hospitalapp.service.PaymentService;
import com.iqbal.hospitalapp.service.PrescriptionService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//POST http://localhost:8088/api/patient/register
//PUT http://localhost:8088/api/patient/{patientId}
//GET http://localhost:8088/api/patient/{patientId}
//ET http://localhost:8088/api/patient/prescription
//GET http://localhost:8088/api/patient/prescription/{prescriptionId}
// GET http://localhost:8088/api/patient
//http://localhost:8088/api/patient

@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	public PatientService patientService;
	
	@Autowired
	public PrescriptionService prescriptionService;
	
	@Autowired
	public PaymentService paymentService;
	
	//Registration
	@PostMapping("/register")
	public ResponseEntity<Patient> saveAdmin(@RequestBody Patient patient){
		System.out.println("Patient Registration Succesfull "+patient);
		return new ResponseEntity<Patient>(patientService.savePatient(patient),HttpStatus.CREATED);
	}
	
	//login
	@PostMapping("/login")
	public ResponseEntity<Patient> loginPatient(@RequestBody Patient patient){
		return new ResponseEntity<Patient>(patientService.loginPatient(patient),HttpStatus.OK);
	}
	
	@PutMapping("{patientId}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("patientId") long patientId, @RequestBody Patient patient) {
	
		return new ResponseEntity<Patient>(patientService.updatePatient(patient,patientId),HttpStatus.OK);
	}
	
	@GetMapping("{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("patientId") long patientId){
		
		return new ResponseEntity<Patient>(patientService.getPatientById(patientId),HttpStatus.OK);
	}
	
	@GetMapping("/prescription")
	public List<Prescription> getAllPrescriptions(){
		return prescriptionService.getAllPrescriptions();
	}
	
	@GetMapping("/prescription/{precriptionId}")
	public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("prescriptionId") long prescriptionId){
		
		return new ResponseEntity<Prescription>(prescriptionService.getPrescriptionById(prescriptionId),HttpStatus.OK);
		
	}
	
	@GetMapping
	public List<Payment> getAllPayments(){
		return paymentService.getAllPayments();
	}

	@DeleteMapping("{patientId}")
	public ResponseEntity<String> deletePatient(@PathVariable("patientId") long patientId) {
		patientService.deletePatientById(patientId);
		return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
	}


}
