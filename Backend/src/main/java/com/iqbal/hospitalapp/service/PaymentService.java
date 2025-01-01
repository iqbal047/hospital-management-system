package com.iqbal.hospitalapp.service;

import java.util.List;

import com.iqbal.hospitalapp.exception.IdMismatchException;
import com.iqbal.hospitalapp.model.Payment;

public interface PaymentService {

	


	Payment addPayment(Payment payment,long patientId,long prescriptionId) throws IdMismatchException, Exception;

	List<Payment> getAllPayments();

	Payment getPaymentById(long paymentId);
}
