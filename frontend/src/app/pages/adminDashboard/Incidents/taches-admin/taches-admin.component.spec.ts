import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TachesAdminComponent } from './taches-admin.component';

describe('TachesAdminComponent', () => {
  let component: TachesAdminComponent;
  let fixture: ComponentFixture<TachesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TachesAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TachesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
