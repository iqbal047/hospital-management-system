package com.iqbal.hospitalapp.controller;

 

import com.iqbal.hospitalapp.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.iqbal.hospitalapp.service.PrescriptionService;

import javax.persistence.Tuple;
import java.util.Map;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//http://localhost:8088/api/Prescription
@RequestMapping("/api/Prescription")
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;

	//delete by id
	@DeleteMapping("{PriscriptionId}")

	public ResponseEntity<Boolean> deletePrescription(@PathVariable("prescriptionId") long prescriptionId) {
		prescriptionService.deletePrescription(prescriptionId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

	@GetMapping("/prescription/{id}")
	public Map<String, Object> getPrescriptionDetails(@PathVariable Long id) {
		Tuple prescriptionDetails = prescriptionService.getPrescriptionDetails(id);
		return CommonUtil.tupleToMap(prescriptionDetails);

	}

}