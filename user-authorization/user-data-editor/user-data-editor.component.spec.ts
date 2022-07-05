import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDataEditorComponent } from './user-data-editor.component';

describe('UserDataEditorComponent', () => {
  let component: UserDataEditorComponent;
  let fixture: ComponentFixture<UserDataEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDataEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDataEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
