
import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';
import { CiEnhFeeder } from '../enh-feeder-list.component';

@Component({
  selector: 'app-enh-feeder-details',
  templateUrl: './enh-feeder-details.component.html',
  styleUrls: ['./enh-feeder-details.component.css']
})
export class EnhFeederDetailsComponent implements OnInit {

  @Input()
  isEditable: boolean = true;

  @Input()
  enhFeederData!: CiEnhFeeder | undefined;
  @Input()
  isNavigation: boolean = false;

storeCode:any;
  constructor(private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private formBuilder: FormBuilder,) { }
  spinner=false;
  @Output() dataSaved: EventEmitter<number> = new EventEmitter();



  enhFeederForm!:FormGroup;

  ngOnInit(): void {
    this.enhFeederForm=new FormGroup({
      code:new FormControl('',Validators.required),
      location:new FormControl('',Validators.required),
      subStation:new FormControl('',Validators.required),
      division:new FormControl('',Validators.required),
      name:new FormControl('',Validators.required),
      landmark:new FormControl('',Validators.required),
    });
    this.enhFeederForm.controls['code'].valueChanges.subscribe(value => {
      this.enhFeederForm.controls['code'].patchValue(this.enhFeederForm.controls['code'].value.toUpperCase(), {emitEvent: false});
    });
    this.enhFeederForm.controls['division'].valueChanges.subscribe(value => {
      this.enhFeederForm.controls['division'].patchValue(this.enhFeederForm.controls['division'].value.toUpperCase(), {emitEvent: false});
    });
  }

  ngOnChanges() {
    if(this.enhFeederData!=null ||this.enhFeederData!=undefined){

      this.spinner = true;
      this.enhFeederForm = new FormGroup({

        location: new FormControl({value: this.enhFeederData.location, disabled: !this.isEditable }, [ Validators.required, ]),
        landmark: new FormControl({value: this.enhFeederData.landmark, disabled: !this.isEditable }, [ Validators.required, ]),
        name: new FormControl({value: this.enhFeederData.name, disabled: !this.isEditable }, [Validators.required]),
        code: new FormControl({value: this.enhFeederData.code, disabled: !this.isEditable }, [Validators.required]),
        division: new FormControl({value: this.enhFeederData.division, disabled: !this.isEditable }, [ Validators.required, ]),
        subStation: new FormControl({value: this.enhFeederData.subStation, disabled: !this.isEditable }, [Validators.required]),
        // enhRecordDataList: this.formBuilder.array([]),
      });


      this.getSubStationByDivision(this.enhFeederData.division);

      var queryParams: any = {};
      queryParams['code'] = this.enhFeederData.code;

    }else{
      this.enhFeederForm=new FormGroup({
        code:new FormControl('',Validators.required),
        location:new FormControl('',Validators.required),
        subStation:new FormControl('',Validators.required),
        division:new FormControl('',Validators.required),
        name:new FormControl('',Validators.required),
        landmark:new FormControl('',Validators.required),
      });
    }

this.enhFeederForm.controls['code'].valueChanges.subscribe(value => {
  this.enhFeederForm.controls['code'].patchValue(this.enhFeederForm.controls['code'].value.toUpperCase(), {emitEvent: false});
});
this.enhFeederForm.controls['division'].valueChanges.subscribe(value => {
  this.enhFeederForm.controls['division'].patchValue(this.enhFeederForm.controls['division'].value.toUpperCase(), {emitEvent: false});
});
  }


  getSubStationByDivisionOnChange(event:any){
    this.getSubStationByDivision(event.target.value);
  }
  getSubStationByDivision(division:string){
    this.spinner = true;
    var queryParam:any={};
    queryParam['division']=division;

    this.restClient.getwithParam(environment.ENH_FEEDER_FIND_SUBSTATION_BY_DIVISION,queryParam).subscribe({
      next:(res:any)=>{
        this.storeCode=res.data;
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
    })

  }



  saveEnhFeederData(){
    if (!this.enhFeederForm.valid) {

      return;
    }

    let requestBody = this.enhFeederForm.getRawValue();
    this.spinner = true;

    var queryParams: any = {};

    let apiURL = '';
    if (this.isNavigation) {
      apiURL = environment.ENH_FEEDER_UPDATE;
    } else {
      apiURL = environment.ENH_FEEDER_SAVE;
    }



    this.restClient.post(apiURL,requestBody).subscribe({
      next:(res:APIResponse)=>{
        let snackBarRef=this.snackBar.open(
          'Feeder Data saved SuccessFully',
          'close',
          {
            horizontalPosition:'center',
            verticalPosition:'bottom',
            duration:3000,
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
        console.log('Enh Feeder Data Saved');

        this.spinner = false;
      },
    });


  }


  cancelEnhFeederDetails(){
    this.dataSaved.emit(0);
  }

}
