import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhPoleDetailsComponent } from './enh-pole-details.component';

describe('EnhPoleDetailsComponent', () => {
  let component: EnhPoleDetailsComponent;
  let fixture: ComponentFixture<EnhPoleDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhPoleDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhPoleDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
