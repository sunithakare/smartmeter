import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiSupervisorListComponent } from './ci-supervisor-list.component';

describe('CiSupervisorListComponent', () => {
  let component: CiSupervisorListComponent;
  let fixture: ComponentFixture<CiSupervisorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiSupervisorListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiSupervisorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
