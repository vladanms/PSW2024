import { TestBed } from '@angular/core/testing';

import { TouristComplaintService } from './tourist-complaint.service';

describe('TouristComplaintService', () => {
  let service: TouristComplaintService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TouristComplaintService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
