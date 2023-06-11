import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PostsComponent} from "../home/posts/posts.component";
import {PostDetailComponent} from "../detail/post-detail/post-detail.component";
import {QuestListComponent} from "../quests/quest-list/quest-list.component";
import {PaperfraftListComponent} from "../papercraft/paperfraft-list/paperfraft-list.component";
import {ForumListComponent} from "../forum/forum-list/forum-list.component";
import {ModelListComponent} from "../models/model-list/model-list.component";
import {SearchResultsComponent} from "../search/search-results/search-results.component";
import {PostCreationComponent} from "../administration/post-creation/post-creation.component";
import {DeletionComponent} from "../administration/deletion/deletion.component";

const routes: Routes = [

  {path: 'home/:idPost', component: PostDetailComponent},
  {path: 'home', component: PostsComponent},
  {path: 'create', component: PostCreationComponent},
  {path: 'quests', component: QuestListComponent},
  {path: 'models', component: ModelListComponent},
  {path: 'forum', component: ForumListComponent},
  {path: 'papercraft', component: PaperfraftListComponent},
  {path: 'search', component:SearchResultsComponent},
  {path: 'administrate', component:DeletionComponent},
  {path: '**', component: PostsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

