import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PostsComponent} from "../home/posts/posts.component";
import {PostDetailComponent} from "../detail/post-detail/post-detail.component";

const routes: Routes = [

  {path: 'home/:idPost', component: PostDetailComponent},
  {path: 'home', component: PostsComponent},
  {path: '**', component: PostsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

