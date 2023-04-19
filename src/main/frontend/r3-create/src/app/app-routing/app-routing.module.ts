import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PostsComponent} from "../home/posts/posts.component";
import {PostDetailComponent} from "../detail/post-detail/post-detail.component";
import {QuestListComponent} from "../quests/quest-list/quest-list.component";
import {SideMenuComponent} from "../quests/side-menu/side-menu.component";

const routes: Routes = [

  {path: 'home/:idPost', component: PostDetailComponent},
  {path: 'home', component: PostsComponent},
  {path: 'quests', component: SideMenuComponent},
  {path: '**', component: PostsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

