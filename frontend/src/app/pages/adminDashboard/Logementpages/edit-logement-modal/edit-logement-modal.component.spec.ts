import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLogementModalComponent } from './edit-logement-modal.component';

describe('EditLogementModalComponent', () => {
  let component: EditLogementModalComponent;
  let fixture: ComponentFixture<EditLogementModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditLogementModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditLogementModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
