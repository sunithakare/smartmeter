import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiApprovedListComponent } from './ci-approved-list.component';

describe('CiApprovedListComponent', () => {
  let component: CiApprovedListComponent;
  let fixture: ComponentFixture<CiApprovedListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiApprovedListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiApprovedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
