import { TestBed } from '@angular/core/testing';

import { CreateTourService } from './create-tour.service';

describe('CreateTourService', () => {
  let service: CreateTourService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateTourService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
