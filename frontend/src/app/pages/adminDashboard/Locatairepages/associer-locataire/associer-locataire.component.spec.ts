import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssocierLocataireComponent } from './associer-locataire.component';

describe('AssocierLocataireComponent', () => {
  let component: AssocierLocataireComponent;
  let fixture: ComponentFixture<AssocierLocataireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssocierLocataireComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssocierLocataireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
