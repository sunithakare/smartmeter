import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpclientService, APIResponse } from 'src/app/services/httpclient.service';
import { environment} from 'src/environments/environment';

import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AllDivisionData } from '../../ci-agency-allotment-list/ci-agency-allotment-details/ci-agency-allotment-details.component';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { FieldUserAllotmentDetails } from '../../ci-field-user-allotment/ci-field-user-allotment.component';


@Component({
  selector: 'app-ci-supervisor-allotment-details',
  templateUrl: './ci-supervisor-allotment-details.component.html',
  styleUrls: ['./ci-supervisor-allotment-details.component.css']
})
export class CiSupervisorAllotmentDetailsComponent implements OnInit {

@Input()
isNavigation: boolean = false;
@Input()
isEditable: boolean = false;

@Input()
userAllotmentDetails: FieldUserAllotmentDetails;

@Output() dataSaved: EventEmitter<number> = new EventEmitter();

spinner: boolean = false;
agencyStore:any;
fieldUserStore:any;
fieldSave:any;
userAllotmentForm: FormGroup ;

allDivisionS: AllDivisionData[] = [];

constructor(
  private snackBar: MatSnackBar,
  private restClient: HttpclientService,
  private formBuilder:FormBuilder
) { }

ngOnInit(): void {
  this.userAllotmentForm = new FormGroup({
    mobile: new FormControl({value:'',disabled:!this.isEditable || this.isNavigation},[ Validators.required,Validators.maxLength(10)]),
    userId: new FormControl({value:'',disabled:!this.isEditable || this.isNavigation},[ Validators.required,]),
    divisionList: new FormControl([]),
    allocationDatatype: new FormControl(''),
    allocation: new FormControl(''),
    agency:new FormControl({value:'',disabled:true}),
    userName:new FormControl({value:'',disabled:true}),
  })

  this.userAllotmentForm.controls['mobile'].setErrors({"Invalid":true});
  this.userAllotmentForm.controls['userId'].setErrors({"Invalid":true});
  //this.fetchAllData();
}
ngOnChanges(){
  if(this.userAllotmentDetails==null || this.userAllotmentDetails==undefined){
    this.userAllotmentForm = new FormGroup({
      mobile: new FormControl({value:'',disabled:!this.isEditable || this.isNavigation},[ Validators.required,Validators.maxLength(10)]),
      userId: new FormControl({value:'',disabled:!this.isEditable || this.isNavigation},[ Validators.required,]),
      divisionList:  new FormArray([],[Validators.required]),
      allocationDatatype: new FormControl('',Validators.required),
      allocation: new FormControl('',Validators.required),
      agency:new FormControl({value:'',disabled:true}),
      userName:new FormControl({value:'',disabled:true}),
    });

    this.userAllotmentForm.controls['mobile'].setErrors({"Invalid":true});
    this.userAllotmentForm.controls['userId'].setErrors({"Invalid":true});
  } else{
    this.fieldSave=this.userAllotmentDetails.userId;

    this.userAllotmentForm = new FormGroup({
      mobile:new FormControl({value:this.userAllotmentDetails.mobile,disabled:!this.isEditable || this.isNavigation}, [Validators.required,]),
      userId: new FormControl({value:this.userAllotmentDetails.userId,disabled:!this.isEditable || this.isNavigation}, [Validators.required,]),
      divisionList: this.formBuilder.array(this.userAllotmentDetails.divisionList,Validators.required),
      allocationDatatype: new FormControl(this.userAllotmentDetails.allocationDatatype,Validators.required),
      allocation: new FormControl(this.userAllotmentDetails.allocation,Validators.required),
      agency:new FormControl({value:this.userAllotmentDetails.agency,disabled:true}) ,
      userName:new FormControl({value:this.userAllotmentDetails.userName,disabled:true}) ,
    });
  }
}

//for populating the data
fetchAllData(): void{
  this.spinner = true;
  var queryParams: any ={};
  queryParams['mobile'] = this.userAllotmentForm.getRawValue().mobile;
  queryParams['userId'] = this.userAllotmentForm.getRawValue().userId;
  queryParams['type'] = this.userAllotmentForm.getRawValue().allocationDatatype;

  this.restClient.getwithParam(environment.SEARCH_SUPERVISOR_DATA_FOR_POPULATE,queryParams)
  .subscribe({
    next: (result: APIResponse) => {
      var response = result.data as UserSearchResponse;

      // this.userAllotmentForm.controls['agency'].setValue(response.agencyName);
      this.allDivisionS=response.agencyAllotmentData;
      if(response.allotmentData.divisionList!=null){

        this.userAllotmentForm.controls['divisionList']=this.formBuilder.array(response.allotmentData.divisionList);
      }
        this.userAllotmentForm.controls['userId'].setValue(response.allotmentData.userId);
        this.userAllotmentForm.controls['userName'].setValue(response.allotmentData.userName);
        this.userAllotmentForm.controls['mobile'].setValue(response.allotmentData.mobile);
        this.userAllotmentForm.controls['agency'].setValue(response.allotmentData.agency);
        this.userAllotmentForm.controls['mobile'].disable();
        this.userAllotmentForm.controls['userId'].disable();
        this.userAllotmentForm.controls['mobile'].setErrors(null);
        this.userAllotmentForm.controls['userId'].setErrors(null);
        this.userAllotmentForm.controls['agency'].setErrors(null);

    },
    error: (err: any) => {
      console.log(err);
      this.userAllotmentForm.controls['mobile'].setErrors({"Invalid":true});
      this.userAllotmentForm.controls['userId'].setErrors({"Invalid":true});
      this.userAllotmentForm.controls['agency'].setErrors({"Invalid":true});
      this.spinner = false;

    let snackBarRef = this.snackBar.open('Mobile/User Id is Invalid', 'close', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration: 3000,
    });
    },
    complete: () => {
      this.spinner = false;
    },
  });
}

