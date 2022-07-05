import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhCreationComponent } from './enh-creation.component';

describe('EnhCreationComponent', () => {
  let component: EnhCreationComponent;
  let fixture: ComponentFixture<EnhCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhCreationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
