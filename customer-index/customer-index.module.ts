import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerIndexRoutingModule } from './customer-index-routing.module';
import { CustomerIndexComponent } from './customer-index.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { OverlayModule } from '@angular/cdk/overlay';
import { PortalModule } from '@angular/cdk/portal';
import { CdkTreeModule } from '@angular/cdk/tree';
import { HttpClientXsrfModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatRippleModule, MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTreeModule } from '@angular/material/tree';
import { CiAgencyListComponent } from './ci-agency-list/ci-agency-list.component';
import { CiAgencyDetailsComponent } from './ci-agency-list/ci-agency-details/ci-agency-details.component';
import { CiAgencyAllotmentListComponent } from './ci-agency-allotment-list/ci-agency-allotment-list.component';
import { CiAgencyAllotmentDetailsComponent } from './ci-agency-allotment-list/ci-agency-allotment-details/ci-agency-allotment-details.component';
import { CiFieldUserListComponent } from './ci-field-user-list/ci-field-user-list.component';
import { CiFieldUserDetailsComponent } from './ci-field-user-list/ci-field-user-details/ci-field-user-details.component';
import { CiFieldUserAllotmentComponent } from './ci-field-user-allotment/ci-field-user-allotment.component';
import { CiFieldUserAllotmentDetailsComponent } from './ci-field-user-allotment/ci-field-user-allotment-details/ci-field-user-allotment-details.component';
import { EnhCreationComponent } from './enh-creation/enh-creation.component';
import { EnhDetailsComponent } from './enh-creation/enh-details/enh-details.component';
import { CiSupervisorListComponent } from './ci-supervisor-list/ci-supervisor-list.component';
import { CiSupervisorDetailsComponent } from './ci-supervisor-list/ci-supervisor-details/ci-supervisor-details.component';
import { CiSupervisorAllotmentComponent } from './ci-supervisor-allotment/ci-supervisor-allotment.component';
import { CiSupervisorAllotmentDetailsComponent } from './ci-supervisor-allotment/ci-supervisor-allotment-details/ci-supervisor-allotment-details.component';
import { CiApprovalListComponent } from './ci-approval-list/ci-approval-list.component';
import { CiApprovalDetailsComponent } from './ci-approval-list/ci-approval-details/ci-approval-details.component';
import { CiApprovalDialogComponent } from './ci-approval-list/ci-approval-dialog/ci-approval-dialog.component';
import { CiHoldListComponent } from './ci-approval-list/ci-hold-list/ci-hold-list.component';
import { CiRejectListComponent } from './ci-approval-list/ci-reject-list/ci-reject-list.component';
import { CiMeterImageDialogComponent } from './ci-approval-list/ci-meter-image-dialog/ci-meter-image-dialog.component';
import { EnhDtrDetailsComponent } from './enh-creation/enh-dtr-list/enh-dtr-details/enh-dtr-details.component';
import { EnhDtrListComponent } from './enh-creation/enh-dtr-list/enh-dtr-list.component';
import { EnhFeederDetailsComponent } from './enh-creation/enh-feeder-list/enh-feeder-details/enh-feeder-details.component';
import { EnhFeederListComponent } from './enh-creation/enh-feeder-list/enh-feeder-list.component';
import { EnhPoleDetailsComponent } from './enh-creation/enh-pole-list/enh-pole-details/enh-pole-details.component';
import { EnhPoleListComponent } from './enh-creation/enh-pole-list/enh-pole-list.component';
import { EnhSubStationDetailsComponent } from './enh-creation/enh-sub-station/enh-sub-station-details/enh-sub-station-details.component';
import { EnhSubStationComponent } from './enh-creation/enh-sub-station/enh-sub-station.component';
import { CiNewApprovalComponent } from './ci-approval-list/ci-new-approval/ci-new-approval.component';
import { CiSignalStrengthUploadComponent } from './ci-signal-strength-upload/ci-signal-strength-upload.component';
import { CiApprovedListComponent } from './ci-approval-list/ci-approved-list/ci-approved-list.component';

@NgModule({
  declarations: [
    CustomerIndexComponent,
    CiAgencyListComponent,
    CiAgencyDetailsComponent,
    CiAgencyAllotmentListComponent,
    CiAgencyAllotmentDetailsComponent,
    CiFieldUserListComponent,
    CiFieldUserDetailsComponent,
    CiFieldUserAllotmentComponent,
    CiFieldUserAllotmentDetailsComponent,
    EnhCreationComponent,
    EnhDetailsComponent,
    CiSupervisorListComponent,
    CiSupervisorDetailsComponent,
    CiSupervisorAllotmentComponent,
    CiSupervisorAllotmentDetailsComponent,
    CiApprovalListComponent,
    CiApprovalDetailsComponent,
    CiApprovalDialogComponent,
    CiHoldListComponent,
    CiRejectListComponent,
    CiMeterImageDialogComponent,
    EnhSubStationComponent,
    EnhSubStationDetailsComponent,
    EnhFeederListComponent,
    EnhFeederDetailsComponent,
    EnhDtrListComponent,
    EnhDtrDetailsComponent,
    EnhPoleListComponent,
    EnhPoleDetailsComponent,
    CiNewApprovalComponent,
    CiSignalStrengthUploadComponent,
    CiApprovedListComponent

  ],
  imports: [
    CommonModule,
    CustomerIndexRoutingModule,
    FormsModule,
    DragDropModule,
    HttpClientXsrfModule.withOptions({cookieName: 'XSRF-TOKEN',
    headerName: 'X-XSRF-TOKEN'}),
    FlexLayoutModule,
    // material imports
    CdkTreeModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDividerModule,
    MatExpansionModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatButtonToggleModule,
    MatTreeModule,
    OverlayModule,
    PortalModule,
    MatBadgeModule,
    MatGridListModule,
    MatRadioModule,
    MatDatepickerModule,
    MatTooltipModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatStepperModule,
    MatDialogModule
  ]
})
export class CustomerIndexModule { }
