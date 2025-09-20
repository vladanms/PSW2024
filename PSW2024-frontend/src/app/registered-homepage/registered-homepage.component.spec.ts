import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredHomepageComponent } from './registered-homepage.component';

describe('RegisteredHomepageComponent', () => {
  let component: RegisteredHomepageComponent;
  let fixture: ComponentFixture<RegisteredHomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisteredHomepageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisteredHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
