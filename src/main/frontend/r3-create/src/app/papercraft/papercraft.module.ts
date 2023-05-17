import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaperfraftListComponent } from './paperfraft-list/paperfraft-list.component';
import {RouterLink} from "@angular/router";
import {QuestsModule} from "../quests/quests.module";
import { PapercraftSideMenuComponent } from './papercraft-side-menu/papercraft-side-menu.component';
import {HomeModule} from "../home/home.module";
import {BrowserModule} from "@angular/platform-browser";
import {CoreModule} from "../core/core.module";



@NgModule({
  declarations: [
    PaperfraftListComponent,
    PapercraftSideMenuComponent
  ],
    imports: [
        CommonModule,
        RouterLink,
        QuestsModule,
        HomeModule,
        BrowserModule,
        CoreModule
    ],
  exports: [
    PaperfraftListComponent,
    PapercraftSideMenuComponent
  ]
})
export class PapercraftModule { }
