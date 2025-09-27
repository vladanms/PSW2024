import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TouristGradeComponent } from './tourist-grade.component';

describe('TouristGradeComponent', () => {
  let component: TouristGradeComponent;
  let fixture: ComponentFixture<TouristGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TouristGradeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TouristGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
