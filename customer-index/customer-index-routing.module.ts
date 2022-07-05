import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenericAuthGuardService } from 'src/app/services/authguard.service';
import { CiAgencyAllotmentDetailsComponent } from './ci-agency-allotment-list/ci-agency-allotment-details/ci-agency-allotment-details.component';
import { CiAgencyAllotmentListComponent } from './ci-agency-allotment-list/ci-agency-allotment-list.component';
import { CiAgencyDetailsComponent } from './ci-agency-list/ci-agency-details/ci-agency-details.component';
import { CiAgencyListComponent } from './ci-agency-list/ci-agency-list.component';
import { CiApprovalListComponent } from './ci-approval-list/ci-approval-list.component';
import { CiApprovedListComponent } from './ci-approval-list/ci-approved-list/ci-approved-list.component';
import { CiHoldListComponent } from './ci-approval-list/ci-hold-list/ci-hold-list.component';
import { CiNewApprovalComponent } from './ci-approval-list/ci-new-approval/ci-new-approval.component';
import { CiRejectListComponent } from './ci-approval-list/ci-reject-list/ci-reject-list.component';
import { CiFieldUserAllotmentComponent } from './ci-field-user-allotment/ci-field-user-allotment.component';
import { CiFieldUserListComponent } from './ci-field-user-list/ci-field-user-list.component';
import { CiSignalStrengthUploadComponent } from './ci-signal-strength-upload/ci-signal-strength-upload.component';
import { CiSupervisorAllotmentComponent } from './ci-supervisor-allotment/ci-supervisor-allotment.component';
import { CiSupervisorListComponent } from './ci-supervisor-list/ci-supervisor-list.component';
import { CustomerIndexComponent } from './customer-index.component';
import { EnhCreationComponent } from './enh-creation/enh-creation.component';
import { EnhDtrListComponent } from './enh-creation/enh-dtr-list/enh-dtr-list.component';
import { EnhFeederListComponent } from './enh-creation/enh-feeder-list/enh-feeder-list.component';
import { EnhPoleListComponent } from './enh-creation/enh-pole-list/enh-pole-list.component';
import { EnhSubStationComponent } from './enh-creation/enh-sub-station/enh-sub-station.component';

const routes: Routes = [ {
  path: '',
  component: CustomerIndexComponent,
  children: [
             { path: 'ciagencydata', component: CiAgencyListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
             { path: 'ciagencyallotment', component: CiAgencyAllotmentListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
             { path: 'cifielduser', component: CiFieldUserListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
             { path: 'cifielduserallotment', component: CiFieldUserAllotmentComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
             { path: 'ciSupervisorList', component: CiSupervisorListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
             { path: 'ciSupervisorAllotment', component: CiSupervisorAllotmentComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
             { path: 'ciConsumerList', component: CiApprovedListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'CIAPPROVAL'}},
             { path: 'ciNewApproval', component: CiNewApprovalComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'CIAPPROVAL'}},
             { path: 'ciApproval', component: CiApprovalListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'CIAPPROVAL'}},
             { path: 'ciApprovalHold', component: CiHoldListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'CIAPPROVAL'}},
             { path: 'ciApprovalReject', component: CiRejectListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'CIAPPROVAL'}},
             { path: 'enhsubStation', component: EnhSubStationComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ENHADMIN'}},
             { path: 'feeder', component: EnhFeederListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ENHADMIN'}},
             { path: 'dtrdata', component: EnhDtrListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ENHADMIN'}},
             { path: 'enhpoledata', component: EnhPoleListComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ENHADMIN'}},
             { path: 'enhdata', component: EnhCreationComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ENHADMIN'}},
             { path: 'signalStrength', component: CiSignalStrengthUploadComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'ANY'}},
            //  { path: 'myapprovals', component: MyApprovalBucketComponent ,canActivate: [GenericAuthGuardService],data:{role:'ANY',permission:'UAMAPPROVAL'}},

             { path: '', redirectTo: '/ltapp/dashboard', pathMatch: 'full' }
            ],

}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerIndexRoutingModule { }
