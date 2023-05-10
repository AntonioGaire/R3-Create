import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModelListComponent } from './model-list/model-list.component';
import { ModelSideMenuComponent } from './model-side-menu/model-side-menu.component';
import {RouterLink} from "@angular/router";



@NgModule({
  declarations: [
    ModelListComponent,
    ModelSideMenuComponent
  ],
    imports: [
        CommonModule,
        RouterLink
    ]
})
export class ModelsModule { }
