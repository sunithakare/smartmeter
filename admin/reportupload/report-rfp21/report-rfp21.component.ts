import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-report-rfp21',
  templateUrl: './report-rfp21.component.html',
  styleUrls: ['./report-rfp21.component.css']
})
export class ReportRfp21Component implements OnInit {
  spinner:boolean=false;

  file: File|null;
  @ViewChild('fileInput')
  fileInputVariable: ElementRef;
  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,) { }

  ngOnInit(): void {
  }

  selectFile(event: any): void {
    if (event.target.files && event.target.files[0]) {
      this.file = event.target.files[0];
      // this.currentFile = file;
      // this.fileName = this.currentFile.name;

      // this.uploadForm.controls['file'].setValue(this.file);
      // this.uploadForm.controls['file'].updateValueAndValidity();

    } else {
      // this.fileName = 'Select File';
    }
  }
  uploadReport():void{
    this.spinner = true;
    var formData=new FormData();
    formData.append("uploadFile",this.file!);
        this.restClient.uploadFile(environment.SLAREPORTS_UPLOAD_RFP21_API, formData).subscribe({
          next: (result: APIResponse) => {
            let snackBarRef = this.snackBar.open(
              'Data Saved SuccessFully',
              'close',
              {
                horizontalPosition: 'center',
                verticalPosition: 'bottom',
                duration: 3000,
              }
            );
            this.file=null;
            this.fileInputVariable.nativeElement.value = "";
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
            console.log('Role Saved');

            this.spinner = false;
          },
        });
      }
}
