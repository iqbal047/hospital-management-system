package com.iqbal.hospitalapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqbal.hospitalapp.model.Prescription;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;


public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
	Optional<Prescription> findByPrescriptionIdAndPatientId(long prescriptionId,long patientId);

@Query(value = "SELECT\n" +
        "    CONCAT(p.first_name, ' ', p.last_name) AS patient_name,\n" +
        "    pr.prescription_id,\n" +
        "    pr.patient_id,\n" +
        "    pay.total_payment,\n" +
        "    pay.payment_Date,\n" +
        "    pay.name_on_card,\n" +
        "    pay.cvv,\n" +
        "    pay.exp_year,\n" +
        "    pay.admit_date\n" +
        "FROM prescription_table AS pr\n" +
        "JOIN patient_table AS p ON pr.patient_id = p.patient_id\n" +
        "JOIN payment_table AS pay ON pr.prescription_id = pay.prescription_id\n" +
        "WHERE pr.prescription_id = :id", nativeQuery = true)

 Tuple something(Long id);



}