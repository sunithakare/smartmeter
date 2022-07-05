import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { APIResponse, HttpclientService, PageableResponse } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-enh-sub-station',
  templateUrl: './enh-sub-station.component.html',
  styleUrls: ['./enh-sub-station.component.css']
})
export class EnhSubStationComponent implements OnInit {

  selectedIndex = 0;
  isLoading = false;
  filtered = false;
  isNavigation: boolean = false;
  isEditable: boolean = false;
  currentPage = 0;
  pageSize = 15;
  totalRows = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  spinner = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<any>();

  displayedColumns: string[] = ['name','code','location','landmark','division'];

  subStationCodeSearch : string='';
  subStationNameSearch : string='';

  enhSubStationDetailsResponse: SubStationListData={
    name:'',
    code:'',
    location:'',
    landmark:'',
    division:'',
  };

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
    if(this.subStationCodeSearch!=''){
      queryParams['code']=this.subStationCodeSearch;
    }
    if(this.subStationNameSearch!=''){
      queryParams['name']=this.subStationNameSearch;
    }

    this.restClient.getwithParam(environment.FILTER_SUBSTATION_API, queryParams)
   .subscribe({
     next: (result:PageableResponse)=>{
       var content: SubStationListData[] = result.data.content as SubStationListData[];
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

  loadData(){
    this.isLoading = true;
    this.filtered = false;
    var queryParams: any = {};
    queryParams['page'] = this.currentPage;
    queryParams['size'] = this.pageSize;

    this.restClient
    .getwithParam(environment.FETCH_ALL_SUBSTATION_API, queryParams)
    .subscribe({
      next: (result: PageableResponse) => {
        var content: SubStationListData[] = result.data.content as SubStationListData[];

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
        console.log('SubStation Creation successful ');
        this.isLoading = false;
      },
    });

  }

  showSubStationDetails(row: any){
    this.enhSubStationDetailsResponse =row as SubStationListData;

    this.isNavigation= true;
    this.selectedIndex= 1;
  }

   addNewSubStation(){
     this.enhSubStationDetailsResponse={
       name:'',
       code:'',
       location:'',
       landmark:'',
       division:'',

     };
     this.isNavigation=false;
     this.selectedIndex=1;
   }

  loadListTab() {
    this.selectedIndex = 0;
    this.loadData();
  }

}

// export interface SubStationDetailsResponse{
//   name:string;
//   code:string;
//   location:string;
//   landmark:string;
//   division:string;
// }

export interface SubStationListData{
  name:string;
  code:string;
  location:string;
  landmark:string;
  division:string;
}
