import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForumListComponent } from './forum-list/forum-list.component';
import { ForumSideMenuComponent } from './forum-side-menu/forum-side-menu.component';
import {RouterLink} from "@angular/router";



@NgModule({
  declarations: [
    ForumListComponent,
    ForumSideMenuComponent
  ],
    imports: [
        CommonModule,
        RouterLink
    ]
})
export class ForumModule { }
