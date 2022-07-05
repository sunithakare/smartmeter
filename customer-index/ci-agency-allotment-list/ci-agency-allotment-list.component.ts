import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSelectChange } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { saveAs } from 'file-saver';
import {
  APIResponse,
  ConfigCode,
  HttpclientService,
  PageableResponse,
} from 'src/app/services/httpclient.service';
import { environment, roleConfig, roleType } from 'src/environments/environment';
import { EnhStateList, EnhDiscomList, EnhCityList, EnhDivisionList } from './ci-agency-allotment-details/ci-agency-allotment-details.component';

@Component({
  selector: 'app-ci-agency-allotment-list',
  templateUrl: './ci-agency-allotment-list.component.html',
  styleUrls: ['./ci-agency-allotment-list.component.css'],
})
export class CiAgencyAllotmentListComponent implements OnInit {
  isEditable: boolean = false;
  agencyDetails: AgencyDetails | undefined = {
    allocationData: '',
    allocation: '',
    divisionCounts: '',
    city: '',
    division: [''],
    agencyCode: '',
    state: '',
    discom: '',
    //         permissionsList: [],
    // asignedPermission: [],
  };

  division: string='';
  agencyCode: string='';
  state: string='';
  discom: string='';
  city: string='';

  stateList: EnhStateList[] = [];
  allDiscomList: EnhDiscomList[] = [];
  allCityList:EnhCityList[]=[];

  selectedIndex = 0;
  spinner = false;
  isLoading = false;

  isNavigation: boolean = false;
  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  filtered = false;

  displayedColumns: string[] = [
    'state',
    'discom',
    // 'city',
    'division',
    'agency',
  ];
  // dataSource = new MatTableDataSource<UserElement>(ELEMENT_DATA);
  dataSource = new MatTableDataSource<AgencyAllotmentData>();

  agencyCodeSearch: string = '';

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) {

    this.isEditable=this.authservice.checkPermission(roleType.ADMIN,roleConfig.AgencyAllotment) || this.authservice.checkPermission(roleType.MANAGER,roleConfig.AgencyAllotment);


  }

  ngOnInit(): void {


    this.dataSource.paginator = this.paginator;
    this.loadData();
    this.fetchStateList();
  }

  onChangePage(pe: PageEvent) {
    this.pageSize = pe.pageSize;
    this.currentPage = pe.pageIndex;
    if (this.filtered) {
      this.fetchFilteredData();
    } else {
      this.loadData();
    }
  }

  searchWithFilter() {
    this.currentPage = 0;
    this.fetchFilteredData();
  }
  ngAfterViewInit() {
    // this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }



  fetchFilteredData() {
    this.isLoading = true;
    this.filtered = true;

    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;
    queryParams['city'] = this.city;
    queryParams['state'] = this.state;
    queryParams['discom'] = this.discom;
    queryParams['division'] = this.division;
    queryParams['agency'] = this.agencyCode;

    this.restClient
      .getwithParam(
        environment.CIA_FILTER_AGENCY_ALLOTMENT_DETAILS,
        queryParams
      )
      .subscribe({
        next: (result: PageableResponse) => {
          var content: AgencyAllotmentData[] = result.data
            .content as AgencyAllotmentData[];

          this.dataSource.data = content;
          this.totalRows = result.data.totalElements;
          if (content.length == 0) {
            let snackBarRef = this.snackBar.open(
              'No Data Found For the Filter',
              'close',
              {
                horizontalPosition: 'center',
                verticalPosition: 'bottom',
                duration: 3000,
              }
            );
          }
        },
        error: (err: any) => {
          console.log(err);
          this.isLoading = false;
        },
        complete: () => {
          console.log('complete');
          this.isLoading = false;
        },
      });
  }
  allotData!: any;

  loadData(): void {
    this.isLoading = true;
    this.filtered = false;
    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;

    this.restClient
      .getwithParam(environment.CIA_FIND_CI_AGENCY_DATA, queryParams)
      .subscribe({
        next: (result: PageableResponse) => {
          var content: AgencyAllotmentData[] = result.data
            .content as AgencyAllotmentData[];

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
          console.log('Role List Fetch Complete ');
          this.isLoading = false;
        },
      });
  }

  showDetails(row: any) {
    this.spinner = true;
    var queryParams: any = {};
    queryParams['agency'] = row.agencyCode;
    queryParams['allocationData'] = row.allocationData;

    this.restClient
      .getwithParam(environment.CIA_FIND_BY_AGENCY, queryParams)
      .subscribe({
        next: (res: APIResponse) => {
          this.agencyDetails = res.data as AgencyDetails;

          this.isNavigation = true;
          this.selectedIndex = 1;
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
          console.log('fetching by agency success');
          this.spinner = false;
        },
      });
  }

  addNewAgency(): void {
    this.agencyDetails = undefined;
    this.isNavigation = false;
    this.selectedIndex = 1;
  }
  loadListTab() {
    this.selectedIndex = 0;
    this.loadData();
  }



  fetchStateList() {
    this.spinner = true;

    var queryParams: any = {};
    queryParams['type'] = 'STATE';

    this.restClient
      .getwithParam(environment.FETCH_ALL_STATE_IN_ENH, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          this.stateList = result.data as EnhStateList[];
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {
          console.log('State List Fetch Complete ');

          this.spinner = false;
        },
      });
  }
  fetchDiscomInStateforChange(event:MatSelectChange):void{
    this.fetchDiscomFromState(event.value);
  }
  fetchDiscomFromState(state: any) {
    this.spinner = true;
    var queryParams: any = {};
    queryParams['state'] = state;
    this.restClient
      .getwithParam(
        environment.FETCH_ALL_DISCOM_IN_STATE_ENH,
        queryParams
      )
      .subscribe({
        next: (result: APIResponse) => {
          this.allDiscomList = result.data as EnhDiscomList[];
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {
          console.log('state 2 fetched..!');

          this.spinner = false;
        },
      });
  }

  fetchCityInDiscomforChange(event:MatSelectChange):void{
    this.fetchcityFromDiscom(event.value);
  }
  fetchcityFromDiscom(discom: any) {
    this.spinner = true;
    var queryParams: any = {};
    queryParams['discom'] = discom;
    this.restClient
      .getwithParam(
        environment.FETCH_ALL_CITY_IN_DISCOM_IN_ENH,
        queryParams
      )
      .subscribe({
        next: (result: APIResponse) => {
          this.allCityList = result.data as EnhCityList[];
        },
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {

          this.spinner = false;
        },
      });
  }



  exportToExcel(): void {
    this.spinner = true;



    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;
    queryParams['city'] = this.city;
    queryParams['state'] = this.state;
    queryParams['discom'] = this.discom;
    queryParams['division'] = this.division;
    queryParams['agency'] = this.agencyCode;


    this.restClient
      .downloadusingGet(environment.CIA_AGENCY_EXPORT_TO_EXCEL,queryParams)
      .subscribe({
        next: (result: any) => {
          this.spinner = false;

          var filename = 'Agency Allotement Export.xlsx';

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



}

export interface AgencyDetails {
  allocationData: string;
  allocation: string;
  divisionCounts: string;
  city: string;
  division: string[];
  agencyCode: string;
  state: string;
  discom: string;
  // permissionsList:SystemAccess[],
  // asignedPermission:SystemAccess[],
}

export interface AgencyAllotmentData {
  state: string;
  discom: string;
  city: string;
  division: string;
  agencyCode: string;
}
