import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKeypointsComponent } from './add-keypoints.component';

describe('AddKeypointsComponent', () => {
  let component: AddKeypointsComponent;
  let fixture: ComponentFixture<AddKeypointsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddKeypointsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddKeypointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
