import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhPoleListComponent } from './enh-pole-list.component';

describe('EnhPoleListComponent', () => {
  let component: EnhPoleListComponent;
  let fixture: ComponentFixture<EnhPoleListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhPoleListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhPoleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
