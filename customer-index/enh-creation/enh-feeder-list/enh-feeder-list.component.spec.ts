import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhFeederListComponent } from './enh-feeder-list.component';

describe('EnhFeederListComponent', () => {
  let component: EnhFeederListComponent;
  let fixture: ComponentFixture<EnhFeederListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhFeederListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhFeederListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
