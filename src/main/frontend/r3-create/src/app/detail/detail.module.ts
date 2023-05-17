import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostDetailComponent } from './post-detail/post-detail.component';
import {BrowserModule} from "@angular/platform-browser";
import {MatCardModule} from "@angular/material/card";



@NgModule({
  declarations: [
    PostDetailComponent
  ],
    imports: [
        CommonModule,
        BrowserModule,
        MatCardModule
    ]
})
export class DetailModule { }
