import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TagSelectorComponent } from './tag-selector/tag-selector.component';
import { PostsComponent } from './posts/posts.component';
import { BrowserModule } from "@angular/platform-browser";
import {RouterLink} from "@angular/router";



@NgModule({
    declarations: [
        TagSelectorComponent,
        PostsComponent
    ],
    exports: [
        PostsComponent
    ],
    imports: [
        CommonModule,
        BrowserModule,
        RouterLink
    ]
})
export class HomeModule { }
