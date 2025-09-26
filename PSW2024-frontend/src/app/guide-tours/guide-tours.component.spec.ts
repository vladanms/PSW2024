import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuideToursComponent } from './guide-tours.component';

describe('GuideToursComponent', () => {
  let component: GuideToursComponent;
  let fixture: ComponentFixture<GuideToursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GuideToursComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuideToursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
