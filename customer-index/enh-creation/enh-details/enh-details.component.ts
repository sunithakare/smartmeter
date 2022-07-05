import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatSelectChange } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import {
  APIResponse,
  HttpclientService,
} from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';
import { OrgNetwork } from '../enh-creation.component';

@Component({
  selector: 'app-enh-details',
  templateUrl: './enh-details.component.html',
  styleUrls: ['./enh-details.component.css'],
})
export class EnhDetailsComponent implements OnInit {
  @Input()
  isEditable: boolean = true;
  @Input()
  isNavigation: boolean = false;
  @Input()
  enhOrgData: OrgNetwork;

  @Output() dataSaved: EventEmitter<number> = new EventEmitter();

  spinner: boolean = false;
  enhForm: FormGroup;
  dropdownData:ENHDropdownData;
  dataSource = new MatTableDataSource<any>();
  displayedColumns: string[] = [
    'sNo',
    'subStation',
    'feeder',
    'dtr',
    'pole',
    'action',
  ];

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.enhForm = new FormGroup({
      state: new FormControl({ value: '', disabled: true }, [
        Validators.required,
      ]),
      discom: new FormControl({ value: '', disabled: true }, [
        Validators.required,
      ]),
      zone: new FormControl({ value: '', disabled: true }, [
        Validators.required,
      ]),
      city: new FormControl({ value: '', disabled: true }, [
        Validators.required,
      ]),
      circle: new FormControl({ value: '', disabled: true }, [
        Validators.required,
      ]),
      division: new FormControl({ value: '', disabled: true }, [
        Validators.required,
      ]),
      subDivision: new FormControl({ value: '', disabled: true }, [
        Validators.required,
      ]),
      enhRecordDataList: this.formBuilder.array([]),
    });

    let arrayResposne: EnhRecord[] = [];
    let FGArray = arrayResposne.map(this.RecordAsFormGroup, {
      isEditable: this.isEditable,
    });

    this.enhForm.setControl('enhRecordDataList', new FormArray(FGArray));

