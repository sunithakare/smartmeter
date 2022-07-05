import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { HttpclientService, PageableResponse, APIResponse } from 'src/app/services/httpclient.service';
import { environment, roleConfig, roleType } from 'src/environments/environment';

import { saveAs } from 'file-saver';
@Component({
  selector: 'app-ci-agency-list',
  templateUrl: './ci-agency-list.component.html',
  styleUrls: ['./ci-agency-list.component.css']
})
export class CiAgencyListComponent implements OnInit {
  selectedIndex = 0;
  spinner = false;
  fetchUrl: string ='';//for checking
  isLoading = false;
  isNavigation: boolean = false;
  isEditable: boolean = false;
  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  // @ViewChild(MatSort) sort: MatSort;
  filtered=false;

  displayedColumns: string[] = [ 'agencyCode', 'agencyName', 'agencyManager', 'mobile','status'];

  // dataSource = new MatTableDataSource<UserElement>(ELEMENT_DATA);
  dataSource = new MatTableDataSource<any>();

  CIAgencyDetailsResponse: AgencyDetailsResponse ={
    agencyCode:'',
      agencyManager:'',
      agencyName:'',
      externalId:'',
      agencyType:[],
      mobile:'',
      landmark:'',
      email:'',
     status:false,
  };
  agencyCodeSearch : string='';
  agencyTypeSearch : string='';
  agencyNameSearch : string='';
  agencyManager : string='';
  mobile:string='';
  status : string='Active';

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) { }

  ngOnInit(): void {

    this.dataSource.paginator = this.paginator;
    this.loadData();
    this.isEditable=this.authservice.checkPermission(roleType.ADMIN,roleConfig.AgencyCreation) || this.authservice.checkPermission(roleType.MANAGER,roleConfig.AgencyCreation);

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
    this.filtered =true;

    var queryParams: any = {};
    queryParams['page']=this.currentPage;
    queryParams['size']=this.pageSize;
    if(this.agencyCodeSearch!=''){
      queryParams['agencyCode']=this.agencyCodeSearch;
    }


    if(this.agencyTypeSearch!=''){
      queryParams['agencyType']=this.agencyTypeSearch;
    }

    if(this.agencyNameSearch!=''){
      queryParams['agencyName']=this.agencyNameSearch;
    }

    if(this.agencyManager!=''){
      queryParams['agencyManager']=this.agencyManager;
    }
    if(this.mobile!=''){
      queryParams['mobile']=this.mobile;
    }
    if(this.status!=''){
      queryParams['status']=this.status;
    }



    this.restClient.getwithParam(environment.FILTER_AGENCY_API, queryParams)
   .subscribe({
     next: (result:PageableResponse)=>{
       var content: AgencyListDetails[] = result.data.content as AgencyListDetails[];
       this.dataSource.data=content;
       this.totalRows=result.data.totalElements;
       if(content.length == 0){
         let snackBarRef=this.snackBar.open(
           'No data found here for filter','close',
           {
             horizontalPosition:'center',
             verticalPosition:'bottom',
             duration:2000,
           }
         );
       }
     },
     error:(err:any)=>{
       console.log(err);
       this.isLoading=false;
     },
     complete:()=>{
       console.log('successful');
       this.isLoading=false;
     },
   });

  }

  ngAfterViewInit() {
    // this.dataSource.paginator = this.paginator;
    // this.dataSource.sort = this.sort;
  }


  loadData() {
    this.isLoading = true;
    this.filtered = false;
    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;

    this.restClient
      .getwithParam(environment.FETCH_All_AGENCY_API, queryParams)
      .subscribe({
        next: (result: PageableResponse) => {
          var content: AgencyListDetails[] = result.data.content as AgencyListDetails[];

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
          console.log('Agency Creation successful ');
          this.isLoading = false;
        },
      });
  }


// navigation list to details page
  showAgencyDetails(row: any) {
     this.spinner = true;

     var queryParams: any ={};
    //  queryParams['agencyType']=row.agencyType;
     queryParams['agencyCode']=row.agencyCode;

     this.restClient.getwithParam(environment.LIST_TO_DETAILS_NAVIGATION_AGENCY_CREATION_API,queryParams)
     .subscribe({
       next:(results:APIResponse) =>{
        //  let CIAgencyData =results.data as AgencyDetailsResponse;
         this.CIAgencyDetailsResponse =results.data as AgencyDetailsResponse;


         this.isNavigation= true;
         this.selectedIndex= 1;
       },
       error:(err:any) => {
         console.log(err);
         let snackBarRef = this.snackBar.open('server error' ,'close',{
           horizontalPosition:'center',
          verticalPosition:'bottom',
          duration :3000,
                });
this.spinner = false;
       },
       complete: () => {
         console.log('Agency List Fetch Complete');
         this.spinner =false;
       },
      });
  }


  addNewAgency() {
    this.CIAgencyDetailsResponse={

      agencyCode:'',
      agencyManager:'',
      agencyName:'',
      externalId:'',
      agencyType:[],
      mobile:'',
      landmark:'',
      email:'',
      status:false,

    };
 this.isNavigation=false;
   this.selectedIndex=1;

}


loadListTab() {
  this.selectedIndex = 0;
  this.loadData();
}


exportToExcel(): void {
  this.spinner = true;

  var queryParams: any = {};
  if(this.agencyCodeSearch!=''){
    queryParams['agencyCode']=this.agencyCodeSearch;
  }


  if(this.agencyTypeSearch!=''){
    queryParams['agencyType']=this.agencyTypeSearch;
  }

  if(this.agencyNameSearch!=''){
    queryParams['agencyName']=this.agencyNameSearch;
  }

  if(this.agencyManager!=''){
    queryParams['agencyManager']=this.agencyManager;
  }
  if(this.mobile!=''){
    queryParams['mobile']=this.mobile;
  }
  if(this.status!=''){
    queryParams['status']=this.status;
  }


  this.restClient
    .downloadusingGet(environment.EXPORT_AGENCY_API,queryParams)
    .subscribe({
      next: (result: any) => {
        this.spinner = false;

        var filename = 'Agency Data Export.xlsx';

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

// export interface AgencyData{
//   agencyType:string;
//   agencyCode:string;
//   agencyName:string;
//   agencyManager:string;
//   landmark : string;
//   mobile:string;
//   email : string;
//   status:boolean;
// }
// export interface AgencyDataSearch{
//   agencyType? : string;
//   agencyCode? : string;
//   agencyName? : string;
//   agencyManager? : string;
//   email? : string;
//   landmark? : string;
//   status? : boolean;
//   mobile? : string;
// }

export interface AgencyDetailsResponse{
  agencyType:string[];
  agencyCode:string;
  agencyName:string;
  externalId:string;
  agencyManager:string;
  mobile:string;
  email : string;
  landmark : string;
  status: boolean;
}

export interface AgencyListDetails{
   agencyCode:string;
    agencyType:string;
    agencyManager:string;
    agencyName:string;
    mobile:string;
    email : string;
    landmark :string;
    status : boolean;
}
