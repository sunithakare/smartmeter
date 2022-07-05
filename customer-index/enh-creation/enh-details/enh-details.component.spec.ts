import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhDetailsComponent } from './enh-details.component';

describe('EnhDetailsComponent', () => {
  let component: EnhDetailsComponent;
  let fixture: ComponentFixture<EnhDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
