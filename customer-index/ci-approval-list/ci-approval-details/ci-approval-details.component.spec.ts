import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiApprovalDetailsComponent } from './ci-approval-details.component';

describe('CiApprovalDetailsComponent', () => {
  let component: CiApprovalDetailsComponent;
  let fixture: ComponentFixture<CiApprovalDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiApprovalDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiApprovalDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
