import { TestBed } from '@angular/core/testing';

import { TouristToursService } from './tourist-tours.service';

describe('TouristToursService', () => {
  let service: TouristToursService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TouristToursService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
