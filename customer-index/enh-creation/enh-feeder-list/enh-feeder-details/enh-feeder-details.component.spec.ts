import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhFeederDetailsComponent } from './enh-feeder-details.component';

describe('EnhFeederDetailsComponent', () => {
  let component: EnhFeederDetailsComponent;
  let fixture: ComponentFixture<EnhFeederDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhFeederDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhFeederDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
