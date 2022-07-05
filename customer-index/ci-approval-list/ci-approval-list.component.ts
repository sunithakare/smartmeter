import { Component, Input, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import {
  APIResponse,
  HttpclientService,
  PageableResponse,
} from 'src/app/services/httpclient.service';
import {
  environment,
  roleConfig,
  roleType,
} from 'src/environments/environment';
import { saveAs } from 'file-saver';
import { CiApprovalDialogComponent } from './ci-approval-dialog/ci-approval-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth.service';
import { MatSelectChange } from '@angular/material/select';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-ci-approval-list',
  templateUrl: './ci-approval-list.component.html',
  styleUrls: ['./ci-approval-list.component.css'],
})
export class CiApprovalListComponent implements OnInit {
  selectAllVar: boolean = false;
  spinner: boolean = false;
  selectIndex = 0;
  downdownFilterData: EnhDropdownResponse[];
  ciDataForEdit: CIDataForEdit;
  isEditable: boolean;
  allSelected: boolean = false;
  @Input()
  type: string = 'ALL';

  displayColumns: string[] = [
    'selectAll',
    'consumerNo',
    'mobileNo',
    'ciDate',
    'latitudelongitude',
    'signalStrength',
    'division',
    'subDivision',
    'subStation',
    'feeder',
    'dtr',
    'pole',
    'oldMeterNo',
    'address',
    'exceptions',
    // 'status',
  ];
  isLoading: boolean = false;
  dataSource = new MatTableDataSource<CiDataApprovalList>();

  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [100, 200, 500, 1000];

  stateList: ApprovalDropdownData[] = [];
  discomList: ApprovalDropdownData[] = [];
  cityList: ApprovalDropdownData[] = [];
  divisionList: ApprovalDropdownData[] = [];
  subDivisionList: ApprovalDropdownData[] = [];
  subStationList: ApprovalDropdownData[] = [];
  feederList: ApprovalDropdownData[] = [];

