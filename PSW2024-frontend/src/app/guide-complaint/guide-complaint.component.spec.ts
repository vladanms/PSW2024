import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuideComplaintComponent } from './guide-complaint.component';

describe('GuideComplaintComponent', () => {
  let component: GuideComplaintComponent;
  let fixture: ComponentFixture<GuideComplaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GuideComplaintComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuideComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
