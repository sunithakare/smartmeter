import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SLAAuthGuardService } from '../services/authguard.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LtappComponent } from './ltapp.component';
import { SlaReportsComponent } from './slareports/sla-reports.component';

const routes: Routes = [
  {
    path: '',
    component: LtappComponent,
    children: [{ path: 'slareports/:module', component: SlaReportsComponent ,canActivate: [SLAAuthGuardService]},
               { path: 'slareports', component: SlaReportsComponent ,canActivate: [SLAAuthGuardService] },
               { path: 'dashboard', component: DashboardComponent },
              //  { path: 'uamUsers', component: ListAllApplicationComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'APPUC'}},

               { path: 'admin',  loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)  },
               { path: 'uam',  loadChildren: () => import('./user-authorization/user-authorization.module').then(m => m.UserAuthorizationModule)  },
               { path: 'customerindex',  loadChildren: () => import('./customer-index/customer-index.module').then(m => m.CustomerIndexModule)  },
               { path: '', redirectTo: '/ltapp/dashboard', pathMatch: 'full' }
              ],

  },
  // { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  // { path: 'profile', component: ProfileComponent },
  // { path: 'testhome', component: CommonComponent },
  // { path: 'report', component: ReportTableComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes,)],
  exports: [RouterModule],
})
export class LtappRoutingModule {}
