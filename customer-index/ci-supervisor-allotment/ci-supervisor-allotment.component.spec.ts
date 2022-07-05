import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiSupervisorAllotmentComponent } from './ci-supervisor-allotment.component';

describe('CiSupervisorAllotmentComponent', () => {
  let component: CiSupervisorAllotmentComponent;
  let fixture: ComponentFixture<CiSupervisorAllotmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiSupervisorAllotmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiSupervisorAllotmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
