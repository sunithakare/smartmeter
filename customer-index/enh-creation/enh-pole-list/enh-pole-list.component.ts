import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { APIResponse, HttpclientService, PageableResponse } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-enh-pole-list',
  templateUrl: './enh-pole-list.component.html',
  styleUrls: ['./enh-pole-list.component.css']
})
export class EnhPoleListComponent implements OnInit {

  code:string='';
  name:string='';
  selectedIndex = 0;
  spinner = false;
  isLoading = false;
  isNavigation: boolean = false;
  isEditable: boolean = false;
  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  filtered=false;

  displayedColumns: string[]=['name','code','poleType','location','landmark','feeder','dtr','division'];

  EnhPoleRsponse:EnhPoleData={
    name:'',
    code:'',
    poleType:'',
    landmark:'',
    location:'',
    division:'',
    feeder:'',
    dtr:'',
 };
  dataSource = new MatTableDataSource<any>();

  constructor(
    private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService
  ) {
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.loadPoleData();
    this.isEditable=true;
  }

  onChangePage(pe: PageEvent) {
    this.pageSize = pe.pageSize;
    this.currentPage = pe.pageIndex;
    if (this.filtered) {
      this.fetchFilteredData();
    } else {
      this.loadPoleData();
    }
  }



  searchWithFilter() {
    this.currentPage = 0;
    this.fetchFilteredData();
  }

  fetchFilteredData():void {
    this.isLoading = true;
    this.filtered = true;

    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;
    // queryParams['city'] = this.city;
    queryParams['code'] = this.code;
    queryParams['name'] = this.name;

    this.restClient
      .getwithParam(
        environment.ENH_POLE_FILTER_API,
        queryParams
      )
      .subscribe({
        next: (result: PageableResponse) => {
          var content: EnhPoleData[] = result.data.content as EnhPoleData[];
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

  loadPoleData() {
    this.isLoading = true;
    this.filtered = false;
    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;

    this.restClient
      .getwithParam(environment.ENH_POLE_FETCH_API, queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          var content: EnhPoleData[] = result.data.content as EnhPoleData[];
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
          console.log('Enh pole successful ');
          this.isLoading = false;
        },
      });
  }

  showPoleDetails(row: any) {
//      this.spinner = true;

//     var queryParams: any ={};
//     queryParams['code']=row.code;

//     this.restClient.getwithParam(environment.ENH_POLE_NAVIGATIONLISTTODETAILS_API,queryParams)
//     .subscribe({
//       next:(results:APIResponse) =>{
//       },
//       error:(err:any) => {
//         console.log(err);
//         let snackBarRef = this.snackBar.open('server error' ,'close',{
//           horizontalPosition:'center',
//          verticalPosition:'bottom',
//          duration :3000,
//                });
// this.spinner = false;
//       },
//       complete: () => {
//         console.log('pole List Fetch Complete');
//          this.spinner =false;
//       },
//      });


this.EnhPoleRsponse =row as EnhPoleData;


this.isNavigation= true;
this.selectedIndex= 1;
     }

     addNewPole(){
       this.EnhPoleRsponse={
        name:'',
        code:'',
        poleType:'',
        landmark:'',
        location:'',
        division:'',
        feeder:'',
        dtr:'',
       };
       this.isNavigation=false;
      this.selectedIndex = 1;

     }
     loadListTab() {
      this.selectedIndex = 0;
      this.loadPoleData();
     }
}
export interface EnhPoleData{
name:string;
code:string;
poleType:string;
location:string;
landmark:string;
feeder:string;
dtr:string;
division:string;
}
