
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import {
  APIResponse,
  HttpclientService,
} from 'src/app/services/httpclient.service';
import { FileUploadResponse } from 'src/app/shared/interface/FileUploadResponse';
import { environment } from 'src/environments/environment';
import { saveAs } from 'file-saver';
import { UserDetail } from '../../ci-field-user-list/ci-field-user-list.component';
import { AgencyListDetails } from '../../ci-agency-list/ci-agency-list.component';

@Component({
  selector: 'app-ci-supervisor-details',
  templateUrl: './ci-supervisor-details.component.html',
  styleUrls: ['./ci-supervisor-details.component.css']
})
export class CiSupervisorDetailsComponent implements OnInit {


  userCreationForm: FormGroup;

  spinner: boolean = false;
  @Input()
  isNavigation: boolean = false;
  @Input()
  isEditable: boolean = false;
  @Input()
  fielduserdetail: UserDetail;
  file: File;

  employeeForm: FormGroup;
  dataSource = new MatTableDataSource<any>();
  // uploadForm:FormGroup;

  userCodeSave: any;
  agencyName:string='';

  @Output()
  dataSaved: EventEmitter<number> = new EventEmitter();

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService
  ) {}

  ngOnInit(): void {
    this.userCreationForm = new FormGroup({
      agencyCode: new FormControl({value:'', disabled: !this.isEditable|| this.isNavigation },Validators.required),
      mobile: new FormControl({value:'', disabled: !this.isEditable },[Validators.pattern('[5-9]\\d{9}'),Validators.required]),
      userId: new FormControl({value:'', disabled: !this.isEditable || this.isNavigation},Validators.required),
      status: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      email: new FormControl({value:'', disabled: !this.isEditable },[Validators.required,Validators.email]),
      userFirstName: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      userLastName: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      idProof: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      password: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      fileId: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
    });
    this.getUserAgency();
  }
  ngOnChanges() {
    if (this.fielduserdetail == undefined && this.fielduserdetail == null) {
      this.userCreationForm = new FormGroup({
        agencyCode: new FormControl({value:'', disabled: !this.isEditable || this.isNavigation },Validators.required),
        mobile: new FormControl({value:'', disabled: !this.isEditable },[Validators.pattern('[5-9]\\d{9}'),Validators.required]),
        userId: new FormControl({value:'', disabled: !this.isEditable || this.isNavigation},Validators.required),
        status: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
        email: new FormControl({value:'', disabled: !this.isEditable },[Validators.required,Validators.email]),
        userFirstName: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
        userLastName: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
        password: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
        idProof: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
        fileId: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      });
      this.agencyName='';
      this.getUserAgency();
    } else {
      this.userCodeSave = this.fielduserdetail.userId;
      this.userCreationForm = new FormGroup({
        agencyCode: new FormControl(
          {
            value: this.fielduserdetail.agencyCode,
            disabled: !this.isEditable || this.isNavigation,
          },
          [Validators.required]
        ),
        mobile: new FormControl({value:this.fielduserdetail.mobile, disabled: !this.isEditable },[Validators.pattern('[5-9]\\d{9}'),Validators.required]),
        userId: new FormControl({value:this.fielduserdetail.userId, disabled: !this.isEditable || this.isNavigation },[Validators.required]),
        status: new FormControl({value:this.fielduserdetail.status, disabled: !this.isEditable },Validators.required),
        email: new FormControl({value:this.fielduserdetail.email, disabled: !this.isEditable },[Validators.required,Validators.email]),
        userFirstName: new FormControl({value:this.fielduserdetail.userFirstName, disabled: !this.isEditable },Validators.required),
        userLastName: new FormControl({value:this.fielduserdetail.userLastName, disabled: !this.isEditable },Validators.required),
        idProof: new FormControl({value:this.fielduserdetail.idProof, disabled: !this.isEditable },Validators.required),
        password: new FormControl({value:this.fielduserdetail.idProof, disabled: !this.isEditable }),
        fileId: new FormControl({value:this.fielduserdetail.fileId, disabled: !this.isEditable },Validators.required),
      });

      this.agencyName='';

      if(this.fielduserdetail.agencyCode!=null &&this.fielduserdetail.agencyCode!=''){
           this.validateAgencyCode();
    }
    }

    this.userCreationForm.controls['fileId'].setErrors({"Invalid File":true});
  }

  // selectFile(event: any): void {
  //   if (event.target.files && event.target.files[0]) {
  //     this.file = event.target.files[0];
  //   } else {
  //   }
  // }

  removeDocument(index: number): void {
    (<FormArray>this.employeeForm.get('documentList')).removeAt(index);
    this.dataSource.data = this.dataSource.data;
  }

  uploadFile(event: any) {
    this.spinner = true;
    var file: File = event.target.files[0];
    var formData = new FormData();
    formData.append('uploadFile', file);
    this.restClient
      .uploadFile(environment.FILE_UPLOAD_API, formData)
      .subscribe({
        next: (result: APIResponse) => {
          var fileData = result.data as FileUploadResponse;

          this.userCreationForm.get('fileId')?.setValue(fileData.fileId);
          this.userCreationForm.controls['fileId'].setErrors(null);

          // element.get('documnetId')?.setValue(fileData.fileId);
          // let snackBarRef = this.snackBar.open(
          //   'File Upload SuccessFully',
          //   'close',
          //   {
          //     horizontalPosition: 'center',
          //     verticalPosition: 'bottom',
          //     duration: 3000,
          //   }
          // );
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

          this.spinner = false;
        },
      });
  }

  downloadDocument(): void {
    this.spinner = true;

    var queryParams: any = {};
    queryParams['docId'] = this.userCreationForm.get('fileId')?.value;

    this.restClient
      .downloadusingGet(environment.FILE_DOWNLOAD_API, queryParams)
      .subscribe({
        next: (result: any) => {
          saveAs(result, 'Id Proof.pdf');
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
  saveFieldUserCreation() {
    if (!this.userCreationForm.valid) {
      return;
    }
    this.spinner = true;

    var queryParams: any = {};
    let apiURL = '';
    if (this.isNavigation) {
      apiURL =
        environment.UPDATE_CI_SUPERVISOR_USER_CREATION + '/' + this.userCodeSave;
    } else {
      apiURL = environment.SAVE_SUPERVISOR_USER_DATA;
    }
    this.restClient.post(apiURL, this.userCreationForm.getRawValue()).subscribe({
      next: (result: APIResponse) => {
        let snackBarRef = this.snackBar.open('Data Saved', 'close', {
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          duration: 3000,
        });
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
        console.log('field saved');
        this.spinner = false;
      },
    });
  }

  validateAgencyCode():void {
    this.spinner = true;

    var queryParams: any ={};
   //  queryParams['agencyType']=row.agencyType;
    queryParams['agencyCode']=this.userCreationForm.controls['agencyCode'].value;

    this.restClient.getwithParam(environment.VALIDATE_AGENCY_API,queryParams)
    .subscribe({
      next:(results:APIResponse) =>{
        this.userCreationForm.controls['agencyCode'].setErrors(null);

        var content: AgencyListDetails = results.data as AgencyListDetails;
        this.agencyName=content.agencyName;

      },
      error:(err:any) => {

   this.userCreationForm.controls['agencyCode'].setErrors({"Invalid Agency":true});
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

 getUserAgency() {
  var queryParams: any = {};
  this.restClient
    .getwithParam(environment.GET_USER_AGENCY_API, queryParams)
    .subscribe({
      next: (result: APIResponse) => {
        var content: AgencyListDetails = result.data as AgencyListDetails;
          this.userCreationForm.controls['agencyCode'].setValue(content.agencyCode);
          this.userCreationForm.controls['agencyCode'].disable();
          this.agencyName=content.agencyName;

      },
      error: (err: any) => {
        console.log(err);
        // let snackBarRef = this.snackBar.open('Server Error', 'close', {
        //   horizontalPosition: 'center',
        //   verticalPosition: 'bottom',
        //   duration: 3000,
        // });
      },
      complete: () => {

      },
    });
}




  closeDetailsTab():void{

    this.dataSaved.emit(0);
  }
}
