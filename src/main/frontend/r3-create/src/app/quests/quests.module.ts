import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuestListComponent } from './quest-list/quest-list.component';
import { SideMenuComponent } from './side-menu/side-menu.component';
import {BrowserModule} from "@angular/platform-browser";
import {HomeModule} from "../home/home.module";
import {RouterLink} from "@angular/router";



@NgModule({
  declarations: [
    QuestListComponent,
    SideMenuComponent
  ],
    imports: [
        CommonModule,
        BrowserModule,
        HomeModule,
        RouterLink
    ],
  exports: [
    SideMenuComponent,
    QuestListComponent
  ]
})
export class QuestsModule { }
