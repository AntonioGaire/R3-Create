import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {CoreService} from "../core.service";
import {PostPage} from "../../interfaces/post-page";
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})

export class PostListComponent implements OnInit {

  postPage!: PostPage;
  posts: Post[] = [];
  type: string = "";
  categories: string[] = [];
  searchTerm :string = "";
  kudos :number = 0;

  constructor(public coreService: CoreService, private route: ActivatedRoute) {}

  ngOnInit(): void {

    this.route.queryParams
      .subscribe(params =>  {
        console.log(params)
          this.type = (params['type'] == undefined ? [] : params['type'])
          this.categories = (params['categories'] == undefined ? [] : params['categories']);
          this.searchTerm = (params['search'] == undefined ? '' : params['search']);
          this.kudos = (params['kudos'] == undefined ? '' : params['kudos']);
        }
      );
      if(this.searchTerm == '') {
        this.coreService.getPostsVariable(0, this.type, this.categories, this.kudos).subscribe((data: PostPage) => {
          this.postPage = data;
          console.log("P T C", data.posts);
          this.posts = data.posts;
        })
      }else{
        this.coreService.getSearchResults(0, this.searchTerm).subscribe((data: PostPage) => {
          this.postPage = data;
          console.log("search", data.posts);
          this.posts = data.posts;
        })
      }

      // if (this.posts.length<12){
      //   this.onScroll();
      // }
  }
  onScroll():void{
    console.log("SCROLL")
    if(this.searchTerm == ''){
      this.coreService.getPostsVariable(this.postPage.currentPage+1, this.type, this.categories, this.kudos).subscribe((data: PostPage)=>{
        this.postPage = data;
        this.posts.push(...data.posts);
        console.log("scrolledposts", this.posts);
      })
    }else{
      this.coreService.getSearchResults(this.postPage.currentPage+1, this.searchTerm).subscribe((data: PostPage)=>{
        this.postPage = data;
        this.posts.push(...data.posts);
        console.log("scrolledposts", this.posts);
      })
    }
  }

}
