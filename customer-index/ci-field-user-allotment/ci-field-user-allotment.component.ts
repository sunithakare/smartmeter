import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { HttpclientService, PageableResponse, APIResponse, ConfigCode } from 'src/app/services/httpclient.service';
import { environment, roleConfig, roleType} from 'src/environments/environment';
import { MatSelectChange } from '@angular/material/select';
@Component({
  selector: 'app-ci-field-user-allotment',
  templateUrl: './ci-field-user-allotment.component.html',
  styleUrls: ['./ci-field-user-allotment.component.css']
})
export class CiFieldUserAllotmentComponent implements OnInit {

  selectedIndex = 0;
  spinner = false;
  isLoading = false;
  isDetailsEditable: boolean = false;
  isNavigation: boolean = false;
  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  filtered=false;

  displayedColumns: string[] = ['state', 'discom', 'city','agency',   'fieldUser','allocationDatatype','allottedDivision'];
  // dataSource = new MatTableDataSource<UserElement>(ELEMENT_DATA);
  dataSource = new MatTableDataSource<any>();

  detailResponse: FieldUserAllotmentDetails = {
    mobile:'',
    userId: '',
    userName: '',
    divisionList:[],
    agency:'',
    allocationDatatype:'',
   allocation: '',
  };

searcher:any={
  userId:"",
  division:""
};

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) { }

  ngOnInit(): void {

    this.dataSource.paginator = this.paginator;
    this.loadData();
    this.isDetailsEditable=this.authservice.checkPermission(roleType.ADMIN,roleConfig.CIFieldUserAllotment) || this.authservice.checkPermission(roleType.MANAGER,roleConfig.CIFieldUserAllotment);


  }
  ngAfterViewInit() {
    // this.dataSource.paginator = this.pagina tor;
    this.dataSource.sort = this.sort;
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

  fetchFilteredData(){
    this.isLoading=true;
    this.filtered=true;

    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;
    queryParams['divisionId'] = this.searcher.division;
    queryParams['userId'] = this.searcher.userId;


    this.restClient
    .getwithParam(
      environment.FETCH_DATA_BY_FILTER,
      queryParams
    )
    .subscribe({
      next: (result: PageableResponse) => {
        var content: FieldUserAllotmentList[] = result.data.content as FieldUserAllotmentList[];

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


  loadData():void{
    this.isLoading = true;
    this.filtered = false;
    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;

    this.restClient
      .getwithParam(environment.FETCH_CI_USER_ALLOTMENT, queryParams)
      .subscribe({
        next: (result: PageableResponse) => {
          var content: FieldUserAllotmentList[] = result.data.content as FieldUserAllotmentList[];

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
          console.log('FieldUserAllotment List Fetch Complete ');
          this.isLoading = false;
        },
      });
  }

 //navigating the data from list to details page
  showDetails(row: any) {
     this.spinner = true;

     var queryParams: any ={};
     queryParams['userId'] = row.userId;
     queryParams['type'] = row.allocationDatatype;

     this.restClient.getwithParam(environment.FETCH_FIELD_USER_ALLOTMENT_DETAILS_API, queryParams)
     .subscribe({
       next: (result: APIResponse) => {
          this.detailResponse = result.data as FieldUserAllotmentDetails;
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
         console.log('Agency List Fetch Complete ');
         this.spinner = false;
       }
     });
  }

  loadListTab() {
    this.selectedIndex = 0;
    this.loadData();
  }
  addNewFieldUser():void{
    this.isDetailsEditable=true;
    this.detailResponse = {
      mobile:'',
      userId: '',
      userName: '',
      divisionList:[],
      agency:'',
      allocationDatatype:'',
     allocation: '',
    };

    this.isNavigation = false;
    this.selectedIndex = 1;
  }
}
export interface FieldUserAllotmentList{
  stateId?:string;
  discomId?:string;
  city?:string;
  agency?:string;
  userId?:string;
  allottedDivision?: string;
  allocationDatatype?: string;
}

export interface FieldUserAllotmentDetails{
  userName:string;
  mobile:String;
  userId: string;
  divisionList: string[];
  agency: string;
  allocation: string;
  allocationDatatype: string;
}
