import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableLocataireComponent } from './table-locataire.component';

describe('TableLocataireComponent', () => {
  let component: TableLocataireComponent;
  let fixture: ComponentFixture<TableLocataireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableLocataireComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableLocataireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
