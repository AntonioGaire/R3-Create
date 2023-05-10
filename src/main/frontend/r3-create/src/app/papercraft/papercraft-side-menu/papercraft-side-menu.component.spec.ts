import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PapercraftSideMenuComponent } from './papercraft-side-menu.component';

describe('PapercraftSideMenuComponent', () => {
  let component: PapercraftSideMenuComponent;
  let fixture: ComponentFixture<PapercraftSideMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PapercraftSideMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PapercraftSideMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
