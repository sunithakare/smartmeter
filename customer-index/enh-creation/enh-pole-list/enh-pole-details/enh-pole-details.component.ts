import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSelectChange } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';
import { EnhPoleData } from '../enh-pole-list.component';

@Component({
  selector: 'app-enh-pole-details',
  templateUrl: './enh-pole-details.component.html',
  styleUrls: ['./enh-pole-details.component.css']
})
export class EnhPoleDetailsComponent implements OnInit {
  @Input()
  isNavigation: boolean = false;
  @Input()
  isEditable: boolean = false;

  @Input()
  EnhPoleRsponse:EnhPoleData;
  spinner: any;

storeFeeder:any;
storedtr:any;

  @Output()
  dataSaved: EventEmitter<number> = new EventEmitter();

  enhPoleForm: FormGroup;

  constructor(
    public restClient: HttpclientService,
    public snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.enhPoleForm=new FormGroup({
      name:new FormControl('',[Validators.required]),
      code:new FormControl('',[Validators.required]),
      poleType:new FormControl('',[Validators.required]),
      landmark:new FormControl('',[Validators.required]),
      location:new FormControl('',[Validators.required]),
      feeder:new FormControl('',[Validators.required]),
      dtr:new FormControl(''),
      division:new FormControl('',[Validators.required]),
    });

    this.enhPoleForm.controls['code'].valueChanges.subscribe(value => {
      this.enhPoleForm.controls['code'].patchValue(this.enhPoleForm.controls['code'].value.toUpperCase(), {emitEvent: false});
    });
    this.enhPoleForm.controls['division'].valueChanges.subscribe(value => {
      this.enhPoleForm.controls['division'].patchValue(this.enhPoleForm.controls['division'].value.toUpperCase(), {emitEvent: false});
    });
  }

  ngOnChanges() {
    if(this.EnhPoleRsponse== undefined && this.EnhPoleRsponse==null){
      this.enhPoleForm= new FormGroup({
        feeder: new FormControl({value:'', disabled: !this.isEditable}, [Validators.required]),
        code: new FormControl({value:'', disabled: !this.isEditable}, [Validators.required]),
        location: new FormControl({value:'', disabled: !this.isEditable}, [Validators.required]),
        dtr: new FormControl({value:'', disabled: !this.isEditable}, ),
        name: new FormControl({value:'', disabled: !this.isEditable}, [Validators.required]),
        landmark: new FormControl({value:'', disabled: !this.isEditable}, [Validators.required]),
        poleType: new FormControl({value:'', disabled: !this.isEditable}, [Validators.required]),
        division: new FormControl({value:'', disabled: !this.isEditable}, [Validators.required]),
      });
    }
    else{
     // this.getDivison(this.EnhPoleRsponse.division)
     // this.getByFeeder(this.EnhPoleRsponse.feeder);
      this.enhPoleForm = new FormGroup({
        feeder: new FormControl({value:this.EnhPoleRsponse.feeder, disabled: !this.isEditable}, [Validators.required]),
        code: new FormControl({value:this.EnhPoleRsponse.code, disabled: !this.isEditable}, [Validators.required]),
        location: new FormControl({value:this.EnhPoleRsponse.location, disabled: !this.isEditable}, [Validators.required]),
        dtr: new FormControl({value:this.EnhPoleRsponse.dtr, disabled: !this.isEditable}),
        name: new FormControl({value:this.EnhPoleRsponse.name, disabled: !this.isEditable}, [Validators.required]),
        landmark: new FormControl({value:this.EnhPoleRsponse.landmark, disabled: !this.isEditable}, [Validators.required]),
        division: new FormControl({value:this.EnhPoleRsponse.division, disabled: !this.isEditable}, [Validators.required]),
        poleType: new FormControl({value:this.EnhPoleRsponse.poleType, disabled: this.isEditable}, [Validators.required]),
      })

    this.getFeederByDivision(this.EnhPoleRsponse.division);
    this.getDtrByFeeder(this.EnhPoleRsponse.feeder);
    }

    this.enhPoleForm.controls['code'].valueChanges.subscribe(value => {
      this.enhPoleForm.controls['code'].patchValue(this.enhPoleForm.controls['code'].value.toUpperCase(), {emitEvent: false});
    });
    this.enhPoleForm.controls['division'].valueChanges.subscribe(value => {
      this.enhPoleForm.controls['division'].patchValue(this.enhPoleForm.controls['division'].value.toUpperCase(), {emitEvent: false});
    });
     }

  saveEnhPoleData(){
    if (!this.enhPoleForm.valid) {
      return;
    }
    this.spinner = true;

    var queryParams: any = {};
    let apiURL = '';
    if (this.isNavigation) {
      apiURL = environment.ENH_POLE_UPDATE_API;
    } else {
      apiURL = environment.ENH_POLE_SAVE_API;
    }

    this.restClient
      .post(apiURL, this.enhPoleForm.getRawValue())
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
          console.log('Pole Data Saved');
          this.spinner = false;
        },
      });
  }

  closeDetailsTab():void{
    this.dataSaved.emit(0);
  }


  getFeederByDivisionOnChange(event:any){
      this.getFeederByDivision(event.target.value);

  }
  getFeederByDivision(division:string){
    var queryParams: any={};
    queryParams['division']=division;

    this.restClient.getwithParam(environment.ENH_POLE_GET_FEEDER_BY_DIVISION_API,queryParams).subscribe({
      next:(result:any)=>{
        this.storeFeeder=result.data;
      },
        error: (err: any) => {
          console.log(err);
          this.spinner = false;
        },
        complete: () => {
          this.spinner = false;
        },
    })
  }
  getDtrByFeederOnChange(event:MatSelectChange){
    this.getDtrByFeeder(event.value);
  }
  getDtrByFeeder(feeder:string){
    var queryParams: any={};
    queryParams['feeder']=feeder;

    this.restClient.getwithParam(environment. ENH_POLE_GET_DTR_BY_FEEDER_API,queryParams).subscribe({
      next:(result:any)=>{
        this.storedtr=result.data;
      },
      error: (err: any) => {
        console.log(err);
        this.spinner = false;
      },
      complete: () => {
        this.spinner = false;
      },
    })
  }






// getByFeeder(row:string){
//   var queryParams: any={};
//   queryParams['feeder']=row;

//   this.restClient.getwithParam(environment. ENH_POLE_GET_DTR_BY_FEEDER_API,queryParams).subscribe({
//     next:(result:any)=>{
//       this.storedtr=result.data;
//     },
//     error: (err: any) => {
//       console.log(err);
//       this.spinner = false;
//     },
//     complete: () => {
//       this.spinner = false;
//     },
//   })
//  }

//  getDivison(value:string){
//   var queryParams: any={};
//   queryParams['division']=value;

//   this.restClient.getwithParam(environment.ENH_POLE_GET_FEEDER_BY_DIVISION_API,queryParams).subscribe({
//     next:(result:any)=>{
//       this.storeFeeder=result.data;
//     },
//       error: (err: any) => {
//         console.log(err);
//         this.spinner = false;
//       },
//       complete: () => {
//         this.spinner = false;
//       },
//   })
//  }
}
export interface enhPoleData{
  name:string;
code:string;
poleType:string;
location:string;
landmark:string;
feeder:string;
dtr:string;
division:string;
}
