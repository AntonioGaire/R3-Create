import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PostListComponent } from './post-list/post-list.component';
import {RouterLink} from "@angular/router";
import {InfiniteScrollModule} from "ngx-infinite-scroll";
import {MatCardModule} from "@angular/material/card";


@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    PostListComponent
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    PostListComponent
  ],
  imports: [
    CommonModule,
    RouterLink,
    InfiniteScrollModule,
    MatCardModule
  ]
})
export class CoreModule { }