  searcherForm:FormGroup;


  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    public dialog: MatDialog,
    private authservice: AuthService
  ) {
    this.isEditable =
      this.authservice.checkPermission(
        roleType.ADMIN,
        roleConfig.CIApprovalEdit
      ) ||
      this.authservice.checkPermission(
        roleType.MANAGER,
        roleConfig.CIApprovalEdit
      );

      this.searcherForm=new FormGroup({
state:new FormControl(''),
discom:new FormControl(''),
city:new FormControl(''),
division:new FormControl(''),
subDivision:new FormControl(''),
subStation:new FormControl(''),
feeder:new FormControl(''),
consumerNo:new FormControl(''),
start:new FormControl(),
end:new FormControl(),


      });

if(this.type!='Reject' && this.type!='Approved'){
  this.displayColumns = [
    'consumerNo',
    'mobileNo',
    'ciDate',
    'latitudelongitude',
    'signalStrength',
    'division',
    'subDivision',
    'subStation',
    'feeder',
    'dtr',
    'pole',
    'oldMeterNo',
    'address',
    'exceptions',
    // 'status',
  ];
}


  }

  ngOnInit(): void {
    this.loadData();
    this.fetchDropdownData();
  }

  ngOnChanges() {}

  loadData() {
    // var content:AgencyAdminData[]=[{selectAll:this.selectAllVar,consumerNo:'1010',mobileNo:'1212',ciDate:'rio',latitudelongitude:'--',feeder:'324',dtr:'700',pole:'8686',status:'inactive'}
    // ,{consumerNo:'414',mobileNo:'1212',ciDate:'rio',latitudelongitude:'--',feeder:'324',dtr:'700',pole:'8686',status:'inactive'}] as AgencyAdminData[];
    // this.dataSource.data=content;
    this.isLoading = true;
    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;
    queryParams['type'] = this.type;

    this.restClient
      .getwithParam(environment.FETCH_CI_APPROVAL_DATA_LIST, queryParams)
      .subscribe({
        next: (result: PageableResponse) => {
          var content: CiDataApprovalList[] = result.data
            .content as CiDataApprovalList[];

          this.dataSource.data = content;
          this.totalRows = result.data.totalElements;
        },
        error: (err: any) => {
          console.log(err);
          let snackBarRef = this.snackBar.open('Server Error', 'close', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 3000,
          });
          this.isLoading = false;
        },
        complete: () => {
          console.log('Approval List Fetch Complete ');
          this.isLoading = false;
        },
      });
  }

  showDetails(row: any) {
    if (this.type == 'Reject') {
      let snackBarRef = this.snackBar.open('Cannot Edit Data', 'close', {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 3000,
      });
      return;
    }

    this.spinner = true;
    var queryParams: any = {};
    queryParams['consumerNo'] = row.consumerNo;

    this.restClient
      .getwithParam(environment.FETCH_CI_APPROVAL_DATA_FOR_EDIT, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          this.ciDataForEdit = result.data as CIDataForEdit;
          this.selectIndex = 1;
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
          console.log('Data Fetch Complete ');
          this.spinner = false;
        },
      });
  }

  selectAllOptional(event: MatCheckboxChange) {
    var content: CiDataApprovalList[] = this.dataSource.data;

    for (const iterator of content) {
      iterator.selected = event.checked;
    }
  }

  exportToExcel(): void {
    this.spinner = true;
    this.restClient
      .downloadusingPost(environment.DOWNLOAD_CI_APPROVAL_DATA_API+"? type="+this.type, this.searcherForm.getRawValue())
      .subscribe({
        next: (result: any) => {
          this.spinner = false;

          var filename = 'Approval Data List.xlsx';

          saveAs(result, filename);
        },
        error: (err: any) => {
          let snackBarRef = this.snackBar.open('Export Failed', 'close', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 3000,
          });
          this.spinner = false;
        },
        complete: () => {
          console.log('complete');
          this.spinner = false;
        },
      });
  }

  loadListTab() {
    this.selectIndex = 0;
  }
  checkAllSelcted(event: MatCheckboxChange) {
    if (!event.checked) {
      this.allSelected = false;
    }
  }

  handleApprovalAction(status: string): void {
    var consumerList = [];

    for (let index = 0; index < this.dataSource.data.length; index++) {
      const element = this.dataSource.data[index];
      if (element.selected) {
        consumerList.push(element.consumerNo);
      }
    }

    if (consumerList.length == 0) {
      let snackBarRef = this.snackBar.open(
        'Select Atleast One Record',
        'close',
        {
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          duration: 3000,
        }
      );
      return;
    }

    const dialogRef = this.dialog.open(CiApprovalDialogComponent, {
      width: '50%',
      // height: '50%',
      data: {
        consumerNoList: consumerList,
        remarks: '',
        status: status,
        type: this.type,
        submitURL: environment.CI_APPROVAL_API,
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      // this.dataSaved.emit(0);
      this.loadData();
    });
  }

  fetchDropdownData(): void {
    var queryParams: any = {};

    this.restClient
      .getwithParam(environment.FETCH_ENH_DROPDOWN_FOR_FILTER_CI_APPROVAL, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          this.downdownFilterData = result.data  as EnhDropdownResponse[];
          this.stateList = [];
          // for (const iterator of this.downdownFilterData) {
            for (let index = 0; index < this.downdownFilterData.length; index++) {
              const iterator = this.downdownFilterData[index];

            if (
              this.stateList.findIndex(
                (element) => element.value == iterator.state
              ) < 0
            ) {
              this.stateList.push({
                name: iterator.stateName,
                value: iterator.state,
              });
            }
          }
        },
        error: (err: any) => {
          console.log(err);
          let snackBarRef = this.snackBar.open('Filter Error', 'close', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 3000,
          });
          this.isLoading = false;
        },
        complete: () => {
          console.log('Approval List Fetch Complete ');
          this.isLoading = false;
        },
      });
  }

  onChangeOfState(event: MatSelectChange): void {
    this.spinner = true;
    this.discomList = [];
    for (const iterator of this.downdownFilterData) {
      if (event.value == iterator.state) {
        if (
          this.discomList.findIndex(
            (element) => element.value == iterator.discom
          ) < 0
        ) {
          this.discomList.push({
            name: iterator.discomName,
            value: iterator.discom,
          });
        }
      }
    }

    this.spinner = false;
  }
  onChangeOfDiscom(event: MatSelectChange): void {
    this.spinner = true;
    this.cityList = [];
    for (const iterator of this.downdownFilterData) {
      if (event.value == iterator.discom) {
        if (
          this.cityList.findIndex(
            (element) => element.value == iterator.city
          ) < 0
        ) {
          this.cityList.push({
            name: iterator.cityName,
            value: iterator.city,
          });
        }
      }
    }

    this.spinner = false;
  }


  onChangeOfCity(event: MatSelectChange): void {
    this.spinner = true;
    this.divisionList = [];
    for (const iterator of this.downdownFilterData) {
      if (event.value == iterator.city) {
        if (
          this.divisionList.findIndex(
            (element) => element.value == iterator.division
          ) < 0
        ) {
          this.divisionList.push({
            name: iterator.divisionName,
            value: iterator.division,
          });
        }
      }
    }

    this.spinner = false;
  }

  onChangeOfDivision(event: MatSelectChange): void {
    this.spinner = true;
    this.subDivisionList = [];
    for (const iterator of this.downdownFilterData) {
      if (event.value == iterator.division) {
        if (
          this.subDivisionList.findIndex(
            (element) => element.value == iterator.subDivision
          ) < 0
        ) {
          this.subDivisionList.push({
            name: iterator.subDivisionName,
            value: iterator.subDivision,
          });
        }
      }
    }

    this.spinner = false;
  }

  onChangeOfSubDivision(event: MatSelectChange): void {
    this.spinner = true;
    this.subStationList = [];
    for (const iterator of this.downdownFilterData) {
      if (event.value == iterator.subDivision) {
        if (
          this.subStationList.findIndex(
            (element) => element.value == iterator.subStation
          ) < 0
        ) {
          this.subStationList.push({
            name: iterator.subStationName,
            value: iterator.subStation,
          });
        }
      }
    }

    this.spinner = false;
  }

  onChangeOfSubStation(event: MatSelectChange): void {
    this.spinner = true;
    this.feederList = [];
    for (const iterator of this.downdownFilterData) {
      if (event.value == iterator.subStation) {
        if (
          this.feederList.findIndex(
            (element) => element.value == iterator.feeder
          ) < 0
        ) {
          this.feederList.push({
            name: iterator.feederName,
            value: iterator.feeder,
          });
        }
      }
    }

    this.spinner = false;
  }

  loadDataWithFilter() {
    // var content:AgencyAdminData[]=[{selectAll:this.selectAllVar,consumerNo:'1010',mobileNo:'1212',ciDate:'rio',latitudelongitude:'--',feeder:'324',dtr:'700',pole:'8686',status:'inactive'}
    // ,{consumerNo:'414',mobileNo:'1212',ciDate:'rio',latitudelongitude:'--',feeder:'324',dtr:'700',pole:'8686',status:'inactive'}] as AgencyAdminData[];
    // this.dataSource.data=content;
    this.isLoading = true;
    // var queryParams: any = {};
    // queryParams['page'] = this.currentPage;
    // queryParams['size'] = this.pageSize;
    // queryParams['type'] = this.type;

    this.restClient
      .post(environment.FETCH_CI_APPROVAL_DATA_LIST_WITH_FILTER+"?page="+this.currentPage+"&size="+ this.pageSize+"&type="+this.type, this.searcherForm.getRawValue())
      .subscribe({
        next: (result: PageableResponse) => {
          var content: CiDataApprovalList[] = result.data
            .content as CiDataApprovalList[];

          this.dataSource.data = content;
          this.totalRows = result.data.totalElements;
        },
        error: (err: any) => {
          console.log(err);
          let snackBarRef = this.snackBar.open('Server Error', 'close', {
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
            duration: 3000,
          });
          this.isLoading = false;
        },
        complete: () => {
          console.log('Approval List Fetch Complete ');
          this.isLoading = false;
        },
      });
  }


  uploadApprovalFile(event:any):void{

    this.spinner=true;
var formData=new FormData();
formData.append("uploadFile",event.target.files[0]);
    this.restClient.uploadFile(environment.UPLOAD_FOR_CI_APPROVAL+"?type="+this.type, formData).subscribe({
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
      },
      error: (err: any) => {
        console.log(err);

        let snackBarRef = this.snackBar.open('Process Failed', 'close', {
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          duration: 3000,
        });
        this.spinner = false;
      },
      complete: () => {
        event.target.value = '';
        event.target.files = null;
        this.spinner = false;
      },
    });
  }

}
export interface CiDataApprovalList {
  selected: boolean;
  consumerNo: string;
  mobile: string;
  createdDate: string;
  longitudeLatitude: string;
  signaleStrength: string;
  discom: string;
  division: string;
  subDivision: string;
  subStation: string;
  feeder: string;
  dtr: string;
  pole: string;
  meterNo: string;
  address: string;
  // status: string;
  remarks: string;
}

export interface CIDataForEdit {
  consumerNo: string;
  name: string;
  mobile: string;
  address: string;
  discom: string;
  division: string;
  subDivision: string;
  subStation: string;
  feeder: string;
  dtr: string;
  pole: string;
  metersOnPole: number;
  longitudeLatitude: string;
  networkOperator: string;
  signalStrength: string;
  connectionType: string;
  meterNo: string;
  meterType: string;
  meterMake: string;
  meterReading: string;
  meterBoxStatus: string;
  meterSealStatus: string;
  agencyVerified: string;
  verifiedBy: string;
  verifiedDate: Date;
  remarks: string;
  photoBase64: string;
}
export interface EnhDropdownResponse {
  state: string;
  stateName: string;
  discom: string;
  discomName: string;
  city: string;
  cityName: string;
  division: string;
  divisionName: string;
  subDivision: string;
  subDivisionName: string;
  subStation: string;
  subStationName: string;
  feeder: string;
  feederName: string;
}

export interface ApprovalDropdownData {
  name: string;
  value: string;
}
