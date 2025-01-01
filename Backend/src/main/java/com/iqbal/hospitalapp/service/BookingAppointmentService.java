package com.iqbal.hospitalapp.service;

import java.util.List;

import com.iqbal.hospitalapp.exception.AppointmentNotValidException;
import com.iqbal.hospitalapp.model.BookingAppointment;

public interface BookingAppointmentService {

	BookingAppointment addAppointment(long doctorId,long patientId, BookingAppointment bookingAppointment) throws AppointmentNotValidException ;

	List <BookingAppointment> getAllAppointments();

	BookingAppointment getAppointmentById(long appointmentId);

}
