import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { APIResponse, HttpclientService, PageableResponse } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

import { saveAs } from 'file-saver';
import { MatSelectChange } from '@angular/material/select';
@Component({
  selector: 'app-enh-creation',
  templateUrl: './enh-creation.component.html',
  styleUrls: ['./enh-creation.component.css']
})
export class EnhCreationComponent implements OnInit {
  selectedIndex = 0;
  spinner = false;
  fetchUrl: string = '';
  // for tab 1
  isLoading = false;
  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns: string[] = ['state', 'discom', 'zone', 'city', 'circle', 'division', 'subDivision'];
  // dataSource = new MatTableDataSource<UserElement>(ELEMENT_DATA);
  dataSource = new MatTableDataSource<OrgNetwork>();

  orgData:OrgNetwork;

  filtered: boolean = false;

  isDetailsEditable: boolean = true;
  isNavigation: boolean = false;
  subStationList:any;

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) {}


  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.loadData();
    // this.isDetailsEditable=this.authservice.checkPermission(roleType.ADMIN,roleConfig.UserCreation) || this.authservice.checkPermission(roleType.MANAGER,roleConfig.UserCreation);

  }

  loadData() {
    this.isLoading = true;
    this.filtered = false;
    var queryParams: any = {};

    this.restClient
      .getwithParam(environment.ENH_LIST_API, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          var content: OrgNetwork[] = result.data as OrgNetwork[];
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
          console.log('ENH List Fetch Complete ');
          this.isLoading = false;
        },
      });
  }
  fetchFilteredDataInTable(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onChangePage(pe: PageEvent) {
    this.pageSize = pe.pageSize;
    this.currentPage = pe.pageIndex;
  }

  showDetails(row: any) {
    // this.spinner = true;
//
  //   var queryParams: any = {};
  //   queryParams['user'] = row.userName;
  //  this.restClient
  //     .getwithParam(
  //       environment.FETCH_USER_DATA_API,queryParams
  //     )
  //     .subscribe({
  //       next: (result: APIResponse) => {
  //         // this.userDetail= result.data as UserData;
  //         this.isNavigation = true;
  //         this.selectedIndex = 1;

  //       },
  //       error: (err: any) => {
  //         console.log(err);
  //                let snackBarRef = this.snackBar.open('Server Error', 'close', {
  //                   horizontalPosition: 'center',
  //                   verticalPosition: 'bottom',
  //                   duration: 3000,
  //                 });
  //         this.spinner = false;
  //       },
  //       complete: () => {
  //         console.log('User Data Fetch Complete ');
  //         this.spinner = false;
  //       },
  //     });
  this.isNavigation = true;
  this.selectedIndex = 1;
  this.orgData=row;
  }



  exportToExcel():void{

    this.spinner = true;
    this.restClient
        .downloadusingGet(environment.ENH_EXPORT_TO_EXCEL_API,{})
        .subscribe({
          next: (result: any) => {

            this.spinner = false;

              var filename="New ENH Data List.xlsx";

              saveAs(result,filename);

          },
          error: (err: any) => {

            let snackBarRef = this.snackBar.open("Export Failed", 'close', {
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
  exportExistingToExcel():void{

    this.spinner = true;
    this.restClient
        .downloadusingGet(environment.ENH_EXPORT_EXISINTG_TO_EXCEL_API,{})
        .subscribe({
          next: (result: any) => {

            this.spinner = false;

              var filename="Exisitng ENH Data List.xlsx";

              saveAs(result,filename);

          },
          error: (err: any) => {

            let snackBarRef = this.snackBar.open("Export Failed", 'close', {
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
    this.selectedIndex = 0;
    this.loadData();
  }



}

export interface OrgNetwork {
  state:           string;
  stateName:       string;
  discom:          string;
  discomName:      string;
  zone:            string;
  zoneName:        string;
  city:            string;
  cityName:        string;
  circle:          string;
  circleName:      string;
  division:        string;
  divisionName:    string;
  subDivision:     string;
  subDivisionName: string;
}
