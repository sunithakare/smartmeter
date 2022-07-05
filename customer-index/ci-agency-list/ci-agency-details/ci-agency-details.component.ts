import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import {
  APIResponse,
  HttpclientService,
} from 'src/app/services/httpclient.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { AgencyDetailsResponse } from '../ci-agency-list.component';
@Component({
  selector: 'app-ci-agency-details',
  templateUrl: './ci-agency-details.component.html',
  styleUrls: ['./ci-agency-details.component.css'],
})
export class CiAgencyDetailsComponent implements OnInit {
  @Input()
  isNavigation: boolean = false;
  @Input()
  isEditable: boolean = false;

  @Input()
  agencyDetails: AgencyDetailsResponse;

  spinner: any;
  // selectedIndex=0;
  @Output()
  dataSaved: EventEmitter<number> = new EventEmitter();

  agencyCreationForm: FormGroup;

  permissionFilterStr: FormControl = new FormControl('');
  assignedPermissionFilterStr: FormControl = new FormControl('');

  constructor(
    public restClient: HttpclientService,
    public snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.agencyCreationForm = new FormGroup({
      agencyType: new FormControl([], [Validators.required]),
      agencyName: new FormControl('', [Validators.required]),
      externalId: new FormControl('', [Validators.required]),
      agencyCode: new FormControl({value:'', disabled:true}),
      agencyManager: new FormControl('', [Validators.required]),
      landmark: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      mobile: new FormControl('', [
        Validators.required,
        Validators.pattern('[5-9]\\d{9}'),
      ]),
      email: new FormControl('', [Validators.required,Validators.email]),
      status: new FormControl({ value: false }),
    });
  }

  ngOnChanges() {
    if (this.agencyDetails == null && this.agencyDetails == undefined) {
      this.agencyCreationForm = new FormGroup({
        agencyType: new FormControl(
          { value: [], disabled: !this.isEditable },
          [Validators.required]
        ),
        agencyName: new FormControl(
          { value: '', disabled: !this.isEditable },
          [Validators.required]
        ),
        externalId: new FormControl(
          { value: '', disabled: !this.isEditable },
          [Validators.required]
        ),
        agencyCode: new FormControl(
          { value: '', disabled: !this.isEditable },
          [Validators.required]
        ),
        password: new FormControl(
          '',
          [Validators.required]
        ),
        agencyManager: new FormControl(),
        landmark: new FormControl(
          { value: '', disabled: !this.isEditable },
          [Validators.required]
        ),
        mobile: new FormControl(
          { value: '', disabled: !this.isEditable },
          [Validators.required, Validators.pattern('[5-9]\\d{9}')]
        ),
        email: new FormControl(
          { value: '', disabled: this.isEditable },
          [Validators.required,Validators.email]
        ),
        status: new FormControl({ value: false , disabled: !this.isEditable }),
      });
    } else {
      this.agencyCreationForm = new FormGroup({
        agencyType: new FormControl({value:this.agencyDetails.agencyType, disabled: !this.isEditable }, [Validators.required]),
        agencyName: new FormControl({value:this.agencyDetails.agencyName, disabled: !this.isEditable }, [Validators.required]),
        externalId: new FormControl({value:this.agencyDetails.externalId, disabled: !this.isEditable }, [Validators.required]),
        agencyCode: new FormControl({
          value: this.agencyDetails.agencyCode,
          disabled: true,
        }),
        agencyManager: new FormControl({value:this.agencyDetails.agencyManager, disabled: !this.isEditable }, [Validators.required]),
        password: new FormControl(''),
        landmark: new FormControl({value:this.agencyDetails.landmark, disabled: !this.isEditable }, [Validators.required]),
        mobile: new FormControl({value:this.agencyDetails.mobile, disabled: !this.isEditable }, [Validators.required, Validators.pattern('[5-9]\\d{9}')]),
        email: new FormControl({value:this.agencyDetails.email, disabled: !this.isEditable }, [Validators.required,Validators.email]),
        status: new FormControl({value:this.agencyDetails.status, disabled: !this.isEditable }, [Validators.required]),
      });
    }


this.agencyCreationForm.controls['agencyName'].valueChanges.subscribe(value => {
  this.agencyCreationForm.controls['agencyName'].patchValue(this.agencyCreationForm.controls['agencyName'].value.toUpperCase(), {emitEvent: false});
});

  }

  // fetchdataforlookup(): void {
  //   this.spinner = true;
  //   var queryParams: any = {};

  //   queryParams['agencyName'] =
  //     this.agencyCreationForm.getRawValue().agencyName;
  //   queryParams['agencyCode'] =
  //     this.agencyCreationForm.getRawValue().agencyCode;
  //   this.restClient
  //     .getwithParam('/AgencyCreation/lookupdata', queryParams)
  //     .subscribe({
  //       next: (result: APIResponse) => {
  //         var response = result.data as AgencynameAndAgencyCodeLookupAPIResp;
  //         this.agencyCreationForm.controls['agencyType'].setValue(
  //           response.agencyType
  //         );
  //         this.agencyCreationForm.controls['mobile'].setValue(response.mobile);
  //         this.agencyCreationForm.controls['landmark'].setValue(
  //           response.landmark
  //         );
  //       },
  //       error: (err: any) => {
  //         console.log(err);
  //         this.spinner = false;
  //       },
  //       complete: () => {
  //         this.spinner = false;
  //       },
  //     });
  // }

  saveAgencyCreationData() {
    if (!this.agencyCreationForm.valid) {
      return;
    }
    this.spinner = true;

    var queryParams: any = {};
    let apiURL = '';
    if (this.isNavigation) {
      apiURL = environment.UPDATE_AGENCY_CREATION_API;
    } else {
      apiURL = environment.SAVE_AGENCY_CREATION_API;
    }

    this.restClient
      .post(apiURL, this.agencyCreationForm.getRawValue())
      .subscribe({
        next: (result: APIResponse) => {
          let snackBarRef = this.snackBar.open(
            'Data saved successfully',
            'close',

            {
              horizontalPosition: 'center',
              verticalPosition: 'bottom',
              duration: 4000,
            }
          );

          this.dataSaved.emit(0);
        },
        error: (err: any) => {
          console.log(err);

          let snackBarRef = this.snackBar.open('server error', 'close', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 4000,
          });
          this.spinner = false;
        },
        complete: () => {
          console.log('Data Saved');
          this.spinner = false;
        },
      });
  }

  closeDetailsTab():void{

    this.dataSaved.emit(0);
  }



}

export interface AgencynameAndAgencyCodeLookupAPIResp {
  agencyType: [];
  agencyCode: string;
  agencyName: string;
  agencyManager: string;
  email: string;
  mobile: string;
  landmark: string;
  status: string;
}
