import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportRfp21Component } from './report-rfp21.component';

describe('ReportRfp21Component', () => {
  let component: ReportRfp21Component;
  let fixture: ComponentFixture<ReportRfp21Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportRfp21Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportRfp21Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
