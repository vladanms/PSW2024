import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TouristComplaintComponent } from './tourist-complaint.component';

describe('TouristComplaintComponent', () => {
  let component: TouristComplaintComponent;
  let fixture: ComponentFixture<TouristComplaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TouristComplaintComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TouristComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
