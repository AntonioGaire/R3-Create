import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PostsComponent} from "../home/posts/posts.component";
import {PostDetailComponent} from "../detail/post-detail/post-detail.component";
import {QuestListComponent} from "../quests/quest-list/quest-list.component";
import {PaperfraftListComponent} from "../papercraft/paperfraft-list/paperfraft-list.component";
import {ForumListComponent} from "../forum/forum-list/forum-list.component";
import {ModelListComponent} from "../models/model-list/model-list.component";

const routes: Routes = [

  {path: 'home/:idPost', component: PostDetailComponent},
  {path: 'home', component: PostsComponent},
  {path: 'quests', component: QuestListComponent},
  {path: 'models', component: ModelListComponent},
  {path: 'forum', component: ForumListComponent},
  {path: 'papercraft', component: PaperfraftListComponent},
  {path: '**', component: PostsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

