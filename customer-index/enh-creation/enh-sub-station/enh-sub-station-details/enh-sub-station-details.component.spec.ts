import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhSubStationDetailsComponent } from './enh-sub-station-details.component';

describe('EnhSubStationDetailsComponent', () => {
  let component: EnhSubStationDetailsComponent;
  let fixture: ComponentFixture<EnhSubStationDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhSubStationDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhSubStationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
