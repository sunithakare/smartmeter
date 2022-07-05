import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiMeterImageDialogComponent } from './ci-meter-image-dialog.component';

describe('CiMeterImageDialogComponent', () => {
  let component: CiMeterImageDialogComponent;
  let fixture: ComponentFixture<CiMeterImageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiMeterImageDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiMeterImageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
