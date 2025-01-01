package com.iqbal.hospitalapp.serviceimpl;

import java.text.ParseException;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbal.hospitalapp.exception.IdMismatchException;
import com.iqbal.hospitalapp.exception.PrescriptionNotValidException;
import com.iqbal.hospitalapp.exception.ResourceNotFoundException;
import com.iqbal.hospitalapp.model.BookingAppointment;
import com.iqbal.hospitalapp.model.Doctor;
import com.iqbal.hospitalapp.model.Patient;
import com.iqbal.hospitalapp.model.Prescription;
 
import com.iqbal.hospitalapp.repository.PatientRepository;
import com.iqbal.hospitalapp.repository.PrescriptionRepository;
import com.iqbal.hospitalapp.service.BookingAppointmentService;
import com.iqbal.hospitalapp.service.DoctorService;
import com.iqbal.hospitalapp.service.PatientService;
import com.iqbal.hospitalapp.service.PrescriptionService;

import javax.persistence.Tuple;


@Service
public class
PrescriptionserviceImpl implements PrescriptionService  {
	
	@Autowired
	public PrescriptionRepository prescriptionRepository;
	
	@Autowired
	public PatientRepository patientRepository; 
	
	@Autowired
	public BookingAppointmentService bookingAppointmentService;
	
	@Autowired
	public PatientService patientService;
	
	@Autowired
	public DoctorService doctorService;
	
	
	
	@Override
	public Prescription generatePrescription (Prescription prescription,long appointmentId) throws ParseException, IdMismatchException, PrescriptionNotValidException {
		
		BookingAppointment bookingAppointment = bookingAppointmentService.getAppointmentById(appointmentId);
		List<Prescription> prescriptions = prescriptionRepository.findAll();
		
		for(Prescription prescriptionsDetail : prescriptions) {
			BookingAppointment appointment = prescriptionsDetail.getBookingAppointment();
			if(prescriptionsDetail.getDoctorId()==prescription.getDoctorId()) {
				if(prescriptionsDetail.getPatientId()==prescription.getPatientId()) {
					if(appointment.getAppointmentId()==appointmentId) {
						throw new PrescriptionNotValidException ("Prescription is not valid :(");
					}
				}
			}
		}
		
		
		Doctor oldDoctor =  bookingAppointment.getDoctor();
 
		Doctor doctor = doctorService.getDoctorById(prescription.doctorId);
		Patient patient = patientService.getPatientById(prescription.patientId);
		
		if(doctor.getDoctorId()!= oldDoctor.getDoctorId()) {
			throw new IdMismatchException("Doctor id is not matching please check that one ");
		}else if(patient.getPatientId()!=bookingAppointment.getPatientId()) {
			throw new IdMismatchException("Patient id is not matching please check that one ");
		}
		
		prescription.setBookingAppointment(bookingAppointment);
	
		return prescriptionRepository.save(prescription);
	
}

	@Override
	public List<Prescription> getAllPrescriptions() {
		 
		return prescriptionRepository.findAll();
	}

	 
	@Override
	public void deletePrescription(long prescriptionId) {
	 
		
	}

	@Override
	public Prescription getPrescriptionById(long prescriptionId) {
		 
		return prescriptionRepository.findById(prescriptionId).orElseThrow(()->new ResourceNotFoundException("prescription","PrescriptionId",prescriptionId));
	}
	public Tuple getPrescriptionDetails(Long id){
		return prescriptionRepository.something(id);
	}


}
