import { query } from '@angular/animations';
import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
} from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatSelectChange } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SystemAccess } from 'src/app/ltapp/admin/rolecreation/role-creation.component';
import {
  APIResponse,
  ConfigCode,
  HttpclientService,
} from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';
import { AgencyDetails } from '../ci-agency-allotment-list.component';

@Component({
  selector: 'app-ci-agency-allotment-details',
  templateUrl: './ci-agency-allotment-details.component.html',
  styleUrls: ['./ci-agency-allotment-details.component.css'],
})
export class CiAgencyAllotmentDetailsComponent implements OnInit, OnChanges {


  @Input()
  isEditable: boolean = false;

  @Input()
  isNavigation: boolean = false;

  @Input()
  agencyDetails: AgencyDetails | undefined;

  @Output() dataSaved: EventEmitter<number> = new EventEmitter();

  stateList: EnhStateList[] = [];
  allDiscomList: EnhDiscomList[] = [];
  allCityList:EnhCityList[]=[];
  divisionList: EnhDivisionList[] = [];

  allDivisionS: AllDivisionData[] = [];

  isSubDivisionAllocation:boolean=false;
  pageSize = 15;
  currentPage = 0;


  allocationDataStore: any[] = ['CI', 'MI', 'ENH', 'O&M'];

  // ciAgencyForm:FormGroup;
  spinner: boolean = false;

  asignedPermission: SystemAccess[] = [];

