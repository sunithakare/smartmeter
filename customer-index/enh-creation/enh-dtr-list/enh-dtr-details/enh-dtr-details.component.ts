import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { environment } from 'src/environments/environment';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EnhDtrData } from '../enh-dtr-list.component';

@Component({
  selector: 'app-enh-dtr-details',
  templateUrl: './enh-dtr-details.component.html',
  styleUrls: ['./enh-dtr-details.component.css']
})
export class EnhDtrDetailsComponent implements OnInit ,OnChanges{

  spinner: boolean = false;
  @Input()
  isNavigation: boolean = false;
  @Input()
  isEditable: boolean = false;
  @Input()
  EnhDtrDetailsComponent: EnhDtrData;

  @Output()
  dataSaved: EventEmitter<number> = new EventEmitter();

  enhDtrForm : FormGroup;
  storeCode:any;
  constructor(
    public restClient: HttpclientService,
    public snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.enhDtrForm = new FormGroup({
      feeder: new FormControl({value:'', disabled:! this.isEditable },Validators.required),
      code: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      location: new FormControl({value:'', disabled: !this.isEditable },Validators.required),
      dtrRating: new FormControl({value:'', disabled:! this.isEditable },Validators.required),
      name: new FormControl({value:'', disabled:! this.isEditable },Validators.required),
      landmark: new FormControl({value:'', disabled:! this.isEditable },Validators.required),
      division: new FormControl({value:'', disabled:! this.isEditable },Validators.required),
    });
    this.enhDtrForm.controls['code'].valueChanges.subscribe(value => {
      this.enhDtrForm.controls['code'].patchValue(this.enhDtrForm.controls['code'].value.toUpperCase(), {emitEvent: false});
    });
    this.enhDtrForm.controls['division'].valueChanges.subscribe(value => {
      this.enhDtrForm.controls['division'].patchValue(this.enhDtrForm.controls['division'].value.toUpperCase(), {emitEvent: false});
    });
  }
  ngOnChanges(){

    if(this.EnhDtrDetailsComponent== undefined && this.EnhDtrDetailsComponent==null){
      this.enhDtrForm= new FormGroup({
        feeder: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        code: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        location: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        dtrRating: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        name: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        landmark: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
        division: new FormControl({value:[], disabled: !this.isEditable}, [Validators.required]),
      });
    }
    else{
      this.enhDtrForm = new FormGroup({
        feeder: new FormControl({value:this.EnhDtrDetailsComponent.feeder, disabled: !this.isEditable}, [Validators.required]),
        code: new FormControl({value:this.EnhDtrDetailsComponent.code, disabled: !this.isEditable}, [Validators.required]),
        location: new FormControl({value:this.EnhDtrDetailsComponent.location, disabled: !this.isEditable}, [Validators.required]),
        dtrRating: new FormControl({value:this.EnhDtrDetailsComponent.dtrRating, disabled: !this.isEditable}, [Validators.required]),
        name: new FormControl({value:this.EnhDtrDetailsComponent.name, disabled: !this.isEditable}, [Validators.required]),
        landmark: new FormControl({value:this.EnhDtrDetailsComponent.landmark, disabled: !this.isEditable}, [Validators.required]),
        division: new FormControl({value:this.EnhDtrDetailsComponent.division, disabled: !this.isEditable}, [Validators.required]),
      })
      this.getFeederByDivision(this.EnhDtrDetailsComponent.division);
    }

    this.enhDtrForm.controls['code'].valueChanges.subscribe(value => {
      this.enhDtrForm.controls['code'].patchValue(this.enhDtrForm.controls['code'].value.toUpperCase(), {emitEvent: false});
    });
    this.enhDtrForm.controls['division'].valueChanges.subscribe(value => {
      this.enhDtrForm.controls['division'].patchValue(this.enhDtrForm.controls['division'].value.toUpperCase(), {emitEvent: false});
    });
  }

  saveEnhDtr(){

    if (!this.enhDtrForm.valid) {
      return;
    }
    this.spinner = true;

    var queryParams: any = {};
    let apiURL = '';
    if (this.isNavigation) {
      apiURL = environment.UPDATE_DTR;
    } else {
      apiURL = environment.SAVE_DTR;
    }

    this.restClient
      .post(apiURL, this.enhDtrForm.getRawValue())
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
  getByDivision(event:any){
    this.getFeederByDivision(event.target.value);
  }
    getFeederByDivision(division:string){
    var queryParam:any={};
    queryParam['division'] = division;

    this.restClient.getwithParam(environment.FETCH_FEEDER_FROM_DIVISION,queryParam).subscribe({
      next:(res:any)=>{
        this.storeCode=res.data;
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
    })
  }

}
