import { TestBed } from '@angular/core/testing';

import { GuideDraftsService } from './guide-drafts.service';

describe('GuideDraftsService', () => {
  let service: GuideDraftsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuideDraftsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
