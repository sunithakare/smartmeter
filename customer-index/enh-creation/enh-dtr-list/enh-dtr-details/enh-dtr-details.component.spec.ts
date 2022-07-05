import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhDtrDetailsComponent } from './enh-dtr-details.component';

describe('EnhDtrDetailsComponent', () => {
  let component: EnhDtrDetailsComponent;
  let fixture: ComponentFixture<EnhDtrDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhDtrDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhDtrDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
