import { Component, OnInit } from '@angular/core';

import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/services/auth.service';
import { APIResponse, HttpclientService } from 'src/app/services/httpclient.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-enh-feeder-list',
  templateUrl: './enh-feeder-list.component.html',
  styleUrls: ['./enh-feeder-list.component.css']
})
export class EnhFeederListComponent implements OnInit {

  code:string='';
  name:string='';
  displayedColumns: string[] = ['subStation', 'division', 'landmark', 'name','code','location'];
  selectedIndex = 0;
  spinner = false;
  enhFeederData: CiEnhFeeder | undefined={
    subStation:'',
  landmark:'',
  division:'',
  name:'',
  code:'',
  location:'',
  }
  // fetchUrl: string = '';
  // for tab 1
  filtered: boolean = false;
  isDetailsEditable: boolean = true;
  isNavigation: boolean = false;

  isLoading = false;
  totalRows = 0;
  pageSize = 15;
  currentPage = 0;
  pageSizeOptions: number[] = [15, 20, 25, 100];
  dataSource = new MatTableDataSource<CiEnhFeeder>();

  constructor(private snackBar: MatSnackBar,
    private restClient: HttpclientService,
    private authservice: AuthService) { }

  ngOnInit(): void {
    this.loadData();
  }

  onChangePage(event:any){
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    if (this.filtered) {
      this.fetchFilteredData();
    } else {
      this.loadData();
    }
  }

loadData(){

  this.isLoading = true;
    this.filtered = false;
    var queryParams: any = {};
    queryParams['page']=this.currentPage;
    queryParams['size']=this.pageSize;

  this.restClient
      .getwithParam(environment.ENH_FEEDER_FINDALL,queryParams)
      .subscribe({
        next: (result: APIResponse) => {
          var content: CiEnhFeeder[] = result.data.content as CiEnhFeeder[];
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
          console.log('ENH Feeder Fetch Complete ');
          this.isLoading = false;
        },
      });
}

loadListTab() {
  this.selectedIndex = 0;
  this.loadData();
}

fetchFilteredData() {
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
      environment.ENH_FEEDER_FLITER_BY_CODE_NAME,
      queryParams
    )
    .subscribe({
      next: (result: APIResponse) => {
        var content: CiEnhFeeder[] = result.data
          .content as CiEnhFeeder[];

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

searchWithFilter(){
  this.currentPage = 0;
  this.fetchFilteredData();
}




showDetails(row:any){
  this.enhFeederData= row as CiEnhFeeder;
  this.isNavigation = true;
  this.selectedIndex = 1;
}

addNewFeeder(){
  this.enhFeederData={
    subStation:'',
  landmark:'',
  division:'',
  name:'',
  code:'',
  location:'',
  }

  this.selectedIndex=1;
}


}

export interface CiEnhFeeder{
  subStation:string;
  landmark:string;
  division:string;
  name:string;
  code:string;
  location:string;
}
