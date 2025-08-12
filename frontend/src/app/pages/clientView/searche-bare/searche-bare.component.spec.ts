import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearcheBareComponent } from './searche-bare.component';

describe('SearcheBareComponent', () => {
  let component: SearcheBareComponent;
  let fixture: ComponentFixture<SearcheBareComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearcheBareComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearcheBareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
