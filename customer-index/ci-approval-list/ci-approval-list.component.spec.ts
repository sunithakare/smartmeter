import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiApprovalListComponent } from './ci-approval-list.component';

describe('CiApprovalListComponent', () => {
  let component: CiApprovalListComponent;
  let fixture: ComponentFixture<CiApprovalListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiApprovalListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiApprovalListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
