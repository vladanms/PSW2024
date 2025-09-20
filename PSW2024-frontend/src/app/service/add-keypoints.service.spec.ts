import { TestBed } from '@angular/core/testing';

import { AddKeypointsService } from './add-keypoints.service';

describe('AddKeypointsService', () => {
  let service: AddKeypointsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddKeypointsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