// fetchAgencyData():void{
//   this.spinner = true;
//   var queryParams: any ={};
//   queryParams['agency'] = this.userAllotmentForm.getRawValue().agency;
//   queryParams['type'] = this.userAllotmentForm.getRawValue().allocationDatatype;

//   this.restClient.getwithParam(environment.FETCH_AGENCY_DETAILS_FOR_ALLOTMENT_API,queryParams)
//   .subscribe({
//     next: (result: APIResponse) => {
//       this.allDivisionS = result.data as AllDivisionData[];
//     },
//     error: (err: any) => {
//       console.log(err);
//       this.spinner = false;

//     let snackBarRef = this.snackBar.open('Agency is Invalid', 'close', {
//       horizontalPosition: 'center',
//       verticalPosition: 'bottom',
//       duration: 3000,
//     });
//     },
//     complete: () => {
//       this.spinner = false;
//     },
//   });
// }

// divisionStore:any;
// fetchDivisions(): void{
//   this.spinner=true;
//   var queryParams: any={};
//   queryParams['agency'] = this.userAllotmentForm.getRawValue().agency;

//   this.restClient.getwithParam(environment.FETCH_DATA_TO_GET_DIVISIONS,queryParams)
//   .subscribe({
//     next:(result: APIResponse) => {
//       var resp = result.data as DivisionLookupAPIResp;
//       this.divisionStore=resp.divisionCode;
//       this.userAllotmentForm.controls['divisionCode'].setValue(resp.divisionCode)
//     },
//     error: (err: any) => {
//       console.log(err);
//       this.spinner = false;
//     },
//     complete: () => {
//       this.spinner = false;
//     },
//   });
// }


saveFieldUserAllotment(){
if(this.userAllotmentForm.getRawValue().divisionList.length==0){
  let snackBarRef = this.snackBar.open(
    'Please Allocate Atleast One Division','close',
    {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration: 3000,
    }
  );
}

if(!this.userAllotmentForm.valid){
  return;
}
this.spinner = true;
var queryParams: any = {};
let apiURL='';
apiURL=environment.UPDATE_SUUPERVISOR_ALLOTMENT_API;

//check request body is require or not
this.restClient.post(apiURL, this.userAllotmentForm.getRawValue()).subscribe({
  next: (result: APIResponse) => {
    let snackBarRef = this.snackBar.open(
      'Data Saved Successfully','close',
      {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 3000,
      }
    );
    this.dataSaved.emit(0);
  },
  error: (err: any) => {
    console.log(err);

    let snackBarRef = this.snackBar.open('Server Error', 'close', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration: 3000,
    });
    this.spinner = false;
  },
  complete: () => {
    console.log('Field User Allotment Saved');
    this.spinner = false;
  },
});
}


// fetchAllDivisionsinAgency(agencyCode:string):void{
//   var queryParam: any = {};
//   queryParam['agencyCode'] = agencyCode;
//   this.restClient
//     .getwithParam(environment.FETCH_AGENCY_DIVISION_COUNT_API, queryParam)
//     .subscribe({
//       next: (result: APIResponse) => {
//         this.allDivisionS = result.data as AllDivisionData[];
//       },
//       error: (err: any) => {
//         console.log(err);
//       },
//       complete: () => {
//         console.log('Discom list SuccessFully fetched..!');
//       },
//     });




// }


closeDetailsTab():void{

  this.dataSaved.emit(0);
}


checkedChange(event:MatCheckboxChange):void{
  if(event.checked){
    ( this.userAllotmentForm.controls['divisionList'] as FormArray).push(new FormControl(event.source.value));
  }else{
    ( this.userAllotmentForm.controls['divisionList'] as FormArray).removeAt( this.userAllotmentForm.controls['divisionList'].value.indexOf(event.source.value));
  }

}
}



export interface UserSearchResponse{

  allotmentData:FieldUserAllotmentDetails;
  agencyAllotmentData:AllDivisionData[];

}
