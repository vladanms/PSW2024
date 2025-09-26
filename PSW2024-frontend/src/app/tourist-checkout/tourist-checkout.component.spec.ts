import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TouristCheckoutComponent } from './tourist-checkout.component';

describe('TouristCheckoutComponent', () => {
  let component: TouristCheckoutComponent;
  let fixture: ComponentFixture<TouristCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TouristCheckoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TouristCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
