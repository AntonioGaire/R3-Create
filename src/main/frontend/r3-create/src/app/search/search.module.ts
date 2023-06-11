import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchResultsComponent } from './search-results/search-results.component';
import {CoreModule} from "../core/core.module";



@NgModule({
  declarations: [
    SearchResultsComponent
  ],
    imports: [
        CommonModule,
        CoreModule
    ]
})
export class SearchModule { }
