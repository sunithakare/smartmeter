import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiApprovalDialogComponent } from './ci-approval-dialog.component';

describe('CiApprovalDialogComponent', () => {
  let component: CiApprovalDialogComponent;
  let fixture: ComponentFixture<CiApprovalDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiApprovalDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiApprovalDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
