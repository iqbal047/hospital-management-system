package com.iqbal.hospitalapp.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iqbal.hospitalapp.exception.IdMismatchException;
import com.iqbal.hospitalapp.exception.PrescriptionNotValidException;
import com.iqbal.hospitalapp.model.BookingAppointment;
import com.iqbal.hospitalapp.model.Doctor;
import com.iqbal.hospitalapp.model.Patient;
import com.iqbal.hospitalapp.model.Prescription;
import com.iqbal.hospitalapp.service.BookingAppointmentService;
import com.iqbal.hospitalapp.service.DoctorService;
import com.iqbal.hospitalapp.service.PrescriptionService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//http://localhost:8088/api/doctor
//POST http://localhost:8088/api/doctor/register (for registration)
//GET http://localhost:8088/api/doctor/1 (for getting a doctor by ID)
//DELETE http://localhost:8088/api/doctor/1 (for deleting a doctor by ID)
//GET http://localhost:8088/api/doctor/booking (for getting all appointments)
@RequestMapping("/api/doctor")
public class DoctorController {

	@Autowired
	public DoctorService doctorService;
	
	@Autowired
	public PrescriptionService prescriptionService;
	
	@Autowired
	public BookingAppointmentService bookingAppointmentService;
	//registration
	@PostMapping("/register")
	public ResponseEntity<Doctor> saveAdmin(@RequestBody Doctor doctor){
		System.out.println("Doctor Registration Succesfull "+doctor);
		return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor),HttpStatus.CREATED);
	}
	//log in
	@PostMapping("/login")
	public ResponseEntity<Doctor> loginDoctor(@RequestBody Doctor doctor){
		return new ResponseEntity<Doctor>(doctorService.loginDoctor(doctor),HttpStatus.OK);
	}
	
	@PutMapping("{doctorId}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor) {
	
		return new ResponseEntity<Doctor>(doctorService.updateDoctor(doctor,doctorId),HttpStatus.OK);
	}
	
	@GetMapping("/booking")
	public List<BookingAppointment> getAllAppointments(){
		
		return bookingAppointmentService.getAllAppointments() ;
	}
	
	@PostMapping("/register/prescription/{appointmentId}")
	public ResponseEntity<Prescription> generatePrescription(@RequestBody Prescription prescription,@PathVariable("appointmentId") long appointmentId) throws ParseException, IdMismatchException, PrescriptionNotValidException{
		 	return new ResponseEntity<Prescription>(prescriptionService.generatePrescription(prescription,appointmentId),HttpStatus.CREATED);
	}
	
	@GetMapping("patient/{doctorId}")
	public List<Patient> getAllPatientsByDoctorId(@PathVariable("doctorId") long doctorId){
		
		return doctorService.getAllPatientsByDoctorId(doctorId);
	}
	
	@GetMapping("/doctor")
	public List<Doctor> getAllDoctors(){
		return doctorService.getAllDoctors();
	}
	
	// delete
	@DeleteMapping("{doctorId}")
	public ResponseEntity<Boolean> deleteDoctorById(@PathVariable long doctorId) {
		doctorService.deleteDoctor(doctorId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
	
	@GetMapping("{doctorId}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") long doctorId){
		
		return new ResponseEntity<Doctor>(doctorService.getDoctorById(doctorId),HttpStatus.OK);
	}
	
	
}
