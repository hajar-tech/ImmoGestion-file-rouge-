import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HerohomeComponent } from './herohome.component';

describe('HerohomeComponent', () => {
  let component: HerohomeComponent;
  let fixture: ComponentFixture<HerohomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HerohomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HerohomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
