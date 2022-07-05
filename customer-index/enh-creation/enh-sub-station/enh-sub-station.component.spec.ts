import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhSubStationComponent } from './enh-sub-station.component';

describe('EnhSubStationComponent', () => {
  let component: EnhSubStationComponent;
  let fixture: ComponentFixture<EnhSubStationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhSubStationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhSubStationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
