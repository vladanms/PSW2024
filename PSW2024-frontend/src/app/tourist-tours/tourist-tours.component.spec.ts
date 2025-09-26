import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TouristToursComponent } from './tourist-tours.component';

describe('TouristToursComponent', () => {
  let component: TouristToursComponent;
  let fixture: ComponentFixture<TouristToursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TouristToursComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TouristToursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
