import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiRejectListComponent } from './ci-reject-list.component';

describe('CiRejectListComponent', () => {
  let component: CiRejectListComponent;
  let fixture: ComponentFixture<CiRejectListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiRejectListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiRejectListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
