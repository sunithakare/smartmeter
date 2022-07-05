import { Component, OnInit } from '@angular/core';
import { MatDrawer, MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';
@Component({
  selector: 'app-ltapp',
  templateUrl: './ltapp.component.html',
  styleUrls: ['./ltapp.component.css']
})
export class LtappComponent implements OnInit {

  constructor(private router: Router,
    private authservice: AuthService) { }

today:Date=new Date();
  userName:string='';
  topMenuList:menuItems[];
//   sideMenuList:menuItems[]=[
//   {label:"SLA DashBoard",icon:"assignment",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
//                                                                               {label:"All SLA Link",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports",role:"ANY",permission:["All SLA Link"],child:[]},
//                                                                               {label:"HES",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/HES",role:"ANY",permission:["HES"],child:[]},
//                                                                               {label:"MDM-A",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/MDM-A",role:"ANY",permission:["MDM-A"],child:[]},
//                                                                               {label:"MDM-B",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/MDM-B",role:"ANY",permission:["MDM-B"],child:[]},
//                                                                               {label:"CSP",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/CSP",role:"ANY",permission:["CSP"],child:[]},
//                                                                               {label:"INVENTORY",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/INVENTORY",role:"ANY",permission:["INVENTORY"],child:[]},
//                                                                               {label:"HELPDESK",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/HELPDESK",role:"ANY",permission:["HELPDESK"],child:[]},
//                                                                               {label:"PROJECTS-A",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/PROJECT-A",role:"ANY",permission:["PROJECT-A"],child:[]},
//                                                                               {label:"PROJECTS-B",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/PROJECT-B",role:"ANY",permission:["PROJECT-B"],child:[]},
//                                                                               {label:"TSP",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/TSP",role:"ANY",permission:["TSP"],child:[]},
//                                                                             ]
//   },
//   {label:"Operational Analytics",icon:"timeline",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
//                                                                                     {label:"Operational Analytics Reports",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                                     {label:"Critical Dashboard Reports",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},

//                                                                                   ]
//   },
//   {label:"Monitoring Dashboard",icon:"visibility",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[]
//   },
//   {label:"WFM",icon:"work",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
//                                                                   {label:"CIMI",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   {label:"NSC",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   {label:"SM2SM",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   {label:"O&M",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   ]
//   },
//   {label:"Inventory",icon:"shopping_cart",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[]
//   },
//   {label:"User Authorization",icon:"supervisor_account",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[]
//   },
//   {label:"CONFIG",icon:"settings",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
//                                                                   {label:"ROLE CATEGORY",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   {label:"ROLE",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   {label:"SYSTEM ROLE CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"rolecreation",role:"ANY",permission:["SRC"],child:[]},
//                                                                   {label:"USER CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   {label:"SYSTEM USER CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"usercreation",role:"ANY",permission:["SUC"],child:[]},
//                                                                   {label:"REPORT UPLOAD",icon:"all_out",isParent:false,islastChild:true,pageLink:"reportupload",role:"ANY",permission:["RFP 18/19 UPLOAD"],child:[]},
//                                                                   {label:"APPROVER CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"approvercreation",role:"ANY",permission:["APPUC"],child:[]},
//                                                                   // {label:"RFP19",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   // {label:"RFP21",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
//                                                                   ]
//   },
// ]


// with childs
sideMenuList:menuItems[]=[
  {label:"SLA DashBoard",icon:"assignment",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
                                                                              {label:"All SLA Link",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports",role:"ANY",permission:["All SLA Link"],child:[]},
                                                                              {label:"HES",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/HES",role:"ANY",permission:["HES"],child:[]},
                                                                              {label:"MDM",icon:"all_out",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["MDM-A","MDM-B"],child:[

                                                                                {label:"MDM-A",icon:"all_out",isParent:false,islastChild:false,pageLink:"slareports/MDM-A",role:"ANY",permission:["MDM-A"],child:[]},
                                                                                {label:"MDM-B",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/MDM-B",role:"ANY",permission:["MDM-B"],child:[]},

                                                                              ]},
                                                                              {label:"CSP",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/CSP",role:"ANY",permission:["CSP"],child:[]},
                                                                              {label:"INVENTORY",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/INVENTORY",role:"ANY",permission:["INVENTORY"],child:[]},
                                                                              {label:"HELPDESK",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/HELPDESK",role:"ANY",permission:["HELPDESK"],child:[]},
                                                                              {label:"PROJECTS",icon:"all_out",isParent:true,islastChild:false,pageLink:"slareports/PROJECT-A",role:"ANY",permission:["PROJECT-A","PROJECT-B"],child:[

                                                                                {label:"PROJECTS-A",icon:"all_out",isParent:false,islastChild:false,pageLink:"slareports/PROJECT-A",role:"ANY",permission:["PROJECT-A"],child:[]},
                                                                                {label:"PROJECTS-B",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/PROJECT-B",role:"ANY",permission:["PROJECT-B"],child:[]},
                                                                              ]},
                                                                              {label:"TSP",icon:"all_out",isParent:false,islastChild:true,pageLink:"slareports/TSP",role:"ANY",permission:["TSP"],child:[]},
                                                                            ]
  },
  {label:"Operational Analytics",icon:"timeline",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
                                                                                    {label:"Operational Analytics Reports",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                                    {label:"Critical Dashboard Reports",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},

                                                                                  ]
  },
  {label:"Monitoring Dashboard",icon:"visibility",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[]
  },
  {label:"WFM",icon:"work",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
                                                                  {label:"CIMI",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  {label:"NSC",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  {label:"SM2SM",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  {label:"O&M",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  ]
  },
  {label:"Inventory",icon:"shopping_cart",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[]
  },
  {label:"Customer Indexing",icon:"settings_input_antenna",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["CIAC","CIAA","CIFUC","CIFUA","CISA","CISC"],child:[
                                                              {label:"Agency",icon:"all_out",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["CIAC","CIAA"],child:[

                                                                {label:"View Agency",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciagencydata",role:"ANY",permission:["CIAC"],child:[]},
                                                                {label:"Agency Allotment",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciagencyallotment",role:"ANY",permission:["CIAA"],child:[]},
                                                              ]},
                                                              {label:"Supervisor User",icon:"all_out",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["CISA","CISC"],child:[

                                                                {label:"User Creation",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciSupervisorList",role:"ANY",permission:["CISC"],child:[]},
                                                                {label:"User Allotment",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciSupervisorAllotment",role:"ANY",permission:[
                                                                  "CISA"],child:[]},
                                                              ]},
                                                              {label:"Field User",icon:"all_out",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["CIFUC","CIFUA"],child:[

                                                                {label:"User Creation",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/cifielduser",role:"ANY",permission:["CIFUC"],child:[]},
                                                                {label:"User Allotment",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/cifielduserallotment",role:"ANY",permission:["CIFUA"],child:[]},
                                                              ]},
                                                              {label:"ENH",icon:"all_out",isParent:true,islastChild:true,pageLink:"",role:"ANY",permission:["ENHADMIN"],child:[

                                                                {label:"Create Sub-Station",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/enhsubStation",role:"ANY",permission:["ANY"],child:[]},
                                                                {label:"Create Feeder",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/feeder",role:"ANY",permission:["ANY"],child:[]},
                                                                {label:"Create DTR",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/dtrdata",role:"ANY",permission:["ANY"],child:[]},
                                                                {label:"Create Pole",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/enhpoledata",role:"ANY",permission:["ANY"],child:[]},
                                                                {label:"Create ENH",icon:"all_out",isParent:true,islastChild:true,pageLink:"/customerindex/enhdata",role:"ANY",permission:["ENHADMIN"],child:[]},



                                                              ]},
                                                              {label:"Approval Bucket",icon:"all_out",isParent:true,islastChild:true,pageLink:"",role:"ANY",permission:["CIAPPROVAL"],child:[
                                                                {label:"My Approvals",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciApproval",role:"ANY",permission:["CIAPPROVAL"],child:[]},
                                                                {label:"Hold List",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciApprovalHold",role:"ANY",permission:["CIAPPROVAL"],child:[]},
                                                                {label:"Rejected List",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciApprovalReject",role:"ANY",permission:["CIAPPROVAL"],child:[]},
                                                                {label:"New CI List",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciNewApproval",role:"ANY",permission:["CIAPPROVAL"],child:[]},
                                                                {label:"Consumer Data",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/ciConsumerList",role:"ANY",permission:["CIAPPROVAL"],child:[]},
                                                              ]},
                                                              {label:"Signal Strength Upload",icon:"all_out",isParent:false,islastChild:true,pageLink:"/customerindex/signalStrength",role:"ANY",permission:["ANY"],child:[]},

                                                                  ]
  },

  {label:"User Authorization",icon:"supervisor_account",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
                                                                {label:"View All Applications",icon:"all_out",isParent:false,islastChild:true,pageLink:"/uam/uamUsers",role:"ANY",permission:["UAMVIEW"],child:[]},
                                                                {label:"My Approval Bucket",icon:"all_out",isParent:false,islastChild:true,pageLink:"/uam/myapprovals",role:"ANY",permission:["UAMAPPROVAL"],child:[]},


                                                                  ]
  },
  {label:"CONFIG",icon:"settings",isParent:true,islastChild:false,pageLink:"",role:"ANY",permission:["ANY"],child:[
                                                                  // {label:"ROLE CATEGORY",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  // {label:"ROLE",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  {label:"SYSTEM USERS",icon:"all_out",isParent:true,islastChild:true,pageLink:"/admin/rolecreation",role:"ANY",permission:["SRC","SUC"],child:[

                                                                    {label:"SYSTEM ROLE CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/rolecreation",role:"ANY",permission:["SRC"],child:[]},
                                                                    {label:"SYSTEM USER CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/usercreation",role:"ANY",permission:["SUC"],child:[]},

                                                                  ]},
                                                                  {label:"REPORT UPLOAD",icon:"all_out",isParent:true,islastChild:true,pageLink:"",role:"ANY",permission:["ANY"],child:[

                                                                    {label:"RFP 18/19 UPLOAD",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/rfp1819",role:"ANY",permission:["RFP 18/19 UPLOAD"],child:[]},
                                                                    {label:"RFP 21 UPLOAD",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/rfp21",role:"ANY",permission:["RFP 21 UPLOAD"],child:[]},
                                                                  ]},
                                                                  {label:"MAINTAN EMPLOYEE",icon:"all_out",isParent:true,islastChild:true,pageLink:"",role:"ANY",permission:["EMPLOYEE_ROLE","EMPLOYEE_CREATION"],child:[

                                                                    {label:"ROLES",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/employee/employeerole",role:"ANY",permission:["EMPLOYEE_ROLE"],child:[]},
                                                                    {label:"EMPLOYEE CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/employee/employeecreation",role:"ANY",permission:["EMPLOYEE_CREATION"],child:[]},
                                                                  ]},
                                                                  {label:"APPROVER CREATION",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/approvercreation",role:"ANY",permission:["APPUC"],child:[]},
                                                                  {label:"BATCH CONFIG",icon:"all_out",isParent:false,islastChild:true,pageLink:"/admin/batchstatus",role:"ANY",permission:["ANY"],child:[]},
                                                                  // {label:"RFP19",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  // {label:"RFP21",icon:"all_out",isParent:false,islastChild:true,pageLink:"dashboard",role:"ANY",permission:["ANY"],child:[]},
                                                                  ]
  },
]


  ngOnInit(): void {
    if (!this.authservice.isAuthenticated()) {
      this.router.navigate(['/home'])
      .then(value => {
        // window.location.reload();
    })
    }else{
      this.userName=this.authservice.getUserName();
    }
  }

  checkPermission(roleType:string,permissionList:string[]):boolean{
    for (var permission of permissionList) {
      return this.authservice.checkPermission(roleType,permission);
    }
    // permissionList.forEach(permission => {
    //   console.log(permission);
    //   console.log(this.authservice.checkPermission(roleType,permission));
    //   return this.authservice.checkPermission(roleType,permission);
    // });
    return false;

  }


  changeHeader(menu:menuItems){

    this.topMenuList=menu.child;
  }
  logout():void{
    // call api to logout user
    this.authservice.logout(this.router);


  }

}
export interface menuItems{
  label:string;
  isParent:boolean;
  icon:string;
  islastChild:boolean;
  pageLink:string;
  role:string;
  permission:string[];
  child:menuItems[];
}
