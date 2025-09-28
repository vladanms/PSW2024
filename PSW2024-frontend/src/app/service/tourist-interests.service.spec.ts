import { TestBed } from '@angular/core/testing';

import { TouristInterestsService } from './tourist-interests.service';

describe('TouristInterestsService', () => {
  let service: TouristInterestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TouristInterestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
