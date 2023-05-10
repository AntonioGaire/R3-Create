import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelSideMenuComponent } from './model-side-menu.component';

describe('ModelSideMenuComponent', () => {
  let component: ModelSideMenuComponent;
  let fixture: ComponentFixture<ModelSideMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModelSideMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModelSideMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
