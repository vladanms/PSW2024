import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuideDraftsComponent } from './guide-drafts.component';

describe('GuideDraftsComponent', () => {
  let component: GuideDraftsComponent;
  let fixture: ComponentFixture<GuideDraftsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GuideDraftsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuideDraftsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
