import { TestBed } from '@angular/core/testing';

import { TouristGradeService } from './tourist-grade.service';

describe('TouristGradeService', () => {
  let service: TouristGradeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TouristGradeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
