import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

import { HospitalService } from '../../service/hospital.service';




@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.scss']
})
export class BillComponent implements OnInit {
  

  prescriptionId: any = '';
  customer: any = {};
  nameOnCard: any = '';
  cardNumber: any = '';
  expYear: any = '';
  cvv: any = '';
  PaidDate: any = '';
  paidAmount: any = '';
  patientId: any = '';
  totalPayment: number = 0;
  admitDate: any = '';



  constructor(
    private service: HospitalService
    
    ) { }



  ngOnInit(): void {
    this.patientId = this.service.getClientAuthorization();
    this.customer.Name = this.service.getClientName();
    this.prescriptionId = sessionStorage.getItem("prescriptionId");
    {
      // "admit_date": "2023-11-21T08:05:10.711+00:00",
      // "cvv": 0,
      // "patient_id": 3101,
      // "patient_name": "kamal Rabby",
      // "prescription_id": 6457,
      // "exp_year": "0",
      // "payment_Date": "2023-11-21T08:05:10.711+00:00",
      // "total_payment": 1000,
      // "name_on_card": "0"
  }
  
  // prescriptionId: any = '';
  // customer: any = {};
  // nameOnCard: any = '';
  // cardNumber: any = '';
  // expYear: any = '';
  // cvv: any = '';
  // PaidDate: any = new Date();
  // paidAmount: any = '';
  // patientId: any = '';
  // totalPayment: number = 1000;
  // admitDate: any = '';

    this.service.getPrescriptionDetails(Number(this.prescriptionId)).subscribe((res:any)=>{
       this.nameOnCard= res.name_on_card;
       this.expYear = res.exp_year;
       this.admitDate = res.admit_date;
       this.cvv = res.cvv;
       this.PaidDate = res.payment_Date;
       this.totalPayment = res.total_payment;
       this.cardNumber = res.cardNumber;
      },
    (error) => {
      console.error('Error fetching prescription details:', error);
    }
    );
   
    
    

  }
  









 


}






