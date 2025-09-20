import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuideHomepageComponent } from './guide-homepage.component';

describe('GuideHomepageComponent', () => {
  let component: GuideHomepageComponent;
  let fixture: ComponentFixture<GuideHomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GuideHomepageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuideHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
