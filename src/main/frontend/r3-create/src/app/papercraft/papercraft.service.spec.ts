import { TestBed } from '@angular/core/testing';

import { PapercraftService } from './papercraft.service';

describe('PapercraftService', () => {
  let service: PapercraftService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PapercraftService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
