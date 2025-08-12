import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DreamHomesectionComponent } from './dream-homesection.component';

describe('DreamHomesectionComponent', () => {
  let component: DreamHomesectionComponent;
  let fixture: ComponentFixture<DreamHomesectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DreamHomesectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DreamHomesectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
