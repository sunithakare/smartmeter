import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { MatSnackBar } from '@angular/material/snack-bar';
import {
  HttpclientService,
  APIResponse,
} from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { FieldUserAllotmentDetails } from '../ci-field-user-allotment.component';
import { AllDivisionData } from '../../ci-agency-allotment-list/ci-agency-allotment-details/ci-agency-allotment-details.component';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatSelectChange } from '@angular/material/select';
import { AgencyListDetails } from '../../ci-agency-list/ci-agency-list.component';

@Component({
  selector: 'app-ci-field-user-allotment-details',
  templateUrl: './ci-field-user-allotment-details.component.html',
  styleUrls: ['./ci-field-user-allotment-details.component.css'],
})
export class CiFieldUserAllotmentDetailsComponent implements OnInit {
  @Input()
  isNavigation: boolean = false;
  @Input()
  isEditable: boolean = false;

  @Input()
  fieldUserAllotmentDetails: FieldUserAllotmentDetails;

  @Output() dataSaved: EventEmitter<number> = new EventEmitter();

  spinner: boolean = false;
  agencyStore: any;
  fieldUserStore: any;
  fieldSave: any;
  fieldUserAllotmentForm: FormGroup;

  allocationList: AllocationData[] = [];

  allDivisionS: AllDivisionData[] = [];

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.fieldUserAllotmentForm = new FormGroup({
      mobile: new FormControl(
        { value: '', disabled: !this.isEditable || this.isNavigation },
        [Validators.required, Validators.maxLength(10)]
      ),
      userId: new FormControl(
        { value: '', disabled: !this.isEditable || this.isNavigation },
        [Validators.required]
      ),
      divisionList: new FormArray([],[Validators.required]),
      allocationDatatype: new FormControl('',[Validators.required]),
      allocation: new FormControl('',[Validators.required]),
      agency: new FormControl({ value: '', disabled: true }),
      userName: new FormControl({ value: '', disabled: true }),
    });

