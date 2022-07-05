import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiNewApprovalComponent } from './ci-new-approval.component';

describe('CiNewApprovalComponent', () => {
  let component: CiNewApprovalComponent;
  let fixture: ComponentFixture<CiNewApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiNewApprovalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiNewApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
