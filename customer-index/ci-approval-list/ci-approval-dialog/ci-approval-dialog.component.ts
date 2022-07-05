
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
@Component({
  selector: 'app-ci-approval-dialog',
  templateUrl: './ci-approval-dialog.component.html',
  styleUrls: ['./ci-approval-dialog.component.css']
})
export class CiApprovalDialogComponent implements OnInit {


spinner:boolean=false;

  constructor(
    public dialogRef: MatDialogRef<CiApprovalDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CIApprovalDialogData,
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private router: Router,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }

  onsubmit() {
    var requestBody = {
      status:this.data.status,
      remarks:this.data.remarks,
      type:this.data.type,
      consumerNoList: this.data.consumerNoList,
    }
    this.spinner = true;
    // console.log(JSON.stringify(requestBody));
    this.restClient
      .post(this.data.submitURL, requestBody)
      .subscribe({
        next: (result: APIResponse) => {

          let snackBarRef = this.snackBar.open(
            'Request Approved SuccessFully',
            'close',
            {
              horizontalPosition: 'center',
              verticalPosition: 'bottom',
              duration: 3000,
            }
          );
          this.dialogRef.close();
          // window.location.reload();
        },
        error: (err: any) => {
          console.log(err);
          let snackBarRef = this.snackBar.open('Some Error Occoured! Try Again', 'close', {
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


}
export interface CIApprovalDialogData{
status:string;
consumerNoList:string[];
remarks:string;
type:string;
submitURL:string;
}