    this.fieldUserAllotmentForm.controls['mobile'].setErrors({ Invalid: true });
    this.fieldUserAllotmentForm.controls['userId'].setErrors({ Invalid: true });
    this.fetchAllocationTypeList();

  }
  ngOnChanges() {
    if (
      this.fieldUserAllotmentDetails == null ||
      this.fieldUserAllotmentDetails == undefined
    ) {
      this.fieldUserAllotmentForm = new FormGroup({
        mobile: new FormControl(
          { value: '', disabled: !this.isEditable || this.isNavigation },
          [Validators.required, Validators.maxLength(10)]
        ),
        userId: new FormControl(
          { value: '', disabled: !this.isEditable || this.isNavigation },
          [Validators.required]
        ),
        divisionList: new FormArray([], [Validators.required]),
        allocationDatatype: new FormControl('',[Validators.required]),
        allocation: new FormControl('',[Validators.required]),
        agency: new FormControl({ value: '', disabled: true }),
        userName: new FormControl({ value: '', disabled: true }),
      });

      this.fieldUserAllotmentForm.controls['mobile'].setErrors({
        Invalid: true,
      });
      this.fieldUserAllotmentForm.controls['userId'].setErrors({
        Invalid: true,
      });
    } else {
      this.fieldSave = this.fieldUserAllotmentDetails.userId;

      this.fieldUserAllotmentForm = new FormGroup({
        mobile: new FormControl(
          {
            value: this.fieldUserAllotmentDetails.mobile,
            disabled: !this.isEditable || this.isNavigation,
          },
          [Validators.required]
        ),
        userId: new FormControl(
          {
            value: this.fieldUserAllotmentDetails.userId,
            disabled: !this.isEditable || this.isNavigation,
          },
          [Validators.required]
        ),
        divisionList: this.formBuilder.array(
          this.fieldUserAllotmentDetails.divisionList,[Validators.required]
        ),
        allocationDatatype: new FormControl(
          this.fieldUserAllotmentDetails.allocationDatatype,[Validators.required]
        ),
        allocation: new FormControl(this.fieldUserAllotmentDetails.allocation,[Validators.required]),
        agency: new FormControl({
          value: this.fieldUserAllotmentDetails.agency,
          disabled: true,
        }),
        userName: new FormControl({
          value: this.fieldUserAllotmentDetails.userName,
          disabled: true,
        }),
      });
    }
  }

  fetchAllocationTypeList(): void {
    this.spinner = true;
    var queryParams: any = {};

    this.restClient
      .getwithParam(environment.FETCH_SUPERVISOR_ALLOTMENT_DETAILS_FOR_FIELD_USER_API, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          this.allocationList = result.data as AllocationData[];
        },
        error: (err: any) => {
          console.log(err);
          this.spinner = false;
        },
        complete: () => {
          this.spinner = false;
        },
      });
  }

  //for populating the data
  fetchAllData(): void {
    this.spinner = true;
    var queryParams: any = {};
    queryParams['mobile'] = this.fieldUserAllotmentForm.getRawValue().mobile;
    queryParams['userId'] = this.fieldUserAllotmentForm.getRawValue().userId;
    queryParams['type'] =
      this.fieldUserAllotmentForm.getRawValue().allocationDatatype;

    this.restClient
      .getwithParam(environment.FETCH_ALL_DATA_FOR_POPULATE, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          var response = result.data as FieldUserAllotmentDetails;
          // this.fieldUserAllotmentForm.controls['agency'].setValue(response.agencyName);
          if(response.divisionList!=null){
            this.fieldUserAllotmentForm.controls['divisionList'] =
            this.formBuilder.array(response.divisionList);

          }

          this.fieldUserAllotmentForm.controls['agency'].setValue(
            response.agency
          );
          this.fieldUserAllotmentForm.controls['userId'].setValue(
            response.userId
          );
          this.fieldUserAllotmentForm.controls['userName'].setValue(
            response.userName
          );
          this.fieldUserAllotmentForm.controls['mobile'].setValue(
            response.mobile
          );
          this.fieldUserAllotmentForm.controls['mobile'].disable();
          this.fieldUserAllotmentForm.controls['userId'].disable();
          this.fieldUserAllotmentForm.controls['mobile'].setErrors(null);
          this.fieldUserAllotmentForm.controls['userId'].setErrors(null);
        },
        error: (err: any) => {
          console.log(err);
          this.fieldUserAllotmentForm.controls['mobile'].setErrors({
            Invalid: true,
          });
          this.fieldUserAllotmentForm.controls['userId'].setErrors({
            Invalid: true,
          });
          this.spinner = false;

          let snackBarRef = this.snackBar.open(
            'Mobile/User Id is Invalid',
            'close',
            {
              horizontalPosition: 'center',
              verticalPosition: 'bottom',
              duration: 3000,
            }
          );
        },
        complete: () => {
          this.spinner = false;
        },
      });
  }
  // divisionStore:any;
  // fetchDivisions(): void{
  //   this.spinner=true;
  //   var queryParams: any={};
  //   queryParams['agency'] = this.fieldUserAllotmentForm.getRawValue().agency;

  //   this.restClient.getwithParam(environment.FETCH_DATA_TO_GET_DIVISIONS,queryParams)
  //   .subscribe({
  //     next:(result: APIResponse) => {
  //       var resp = result.data as DivisionLookupAPIResp;
  //       this.divisionStore=resp.divisionCode;
  //       this.fieldUserAllotmentForm.controls['divisionCode'].setValue(resp.divisionCode)
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

  saveFieldUserAllotment() {

    if(this.fieldUserAllotmentForm.getRawValue().divisionList.length==0){
    let snackBarRef = this.snackBar.open(
      'Please Allocate Atleast One Division','close',
      {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 3000,
      }
    );
  }

    if (!this.fieldUserAllotmentForm.valid) {
      return;
    }
    this.spinner = true;
    var queryParams: any = {};
    let apiURL = '';
    apiURL = environment.UPDATE_FIELD_USER_ALLOTMENT_API;

    //check request body is require or not
    this.restClient
      .post(apiURL, this.fieldUserAllotmentForm.getRawValue())
      .subscribe({
        next: (result: APIResponse) => {
          let snackBarRef = this.snackBar.open(
            'Data Saved Successfully',
            'close',
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

  // fetchAllDivisionsinAgency(agencyCode: string): void {
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

  closeDetailsTab(): void {
    this.dataSaved.emit(0);
  }

  checkedChange(event: MatCheckboxChange): void {
    if (event.checked) {
      (this.fieldUserAllotmentForm.controls['divisionList'] as FormArray).push(
        new FormControl(event.source.value)
      );
    } else {
      (
        this.fieldUserAllotmentForm.controls['divisionList'] as FormArray
      ).removeAt(
        this.fieldUserAllotmentForm.controls['divisionList'].value.indexOf(
          event.source.value
        )
      );
    }
  }
  dataTypeChanged(event:MatSelectChange):void{
    this.allDivisionS=this.allocationList[this.allocationList.findIndex(i => i.type == event.value)].divisionData;


  }

}

export interface AllocationData{
  type:string;
  divisionData:AllDivisionData[]
}
