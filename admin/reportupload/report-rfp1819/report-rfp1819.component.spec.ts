import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportRfp1819Component } from './report-rfp1819.component';

describe('ReportRfp1819Component', () => {
  let component: ReportRfp1819Component;
  let fixture: ComponentFixture<ReportRfp1819Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportRfp1819Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportRfp1819Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
