import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SubStationListData } from '../enh-sub-station.component';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-enh-sub-station-details',
  templateUrl: './enh-sub-station-details.component.html',
  styleUrls: ['./enh-sub-station-details.component.css']
})
export class EnhSubStationDetailsComponent implements OnInit {
  @Input()
  isNavigation: boolean = false;
  @Input()
  isEditable: boolean = false;

  @Input()
  subStationDetails: SubStationListData;
  spinner: any;

  @Output()
  dataSaved: EventEmitter<number> = new EventEmitter();

  enhSubStationForm:FormGroup;

  constructor(
    public restClient: HttpclientService,
    public snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.enhSubStationForm = new FormGroup({
      name: new FormControl([], [Validators.required]),
      code: new FormControl([], [Validators.required]),
      location: new FormControl([], [Validators.required]),
      landmark: new FormControl([], [Validators.required]),
      division: new FormControl([], [Validators.required]),

    });
    this.enhSubStationForm.controls['code'].valueChanges.subscribe(value => {
      this.enhSubStationForm.controls['code'].patchValue(this.enhSubStationForm.controls['code'].value.toUpperCase(), {emitEvent: false});
    });
    this.enhSubStationForm.controls['division'].valueChanges.subscribe(value => {
      this.enhSubStationForm.controls['division'].patchValue(this.enhSubStationForm.controls['division'].value.toUpperCase(), {emitEvent: false});
    });
  }

  ngOnChanges(){
    if(this.subStationDetails == null && this.subStationDetails == undefined){
      this.enhSubStationForm = new FormGroup({
        name: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        code: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        location: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        landmark: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        division: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
      });
    }else{
      this.enhSubStationForm = new FormGroup({
        name: new FormControl({value:this.subStationDetails.name, disabled: !this.isEditable}, [Validators.required]),
        code: new FormControl({value:this.subStationDetails.code, disabled: !this.isEditable}, [Validators.required]),
        location: new FormControl({value:this.subStationDetails.location, disabled: !this.isEditable}, [Validators.required]),
        landmark: new FormControl({value:this.subStationDetails.landmark, disabled: !this.isEditable}, [Validators.required]),
        division: new FormControl({value:this.subStationDetails.division, disabled: !this.isEditable}, [Validators.required]),
      });
    }

this.enhSubStationForm.controls['code'].valueChanges.subscribe(value => {
  this.enhSubStationForm.controls['code'].patchValue(this.enhSubStationForm.controls['code'].value.toUpperCase(), {emitEvent: false});
});
this.enhSubStationForm.controls['division'].valueChanges.subscribe(value => {
  this.enhSubStationForm.controls['division'].patchValue(this.enhSubStationForm.controls['division'].value.toUpperCase(), {emitEvent: false});
});
  }

  saveSubStationData(){
    if(!this.enhSubStationForm.valid){
      return;
    }
    this.spinner = true;

    var queryParams: any = {};
    let apiURL = '';
    if(this.isNavigation){
      apiURL = environment.UPDATE_SUBSTATION_API;
    } else{
      apiURL = environment.SAVE_SUBSTATION_API;
    }

    this.restClient
      .post(apiURL, this.enhSubStationForm.getRawValue())
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
