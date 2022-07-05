
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { HttpclientService, PageableResponse ,APIResponse } from 'src/app/services/httpclient.service';
import { environment, roleConfig, roleType } from 'src/environments/environment';
import { FieldUserCreationData, UserDetail } from '../ci-field-user-list/ci-field-user-list.component';

import { saveAs } from 'file-saver';

@Component({
  selector: 'app-ci-supervisor-list',
  templateUrl: './ci-supervisor-list.component.html',
  styleUrls: ['./ci-supervisor-list.component.css']
})
export class CiSupervisorListComponent implements OnInit {


  selectedIndex = 0;
  spinner = false;
  isLoading = false;
  isNavigation: boolean = false;
  isEditable:boolean=false;
  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  filtered=false;


  displayedColumns: string[] = ['agencyName','fieldUserCode', 'fieldUserName',  'mobile','status'];
  // dataSource = new MatTableDataSource<UserElement>(ELEMENT_DATA);
  dataSource = new MatTableDataSource<any>();



  fieldUserNameSearch:string='';
  agencyName :string='';

  UserDetailsResponse : UserDetail ={
    agencyCode: '',
    mobile : '',
    userId: '',
    userCode: '',
    status : false,
    userFirstName : '',
    userLastName : '',
    email : '',
    password : '',
    idProof : '',
    fileId : '',

  };


  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) { }

  ngOnInit(): void {

    this.dataSource.paginator = this.paginator;
    this.loadData();
    this.isEditable=this.authservice.checkPermission(roleType.ADMIN,roleConfig.CISupervisorCreation) || this.authservice.checkPermission(roleType.MANAGER,roleConfig.CISupervisorCreation);

    // this.getStatus();
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







  fetchFilteredData():void{

    this.isLoading = true;
    this.filtered = true;

    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;
    queryParams['userName'] = this.fieldUserNameSearch;
    queryParams['agencyName'] = this.agencyName;


    this.restClient
      .getwithParam(environment.FETCH_SUPERVISOR_USER_DATA_WITH_FILTER,
        queryParams
      )
      .subscribe({
        next: (result: PageableResponse) => {
          var content: FieldUserCreationData[] = result.data.content as FieldUserCreationData[];

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
          let snackBarRef = this.snackBar.open(
            'No Data Found For the Filter',
            'close',
            {
              horizontalPosition: 'center',
              verticalPosition: 'bottom',
              duration: 3000,
            }
          );
          this.isLoading = false;
        },
        complete: () => {
          console.log('complete');
          this.isLoading = false;
        },
      });


  }




  ngAfterViewInit() {
    // this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


 // loadData():void{
//this.dataSource.data=[]
 // }





 loadData() {
  this.isLoading = true;
  this.filtered = false;
  var queryParams: any = {};
  queryParams['page'] = this.currentPage;
  queryParams['size'] = this.pageSize;

  this.restClient
    .getwithParam(environment.FETCH_SUPERVISOR_USER_DATA,
     queryParams)
    .subscribe({
      next: (result: PageableResponse) => {



        var content: FieldUserCreationData[] = result.data.content as FieldUserCreationData[];

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
        console.log('FieldUserCreationData List Fetch Complete ');
        this.isLoading = false;
      },
    });
}


  showDetails(row: any) {
    this.spinner=true;

    var queryParams: any = {};
    //queryParams['agencyName']= row.agencyName;
    queryParams['UserId'] = row.fieldUserName;

    this.restClient.getwithParam(
      environment.SUPERVISOR_USER_CREATION_DETAILS_API,
      queryParams
    )
    .subscribe({
      next: (results: APIResponse) =>{
        this.UserDetailsResponse = results.data as UserDetail;

      this.isNavigation =true;
      this.selectedIndex = 1;
    },
    error: (err: any) => {
      console.log(err);
      let snackBarRef = this.snackBar.open('Server Error', 'close', {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 3000,
   });
   this.spinner =false ;
  },
  complete: () => {
    console.log('User Creation Fetch Complete ');
    this.spinner = false;
  },
});
  }

  addNewAgency(){
    this.UserDetailsResponse = {
      agencyCode: '',
      mobile : '',
      userId: '',
      userCode: '',
      status : false,
      userFirstName : '',
      userLastName : '',
      email : '',
      password : '',
      idProof : '',
      fileId : '',
    };


    this.isNavigation = false;
    this.selectedIndex = 1;
  }
  loadListTab() {
    this.selectedIndex = 0;
    this.loadData();
  }

  exportToExcel(): void {
    this.spinner = true;

    var queryParams: any = {};

    queryParams['userName'] = this.fieldUserNameSearch;
    queryParams['agencyName'] = this.agencyName;


    this.restClient
      .downloadusingGet(environment.SUPERVISOR_EXPORT_TO_EXCEL,queryParams)
      .subscribe({
        next: (result: any) => {
          this.spinner = false;

          var filename = 'Supervisor Data Export.xlsx';

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

