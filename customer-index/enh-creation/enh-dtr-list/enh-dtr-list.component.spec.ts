import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnhDtrListComponent } from './enh-dtr-list.component';

describe('EnhDtrListComponent', () => {
  let component: EnhDtrListComponent;
  let fixture: ComponentFixture<EnhDtrListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnhDtrListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhDtrListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
