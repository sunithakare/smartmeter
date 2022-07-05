import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenericAuthGuardService } from 'src/app/services/authguard.service';
import { AdminComponent } from './admin.component';
import { ApproverCreationComponent } from './approvercreation/approver-creation.component';
import { ApproverDetailsComponent } from './approvercreation/approverdetails/approverdetails.component';
import { BatchInvokerComponent } from './batch-invoker/batch-invoker.component';
import { ReportRfp1819Component } from './reportupload/report-rfp1819/report-rfp1819.component';
import { ReportRfp21Component } from './reportupload/report-rfp21/report-rfp21.component';
import { RoleCreationComponent } from './rolecreation/role-creation.component';
import { UserCreationComponent } from './usercreation/user-creation.component';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
              { path: 'rolecreation', component: RoleCreationComponent,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'SRC'} },
              { path: 'usercreation', component: UserCreationComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'SUC'}},
              { path: 'rfp1819', component: ReportRfp1819Component ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'RFP 18/19 UPLOAD'}},
              { path: 'rfp21', component: ReportRfp21Component ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'RFP 21 UPLOAD'}},
              { path: 'approvercreation', component: ApproverCreationComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'APPUC'}},
              { path: 'approverdetails', component: ApproverDetailsComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'APPUC'}},
              { path: 'batchstatus', component: BatchInvokerComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
              { path: 'employee',  loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule)  },
               { path: '', redirectTo: '/ltapp/dashboard', pathMatch: 'full' }
              ],

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
