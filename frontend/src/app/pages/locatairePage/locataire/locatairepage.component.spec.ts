import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocatairepageComponent } from './locatairepage.component';

describe('LocataireComponent', () => {
  let component: LocatairepageComponent;
  let fixture: ComponentFixture<LocatairepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LocatairepageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocatairepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
