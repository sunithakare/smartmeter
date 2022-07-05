import { EnhDtrDetailsComponent } from './enh-dtr-details/enh-dtr-details.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { APIResponse, HttpclientService, PageableResponse } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-enh-dtr-list',
  templateUrl: './enh-dtr-list.component.html',
  styleUrls: ['./enh-dtr-list.component.css']
})
export class EnhDtrListComponent implements OnInit {
  selectedIndex=0;
  spinner = false;
  //dataSource: any;
  fetchUrl: string = '';
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

  displayedColumns: string[] = ['name','code','location','landmark','feeder'];
  // dataSource = new MatTableDataSource<UserElement>(ELEMENT_DATA);
  dataSource = new MatTableDataSource<EnhDtrData>();
  EnhDtrDetailsComponent: EnhDtrData= {
    feeder:'',
    code:'',
    location:'',
    dtrRating:'',
    name : '',
    landmark :'',
    division :'',


  };

  codeSearch:string='';
  nameSearch:string='';

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) { }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.loadData();
    this.isEditable=true;
  }

  fetchFilteredDataInTable(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  showDetails(row: any) {
  //   this.spinner=true;

  //   var queryParams: any = {};
  //   //queryParams['agencyName']= row.agencyName;
  //   queryParams['code'] = row.code;

  //   this.restClient.getwithParam(
  //     environment.GET_DTR_DETAILS_BY_CODE,
  //     queryParams
  //   )
  //   .subscribe({
  //     next: (results: APIResponse) =>{
  //       this.EnhDtrDetailsComponent = results.data as EnhDtrData;

  //     this.isNavigation =true;
  //     this.selectedIndex = 1;
  //   },
  //   error: (err: any) => {
  //     console.log(err);
  //     let snackBarRef = this.snackBar.open('Server Error', 'close', {
  //       horizontalPosition: 'center',
  //       verticalPosition: 'bottom',
  //       duration: 3000,
  //  });
  //  this.spinner =false ;
//   },
//   complete: () => {
//     console.log('User Creation Fetch Complete ');
//     this.spinner = false;
//   },
// });

this.EnhDtrDetailsComponent = row as EnhDtrData;

this.isNavigation =true;
this.selectedIndex = 1;
  }
  onChangePage(pe: PageEvent) {
    this.pageSize = pe.pageSize;
    this.currentPage = pe.pageIndex;
    if (this.filtered) {
      this.fetchFilteredData();
    } else {
      this.loadData();
    }


}loadData() {
  this.isLoading = true;
  this.filtered = false;
  var queryParams: any = {};
  queryParams['page'] = this.currentPage;
  queryParams['size'] = this.pageSize;

  this.restClient
    .getwithParam(environment.GET_ALL_DTR_DATA,
     queryParams)
    .subscribe({
      next: (result: PageableResponse) => {



        var content: EnhDtrData[] = result.data.content as EnhDtrData[];

        //this.dataSource.data = content;
        this.dataSource.data=content;
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
  fetchFilteredData() : void{
    this.isLoading = true;
    this.filtered =true;

    var queryParams: any = {};
    queryParams['page']=this.currentPage;
    queryParams['size']=this.pageSize;
    if(this.codeSearch!=''){
      queryParams['code']=this.codeSearch;
    }


    if(this.nameSearch!=''){
      queryParams['name']=this.nameSearch;
    }

    this.restClient.getwithParam(environment.FILTER_DTR_DATA, queryParams)
   .subscribe({
     next: (result:PageableResponse)=>{
       var content: EnhDtrData[] = result.data.content as EnhDtrData[];
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
  loadListTab() {
    this.selectedIndex = 0;
    this.loadData();
  }
  addNewDtr(){
    this.EnhDtrDetailsComponent={
      feeder: '',
      code:'',
      location:'',
      dtrRating:'',
      name : '',
      landmark :'',
      division : '',
    }

    this.isNavigation = false;
    this.selectedIndex = 1;

  }
  searchWithFilter() {
    this.currentPage = 0;
    this.fetchFilteredData();
  }
}

export interface EnhDtrData{
  feeder:string;
    code:string;
    location:string;
    dtrRating:string;
    name : string;
    landmark :string;
    division : string;

}
