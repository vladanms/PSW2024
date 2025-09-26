import { TestBed } from '@angular/core/testing';

import { TouristCheckoutService } from './tourist-checkout.service';

describe('TouristCheckoutService', () => {
  let service: TouristCheckoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TouristCheckoutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
