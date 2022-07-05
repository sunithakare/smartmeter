import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BatchInvokerComponent } from './batch-invoker.component';

describe('BatchInvokerComponent', () => {
  let component: BatchInvokerComponent;
  let fixture: ComponentFixture<BatchInvokerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BatchInvokerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BatchInvokerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
