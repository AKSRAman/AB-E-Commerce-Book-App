import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminavbarComponent } from './adminavbar.component';

describe('AdminavbarComponent', () => {
  let component: AdminavbarComponent;
  let fixture: ComponentFixture<AdminavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminavbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