  ciAgencyForm!: FormGroup;

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private formBuilder:FormBuilder
  ) {}

  ngOnInit(): void {
    this.ciAgencyForm = new FormGroup({
      state: new FormControl('',[Validators.required]),
      discom: new FormControl('',[Validators.required]),
      city: new FormControl('',[Validators.required]),
      division: new FormArray([],[Validators.required]),
      divisionFilter: new FormControl( { value: '', disabled: !this.isEditable } ),
      allocationData: new FormControl('',[Validators.required]),
      agencyCode: new FormControl('',[Validators.required]),
      allocation: new FormControl('',[Validators.required]),
    });
    this.fetchStateList();
    // this.fetchCityList();
    // this.fetchDiscomList();
  }

  ngOnChanges() {
    if (this.agencyDetails == undefined || this.agencyDetails == null) {
      this.ciAgencyForm = new FormGroup({
        agencyCode: new FormControl({ value: '', disabled: !this.isEditable  },[Validators.required]),
        state: new FormControl( { value: '', disabled: !this.isEditable  }, [Validators.required] ),
        discom: new FormControl({ value: '', disabled: !this.isEditable  }, Validators.required),
        city: new FormControl({ value: '', disabled: !this.isEditable }, [ Validators.required, ]),
        division: new FormArray([],[Validators.required]),
        divisionFilter: new FormControl( { value: '', disabled: !this.isEditable } ),
        allocationData: new FormControl({ value: '', disabled: !this.isEditable  }, Validators.required),
        allocation: new FormControl({ value: '', disabled: !this.isEditable  }, Validators.required),
      });
    } else {

      if ( this.agencyDetails.state != '' && this.agencyDetails.state != null && this.agencyDetails.state != undefined ) {
        this.fetchDiscomFromState(this.agencyDetails.state);
      }


      if ( this.agencyDetails.discom != '' && this.agencyDetails.discom != null && this.agencyDetails.discom != undefined ) {
        this.fetchcityFromDiscom(this.agencyDetails.discom);
      }

      if ( this.agencyDetails.city != '' && this.agencyDetails.city != null && this.agencyDetails.city != undefined ) {
        this.fetchDivisionIncity(this.agencyDetails.city,this.agencyDetails.state);
      }

      this.ciAgencyForm = new FormGroup({
        state: new FormControl( { value: this.agencyDetails.state, disabled: !this.isEditable  }, [Validators.required] ),
        discom: new FormControl( { value: this.agencyDetails.discom, disabled: !this.isEditable }, [Validators.required] ),
        city: new FormControl( { value: this.agencyDetails.city, disabled: !this.isEditable  }, [Validators.required] ),
        divisionFilter: new FormControl( { value: '', disabled: !this.isEditable } ),
        agencyCode: new FormControl( { value: this.agencyDetails.agencyCode, disabled: !this.isEditable ||this.isNavigation  }, [Validators.required] ),
        division: this.formBuilder.array(this.agencyDetails.division),
        allocationData: new FormControl( { value: this.agencyDetails.allocationData, disabled: !this.isEditable||this.isNavigation   },[Validators.required]  ),
        allocation: new FormControl( { value: this.agencyDetails.allocation, disabled: !this.isEditable  },[Validators.required]  ),
      });
    }
  }

  fetchDiscomInStateforChange(event:MatSelectChange):void{
    this.fetchDiscomFromState(event.value);
  }
  fetchDiscomFromState(state: any) {
    this.spinner = true;
    var queryParams: any = {};
    queryParams['state'] = state;
    this.restClient
      .getwithParam(
        environment.FETCH_ALL_DISCOM_IN_STATE_ENH,
        queryParams
      )
      .subscribe({
        next: (result: APIResponse) => {
          this.allDiscomList = result.data as EnhDiscomList[];
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {
          console.log('state 2 fetched..!');

          this.spinner = false;
        },
      });
  }

  fetchCityInDiscomforChange(event:MatSelectChange):void{
    this.fetchcityFromDiscom(event.value);
  }
  fetchcityFromDiscom(discom: any) {
    this.spinner = true;
    var queryParams: any = {};
    queryParams['discom'] = discom;
    this.restClient
      .getwithParam(
        environment.FETCH_ALL_CITY_IN_DISCOM_IN_ENH,
        queryParams
      )
      .subscribe({
        next: (result: APIResponse) => {
          this.allCityList = result.data as EnhCityList[];
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {

          this.spinner = false;
        },
      });
  }

  fetchDivisionInCityforChange(event:MatSelectChange):void{
    this.fetchDivisionIncity(event.value,this.ciAgencyForm.controls['state'].value);
  }
  fetchDivisionIncity(city:string,state:string):void{
    var queryParam: any = {};
    queryParam['state'] = state;
    queryParam['city'] = city;
    this.restClient
      .getwithParam(environment.FETCH_ALL_DIVISION_IN_City_IN_ENH, queryParam)
      .subscribe({
        next: (result: APIResponse) => {
          var resp= result.data as EnhDivisionAllotmentVO;
          this.divisionList = resp.divisionList;
          this.isSubDivisionAllocation = resp.issubDivisionAllocation;

          if(!this.isSubDivisionAllocation){

            this.fetchDataCount(this.ciAgencyForm.controls['state'].value,this.ciAgencyForm.controls['discom'].value,this.ciAgencyForm.controls['city'].value,null);

          }
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {
          console.log('Discom list SuccessFully fetched..!');
        },
      });

  }



  fetchStateList() {
    this.spinner = true;

    var queryParams: any = {};
    queryParams['type'] = 'STATE';

    this.restClient
      .getwithParam(environment.FETCH_ALL_STATE_IN_ENH, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          this.stateList = result.data as EnhStateList[];
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {
          console.log('State List Fetch Complete ');

          this.spinner = false;
        },
      });
  }

  fetchSubDivisionListChange(event:MatSelectChange):void{

    this.fetchDataCount(this.ciAgencyForm.controls['state'].value,this.ciAgencyForm.controls['discom'].value,this.ciAgencyForm.controls['city'].value,event.value);
  }

  fetchDataCount(state:string,discom:string,city:string,division:string|null):void{
    var queryParam: any = {};
    queryParam['state'] = state;
    queryParam['discom'] = discom;
    queryParam['city'] = city;
    if(division!=null){

      queryParam['division'] = division;
    }
    this.restClient
      .getwithParam(environment.FETCH_MASTER_DIVISION_COUNT_API, queryParam)
      .subscribe({
        next: (result: APIResponse) => {
          this.allDivisionS = result.data as AllDivisionData[];
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {
          console.log('Discom list SuccessFully fetched..!');
        },
      });

  }

  agencyDiscomPermission(event: MatSelectChange) {
    // let newName= event.value;
    // this.discomValue=newName;
    // this.spinner = true;
    // var queryParams: any = {};
    // queryParams['discom'] = event.value;
    // this.restClient
    //   .publicgetwithParam(environment.CIA_DISCOMDATA_API, queryParams)
    //   .subscribe({
    //     next: (result: APIResponse) => {
    //       this.allDiv = result.data as DivStore[];
    //     },
    //     error: (err: any) => {
    //       console.log(err);
    //       this.spinner = false;
    //     },
    //     complete: () => {
    //       this.spinner = false;
    //     },
    //   });
  }


  saveCiAgency() {
    console.log(this.ciAgencyForm.valid);
    Object.keys(this.ciAgencyForm.controls).forEach(key => {
      const controlErrors: ValidationErrors = this.ciAgencyForm.get(key)!.errors!;
      if (controlErrors != null) {
        Object.keys(controlErrors).forEach(keyError => {
         console.log('Key control: ' + key + ', keyError: ' + keyError + ', err value: ', controlErrors[keyError]);
        });
      }
    });
    if (!this.ciAgencyForm.valid) {
      return;
    }

    this.spinner = true;
    let requestBody: AgencySaveData = {
      agencyCode: this.ciAgencyForm.getRawValue().agencyCode,
      state: this.ciAgencyForm.getRawValue().state,
      discom: this.ciAgencyForm.getRawValue().discom,
      city: this.ciAgencyForm.getRawValue().city,
      division: this.ciAgencyForm.getRawValue().division,
      allocation: this.ciAgencyForm.getRawValue().allocation,
      allocationData: this.ciAgencyForm.getRawValue().allocationData,
      asignedPermission: this.asignedPermission,
    };

    var queryParam: any = {};
    let apiURL = '';
    if (this.isNavigation) {
      apiURL = environment.CIA_UPDATE;
    } else {
      apiURL = environment.CIA_SAVEDATA;
    }
    this.restClient.post(apiURL, requestBody).subscribe({
      next: (res: APIResponse) => {
        let snackBarRef = this.snackBar.open(
          'Data Saved Successfully',
          'close',
          {
            horizontalPosition: 'center',
            verticalPosition: 'top',
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
        console.log('Ci allotment saved successfully');

        this.spinner = false;
      },
    });
  }

  validateAgencyCode():void {
    this.spinner = true;

    var queryParams: any ={};
   //  queryParams['agencyType']=row.agencyType;
    queryParams['agencyCode']=this.ciAgencyForm.controls['agencyCode'].value;
    queryParams['agencytype']=this.ciAgencyForm.controls['allocationData'].value;

    this.restClient.getwithParam(environment.VALIDATE_AGENCY_API,queryParams)
    .subscribe({
      next:(results:APIResponse) =>{
        this.ciAgencyForm.controls['agencyCode'].setErrors(null);
      },
      error:(err:any) => {

   this.ciAgencyForm.controls['agencyCode'].setErrors({"Invalid Agency":true});
        console.log(err);
        let snackBarRef = this.snackBar.open('Invalid Agency Code' ,'close',{
          horizontalPosition:'center',
         verticalPosition:'bottom',
         duration :3000,
               });
        this.spinner = false;
      },
      complete: () => {
        this.spinner =false;
      },
     });
 }

  closeDetailsTab():void{

    this.dataSaved.emit(0);
  }



  checkedChange(event:MatCheckboxChange):void{
    if(event.checked){
      ( this.ciAgencyForm.controls['division'] as FormArray).push(new FormControl(event.source.value));
    }else{
      ( this.ciAgencyForm.controls['division'] as FormArray).removeAt( this.ciAgencyForm.controls['division'].value.indexOf(event.source.value));
    }

  }
}
export interface AllAllocationData {
  ci_agency: string;
  mi_agency: string;
}

export interface AllDivisionData {
  divCode: string;
  count: string;
}

export interface AgencySaveData {
  agencyCode: string;
  state: string;
  discom: string;
  city: string;
  division: string[];
  allocation: string;
  allocationData: string;
  asignedPermission: SystemAccess[];
}

export interface EnhStateList{
  state:string;
  stateName:string;
}

export interface EnhDiscomList{
  discom:string;
  discomName:string;
}

export interface EnhCityList{
  city:string;
  cityName:string;
}
export interface EnhDivisionList{
  division:string;
  divisionName:string;
}

export interface EnhDivisionAllotmentVO{
  divisionList:EnhDivisionList[];
  issubDivisionAllocation:boolean
}
