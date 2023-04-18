import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {CoreModule} from "./core/core.module";
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {HomeModule} from "./home/home.module";
import {PostDetailComponent} from "./detail/post-detail/post-detail.component";
import {DetailModule} from "./detail/detail.module";

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
        DetailModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
