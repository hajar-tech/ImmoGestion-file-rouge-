import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardLogementComponent } from './card-logement.component';

describe('CardLogementComponent', () => {
  let component: CardLogementComponent;
  let fixture: ComponentFixture<CardLogementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardLogementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardLogementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
