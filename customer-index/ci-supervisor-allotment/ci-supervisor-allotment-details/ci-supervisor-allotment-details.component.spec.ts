import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiSupervisorAllotmentDetailsComponent } from './ci-supervisor-allotment-details.component';

describe('CiSupervisorAllotmentDetailsComponent', () => {
  let component: CiSupervisorAllotmentDetailsComponent;
  let fixture: ComponentFixture<CiSupervisorAllotmentDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiSupervisorAllotmentDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiSupervisorAllotmentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
