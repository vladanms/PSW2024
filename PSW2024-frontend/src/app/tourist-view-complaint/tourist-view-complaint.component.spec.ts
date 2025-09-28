import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TouristViewComplaintComponent } from './tourist-view-complaint.component';

describe('TouristViewComplaintComponent', () => {
  let component: TouristViewComplaintComponent;
  let fixture: ComponentFixture<TouristViewComplaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TouristViewComplaintComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TouristViewComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
