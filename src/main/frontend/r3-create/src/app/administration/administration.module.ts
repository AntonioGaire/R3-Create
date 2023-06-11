import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostCreationComponent } from './post-creation/post-creation.component';
import {CdkOverlayOrigin} from "@angular/cdk/overlay";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import { DeletionComponent } from './deletion/deletion.component';
import { AdditionComponent } from './addition/addition.component';



@NgModule({
  declarations: [
    PostCreationComponent,
    DeletionComponent,
    AdditionComponent
  ],
  imports: [
    CommonModule,
    CdkOverlayOrigin,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule,
    FormsModule
  ]
})
export class AdministrationModule { }