    this.dataSource = new MatTableDataSource(
      (this.enhForm.get('enhRecordDataList') as FormArray).controls
    );
  }

  ngOnChanges() {
    if (this.enhOrgData != null || this.enhOrgData != undefined) {
      this.spinner = true;
      this.enhForm = new FormGroup({
        state: new FormControl(
          { value: this.enhOrgData.state, disabled: true },
          [Validators.required]
        ),
        stateName: new FormControl(
          { value: this.enhOrgData.stateName, disabled: true },
          [Validators.required]
        ),
        discom: new FormControl(
          { value: this.enhOrgData.discom, disabled: true },
          [Validators.required]
        ),
        discomName: new FormControl(
          { value: this.enhOrgData.discomName, disabled: true },
          [Validators.required]
        ),
        zone: new FormControl({ value: this.enhOrgData.zone, disabled: true }, [
          Validators.required,
        ]),
        zoneName: new FormControl({ value: this.enhOrgData.zoneName, disabled: true }, [
          Validators.required,
        ]),
        city: new FormControl({ value: this.enhOrgData.city, disabled: true }, [
          Validators.required,
        ]),
        cityName: new FormControl({ value: this.enhOrgData.cityName, disabled: true }, [
          Validators.required,
        ]),
        circle: new FormControl(
          { value: this.enhOrgData.circle, disabled: true },
          [Validators.required]
        ),
        circleName: new FormControl(
          { value: this.enhOrgData.circleName, disabled: true },
          [Validators.required]
        ),
        division: new FormControl(
          { value: this.enhOrgData.division, disabled: true },
          [Validators.required]
        ),
        divisionName: new FormControl(
          { value: this.enhOrgData.divisionName, disabled: true },
          [Validators.required]
        ),
        subDivision: new FormControl(
          { value: this.enhOrgData.subDivision, disabled: true },
          [Validators.required]
        ),
        subDivisionName: new FormControl(
          { value: this.enhOrgData.subDivisionName, disabled: true },
          [Validators.required]
        ),
        enhRecordDataList: this.formBuilder.array([]),
      });

      this.dataSource = new MatTableDataSource(
        (this.enhForm.get('enhRecordDataList') as FormArray).controls
      );

      var queryParams: any = {};
      queryParams['type'] = 'ROLETYPE';

      this.restClient
        .post(environment.FETCH_ENH_DATA_LIST_API, this.enhOrgData)
        .subscribe({
          next: (result: APIResponse) => {
            var arrayResposne = result.data as EnhRecord[];
            let FGArray = arrayResposne.map(this.RecordAsFormGroup, {
              isEditable: this.isEditable,
            });

            this.enhForm.setControl(
              'enhRecordDataList',
              new FormArray(FGArray)
            );

            this.dataSource = new MatTableDataSource(
              (this.enhForm.get('enhRecordDataList') as FormArray).controls
            );
          },
          error: (err: any) => {
            console.log(err);
          },
          complete: () => {
            console.log('List Fetch Complete ');

            this.spinner = false;
          },
        });


        this.fetchDataforDropdown(this.enhOrgData.division);
    }
  }
  addNewRecord() {
    if (!this.enhForm.valid) {
      let snackBarRef = this.snackBar.open(
        'Please Verify Existing Record',
        'close',
        {
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          duration: 3000,
        }
      );
      return;
    }

    var fg: FormGroup = this.RecordAsFormGroup(new EnhRecord());

    (<FormArray>this.enhForm.get('enhRecordDataList')).push(fg);
    this.dataSource.data = this.dataSource.data;
  }

  removeRecord(i: number) {
    (<FormArray>this.enhForm.get('enhRecordDataList')).removeAt(i);
    this.dataSource.data = this.dataSource.data;
  }
  RecordAsFormGroup(recordData: EnhRecord): FormGroup {
    const fg = new FormGroup({
      subStation: new FormControl(
        { value: recordData.subStation, disabled: !this.isEditable },
        Validators.required
      ),
      feeder: new FormControl(
        { value: recordData.feeder, disabled: !this.isEditable },
        Validators.required
      ),
      dtr: new FormControl(
        { value: recordData.dtr, disabled: !this.isEditable },
      ),
      pole: new FormControl(
        { value: recordData.pole, disabled: !this.isEditable },
        Validators.required
      ),
    });
    return fg;
  }

  saveEnhData() {
    if (!this.enhForm.valid) {
      return;
    }

    let requestBody = this.enhForm.getRawValue();
    this.spinner = true;

    var queryParams: any = {};

    this.restClient
      .post(environment.SAVE_ENH_DATA_LIST_API, requestBody)
      .subscribe({
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
          console.log('enh Data Saved');

          this.spinner = false;
        },
      });
  }

fetchDataforDropdown(division:string):void{

  var queryParams: any = {};
  queryParams['division']=division;

  this.restClient
  .getwithParam(environment.FETCH_ALL_DATA_FOR_UI_DROPDOWN, queryParams)
  .subscribe({
    next: (result: APIResponse) => {
      this.dropdownData= result.data as ENHDropdownData;
    },
    error: (err: any) => {
      console.log(err);
      let snackBarRef = this.snackBar.open('Server Error', 'close', {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 3000,
      });
    },
    complete: () => {
      console.log('Dropdown Fetch Complete ');
    },
  });
}

subStationChange(element:FormGroup):void{

  element.controls['feeder'].setValue(null);
  element.controls['dtr'].setValue(null);
  element.controls['pole'].setValue(null);

}
feederChange(element:FormGroup):void{

  element.controls['dtr'].setValue(null);
  element.controls['pole'].setValue(null);

}
dtrChange(element:FormGroup):void{

  element.controls['pole'].setValue(null);

}

compareSubStation(a:any,b:any):boolean{
  return a.code==b.code;
}

compareFeeder(a:any,b:any):boolean{
  return a.code==b.code;
}
compareDtr(a:any,b:any):boolean{
  return a.code==b.code;
}
comparePole(a:any,b:any):boolean{
  return a.code==b.code;
}
}
export class EnhRecord {
  subStation: any;
  feeder: any;
  dtr: any;
  pole: any;
}


export interface ENHDropdownData {
  substationList: FeederListElement[];
  feederList:     FeederListElement[];
  dtrList:        DTRListElement[];
  poleList:       DTRListElement[];
}

export interface DTRListElement {
  name:       string;
  code:       string;
  dtrRating?: string;
  location:   string;
  landmark:   string;
  feeder:     string;
  division:   string;
  poleType?:  null;
  dtr?:       string;
}

export interface FeederListElement {
  name:        string;
  code:        string;
  location:    string;
  division:    string;
  subStation?: string;
  landmark:    string;
}
