import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForumListComponent } from './forum-list/forum-list.component';
import { ForumSideMenuComponent } from './forum-side-menu/forum-side-menu.component';
import {RouterLink} from "@angular/router";
import {CoreModule} from "../core/core.module";



@NgModule({
  declarations: [
    ForumListComponent,
    ForumSideMenuComponent
  ],
    imports: [
        CommonModule,
        RouterLink,
        CoreModule
    ]
})
export class ForumModule { }
