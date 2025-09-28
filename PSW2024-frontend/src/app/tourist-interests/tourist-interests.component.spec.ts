import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TouristInterestsComponent } from './tourist-interests.component';

describe('TouristInterestsComponent', () => {
  let component: TouristInterestsComponent;
  let fixture: ComponentFixture<TouristInterestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TouristInterestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TouristInterestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
