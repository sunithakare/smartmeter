import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiSignalStrengthUploadComponent } from './ci-signal-strength-upload.component';

describe('CiSignalStrengthUploadComponent', () => {
  let component: CiSignalStrengthUploadComponent;
  let fixture: ComponentFixture<CiSignalStrengthUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiSignalStrengthUploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiSignalStrengthUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
