import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaperfraftListComponent } from './paperfraft-list.component';

describe('PaperfraftListComponent', () => {
  let component: PaperfraftListComponent;
  let fixture: ComponentFixture<PaperfraftListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaperfraftListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaperfraftListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
