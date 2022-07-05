import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiHoldListComponent } from './ci-hold-list.component';

describe('CiHoldListComponent', () => {
  let component: CiHoldListComponent;
  let fixture: ComponentFixture<CiHoldListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiHoldListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiHoldListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
