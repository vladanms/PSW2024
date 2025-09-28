import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminMaliciousComponent } from './admin-malicious.component';

describe('AdminMaliciousComponent', () => {
  let component: AdminMaliciousComponent;
  let fixture: ComponentFixture<AdminMaliciousComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminMaliciousComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminMaliciousComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
