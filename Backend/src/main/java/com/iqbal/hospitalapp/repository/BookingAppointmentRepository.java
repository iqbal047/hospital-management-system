package com.iqbal.hospitalapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqbal.hospitalapp.model.BookingAppointment;

public interface BookingAppointmentRepository  extends JpaRepository<BookingAppointment,Long> {
	
	public List<BookingAppointment> findBookingAppointmentByPatientId(long patientId);

}