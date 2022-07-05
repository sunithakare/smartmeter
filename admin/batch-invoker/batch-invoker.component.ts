import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from 'src/app/services/auth.service';
import {
  APIResponse,
  HttpclientService,
} from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-batch-invoker',
  templateUrl: './batch-invoker.component.html',
  styleUrls: ['./batch-invoker.component.css'],
})
export class BatchInvokerComponent implements OnInit {
  batchName: string = '';

  spinner = false;
  invokeBatch = false;

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) {}

  ngOnInit(): void {}

  checkStatus(): void {
    this.spinner = true;
    this.restClient
      .getwithParam(environment.BATCH_STATUS + '/' + this.batchName, {})
      .subscribe({
        next: (result: APIResponse) => {
var status=result.data as BatchStatus;
          let snackBarRef = this.snackBar.open(
            'Batch is in Progress- '+'Porcessed Count: '+status.writeCount+' - Skiped Count: '+status.skipCount,
            'close',
            {
              horizontalPosition: 'center',
              verticalPosition: 'bottom',
              duration: 3000,
            }
          );


        },
        error: (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status == 404) {
              let snackBarRef = this.snackBar.open(
                'Batch Not Running',
                'close',
                {
                  horizontalPosition: 'center',
                  verticalPosition: 'bottom',
                  duration: 3000,
                }
              );
              this.invokeBatch=true;
              return;
            }
          }
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



  runBatch(): void {
    this.invokeBatch=false;
    this.restClient
      .getwithParam(environment.BATCH_INVOKE + '/' + this.batchName, {})
      .subscribe({
        next: (result: APIResponse) => {

        },
        error: (err: any) => {

          let snackBarRef = this.snackBar.open('Server Error', 'close', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 3000,
          });
        },
        complete: () => {
        },
      });
  }
}
export interface BatchStatus {
  writeCount: number;
  skipCount:  number;
}
