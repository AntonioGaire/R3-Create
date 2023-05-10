import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {CoreModule} from "./core/core.module";
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {HomeModule} from "./home/home.module";
import {DetailModule} from "./detail/detail.module";
import {QuestsModule} from "./quests/quests.module";
import {PapercraftModule} from "./papercraft/papercraft.module";
import {ModelsModule} from "./models/models.module";
import {ForumModule} from "./forum/forum.module";

@NgModule({
  declarations: [
    AppComponent
  ],
    imports: [
      BrowserModule,
      CoreModule,
      RouterOutlet,
      HttpClientModule,
      AppRoutingModule,
      HomeModule,
      DetailModule,
      QuestsModule,
      PapercraftModule,
      ModelsModule,
      ForumModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
