import { TestBed } from '@angular/core/testing';

import { RegisteredHomepageService } from './registered-homepage.service';

describe('RegisteredHomepageService', () => {
  let service: RegisteredHomepageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegisteredHomepageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
