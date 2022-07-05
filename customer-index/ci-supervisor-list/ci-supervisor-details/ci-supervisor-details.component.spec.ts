import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiSupervisorDetailsComponent } from './ci-supervisor-details.component';

describe('CiSupervisorDetailsComponent', () => {
  let component: CiSupervisorDetailsComponent;
  let fixture: ComponentFixture<CiSupervisorDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiSupervisorDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiSupervisorDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
