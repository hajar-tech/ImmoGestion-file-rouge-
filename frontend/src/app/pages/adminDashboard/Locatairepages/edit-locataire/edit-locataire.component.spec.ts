import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLocataireComponent } from './edit-locataire.component';

describe('EditLocataireComponent', () => {
  let component: EditLocataireComponent;
  let fixture: ComponentFixture<EditLocataireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditLocataireComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditLocataireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
