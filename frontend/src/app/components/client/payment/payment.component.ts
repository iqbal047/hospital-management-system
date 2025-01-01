import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {

  prescriptionId: string = '';
  customer: any = {};
  nameOnCard: string = '';
  cardNumber: string = '';
  expYear: string = '';
  cvv: string = '';
  PaidDate: string = '';
  paidAmount: string = '';
  patientId: string = '';
  totalPayment: number = 0;
  admitDate: string = '';
  cost: number = 0;
  discount: number = 0;
 

  selectedOption = [];
  options = [
    
     { name: 'X-ray', value: 'X-ray' },
     { name: 'MRI', value: 'MRI' },
     { name: 'Bloodtest', value: 'Blood test' },
     { name: 'CBC', value: 'CBC' },
     { name: 'Urinetest', value: 'Urine Test' },
    
  ];



  constructor(
    private activtedRouter: ActivatedRoute,
    private service: HospitalService,
    private datePipe: DatePipe,
    private router: Router
  ) {
    this.service.isClientLoginPresent();
    this.patientId = this.service.getClientAuthorization();
    this.activtedRouter.queryParamMap.subscribe((res: any) => {
      console.log('>>>>', res);
      if (res?.params?.id) {
        this.prescriptionId = res?.params?.id;
        console.log('>>>>>>>>>>>', this.prescriptionId)
      }
    });
  }

  ngOnInit(): void {
    this.getCustomerDetail();
  }

  setPaidDate(ev: any): void {
    const date: any = this.datePipe.transform(ev?.value, 'yyyy-MM-dd');
    this.PaidDate = date;
  }

  setAdmitDate(ev: any): void {
    const date: any = this.datePipe.transform(ev?.value, 'yyyy-MM-dd');
    this.admitDate = date;
  }

  getCustomerDetail(): void {
    const cid = this.service.getClientAuthorization();
    this.service.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("Customer*****", res);
        if (!!res && res?.patientId) {
          this.customer = res;
        }
      }, err => {
        console.log("Err");
      }
    )
  }



  onPayment(): void {

    // if (this.nameOnCard.length=== 0 ) {
    //   alert("Name on card should not be blank");
    //   return;
    // }
    // if (this.cardNumber === '' || this.cardNumber.length < 16 || this.cardNumber.length > 16) {

    //   alert("card number exactly 16 digit");
    //   return;
    // }
    // if (this.expYear.length=== 0 ) {
    //   alert("Exp year  should not be blank");
    //   return;
    // }
    // if (this.cvv.length=== 0 ) {
    //   alert("CVV should not be blank");
    //   return;
    // }
    this.setPaidDate(new Date());
    const body: any = {

      totalPayment: this.totalPayment,
      roomId: 1,
      prescriptionId: this.prescriptionId,
      admitDate: this.admitDate,
      paymentDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
      paymentStatus: 'paid',
      nameOnCard: this.nameOnCard,
      cardNumber: parseInt(this.cardNumber),
      expYear: this.expYear,
      cvv: parseInt(this.cvv),
      patient: this.customer
     
    };
    this.service.addPayment(this.patientId, this.prescriptionId, body).pipe(take(1)).subscribe(
      (res: any) => {

        if (res && res?.paymentId) {
          // alert("Payment done sucessfulyy");

          sessionStorage.setItem("prescriptionId", this.prescriptionId);
          this.router.navigate(['/patient/bill']);

        }
      }, err => {
        console.log("error");
      }
    )
  }

}
