import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostDetailComponent } from './post-detail/post-detail.component';
import {BrowserModule} from "@angular/platform-browser";



@NgModule({
  declarations: [
    PostDetailComponent
  ],
  imports: [
    CommonModule,
    BrowserModule
  ]
})
export class DetailModule { }
