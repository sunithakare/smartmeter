
import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';
import { CIDataForEdit } from '../ci-approval-list.component';
import { CiMeterImageDialogComponent } from '../ci-meter-image-dialog/ci-meter-image-dialog.component';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-ci-approval-details',
  templateUrl: './ci-approval-details.component.html',
  styleUrls: ['./ci-approval-details.component.css']
})
export class CiApprovalDetailsComponent implements OnInit {


@Input()
ciDataForEdit: CIDataForEdit;
@Input()
isEditable:boolean;

spinner:boolean=false;

@Output() dataSaved: EventEmitter<number> = new EventEmitter();



  constructor(private snackBar: MatSnackBar, private restClient: HttpclientService,
    public dialog: MatDialog,) { }

  adminApprovalForm:FormGroup;

  ngOnInit(): void {
    this.adminApprovalForm=new FormGroup({
      consumerNo:new FormControl({value:'',disabled:true}, [ Validators.required, ]),
      name:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      mobile:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      address:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      discom:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      division:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      subDivision:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      subStation:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      feeder:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      dtr:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      pole:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      metersOnPole:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      longitudeLatitude:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      networkOperator:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      signalStrength:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      connectionType:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      meterNo:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      meterType:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      meterMake:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      meterReading:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      meterBoxStatus:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      meterSealStatus:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      agencyVerified:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      verifiedBy:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      verifiedDate:new FormControl({value:new Date(),disabled:!this.isEditable }, [ Validators.required, ] ),
      remarks:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
    });
  }

  ngOnChanges(){

    if(this.ciDataForEdit==null || this.ciDataForEdit==undefined){
      this.adminApprovalForm=new FormGroup({
        consumerNo:new FormControl({value:'',disabled:true}, [ Validators.required, ]),
        name:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        mobile:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        address:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        discom:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        division:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        subDivision:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        subStation:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        feeder:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        dtr:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        pole:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        metersOnPole:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        longitudeLatitude:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        networkOperator:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        signalStrength:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        connectionType:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        meterNo:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        meterType:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        meterMake:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        meterReading:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        meterBoxStatus:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        meterSealStatus:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        agencyVerified:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        verifiedBy:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
        verifiedDate:new FormControl({value:new Date(),disabled:!this.isEditable }, [ Validators.required, ] ),
        remarks:new FormControl({value:'',disabled:!this.isEditable }, [ Validators.required, ]),
      });
    }else{
      this.adminApprovalForm=new FormGroup({
        consumerNo:new FormControl({value:this.ciDataForEdit.consumerNo,disabled:true}, [ Validators.required, ]),
        name:new FormControl({value:this.ciDataForEdit.name,disabled:!this.isEditable }, [ Validators.required, ]),
        mobile:new FormControl({value:this.ciDataForEdit.mobile,disabled:!this.isEditable }, [ Validators.required, ]),
        address:new FormControl({value:this.ciDataForEdit.address,disabled:!this.isEditable }, [ Validators.required, ]),
        discom:new FormControl({value:this.ciDataForEdit.discom,disabled:true }, [ Validators.required, ]),
        division:new FormControl({value:this.ciDataForEdit.division,disabled:true }, [ Validators.required, ]),
        subDivision:new FormControl({value:this.ciDataForEdit.subDivision,disabled:!this.isEditable }, [ Validators.required, ]),
        subStation:new FormControl({value:this.ciDataForEdit.subStation,disabled:!this.isEditable }, [ Validators.required, ]),
        feeder:new FormControl({value:this.ciDataForEdit.feeder,disabled:!this.isEditable }, [ Validators.required, ]),
        dtr:new FormControl({value:this.ciDataForEdit.dtr,disabled:!this.isEditable }, [ Validators.required, ]),
        pole:new FormControl({value:this.ciDataForEdit.pole,disabled:!this.isEditable }, [ Validators.required, ]),
        metersOnPole:new FormControl({value:this.ciDataForEdit.metersOnPole,disabled:!this.isEditable }, [ Validators.required, ]),
        longitudeLatitude:new FormControl({value:this.ciDataForEdit.longitudeLatitude,disabled:true }, [ Validators.required, ]),
        networkOperator:new FormControl({value:this.ciDataForEdit.networkOperator,disabled:true }, [ Validators.required, ]),
        signalStrength:new FormControl({value:this.ciDataForEdit.signalStrength,disabled:true }, [ Validators.required, ]),
        connectionType:new FormControl({value:this.ciDataForEdit.connectionType,disabled:true }, [ Validators.required, ]),
        meterNo:new FormControl({value:this.ciDataForEdit.meterNo,disabled:!this.isEditable }, [ Validators.required, ]),
        meterType:new FormControl({value:this.ciDataForEdit.meterType,disabled:!this.isEditable }, [ Validators.required, ]),
        meterMake:new FormControl({value:this.ciDataForEdit.meterMake,disabled:!this.isEditable }, [ Validators.required, ]),
        meterReading:new FormControl({value:this.ciDataForEdit.meterReading,disabled:!this.isEditable }, [ Validators.required, ]),
        meterBoxStatus:new FormControl({value:this.ciDataForEdit.meterBoxStatus,disabled:!this.isEditable }, [ Validators.required, ]),
        meterSealStatus:new FormControl({value:this.ciDataForEdit.meterSealStatus,disabled:true }, [ Validators.required, ]),
        agencyVerified:new FormControl({value:this.ciDataForEdit.agencyVerified,disabled:true }, [ Validators.required, ]),
        verifiedBy:new FormControl({value:this.ciDataForEdit.verifiedBy,disabled:true }, [ Validators.required, ]),
        verifiedDate:new FormControl({value:this.ciDataForEdit.verifiedDate,disabled:true }, [ Validators.required, ] ),
        remarks:new FormControl({value:this.ciDataForEdit.remarks,disabled:true }, [ Validators.required, ]),
      });

    }
if(!this.isEditable){
  this.adminApprovalForm.disable();
}

  }

  saveData(){
    if (!this.adminApprovalForm.valid) {
      return;
    }
    this.spinner = true;

    this.restClient
      .post(environment.SAVE_CI_APPROVAL_DATA_AFTER_EDIT, this.adminApprovalForm.getRawValue())
      .subscribe({
        next: (result: APIResponse) => {
          // this.ciDataForEdit= result.data as CIDataForEdit;
this.dataSaved.emit(0)
        },
        error: (err: any) => {
          console.log(err);
          let snackBarRef = this.snackBar.open('Cannot Edit Data', 'close', {
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

  closeDetailsTab(): void {
    this.dataSaved.emit(0);
  }


  openImageDialog():void{

    const dialogRef = this.dialog.open(CiMeterImageDialogComponent, {
      width: '50%',
      // height: '50%',
      data:this.ciDataForEdit.photoBase64,
    });
  }
  downloadPhoto():void{
  // Decode Base64 string
  const decodedData = window.atob(this.ciDataForEdit.photoBase64);

  // Create UNIT8ARRAY of size same as row data length
  const uInt8Array = new Uint8Array(decodedData.length);

  // Insert all character code into uInt8Array
  for (let i = 0; i < decodedData.length; ++i) {
    uInt8Array[i] = decodedData.charCodeAt(i);
  }

   saveAs( new Blob([uInt8Array], { type: 'image/jpg' }), this.ciDataForEdit.consumerNo+".jpg");
  }

}

