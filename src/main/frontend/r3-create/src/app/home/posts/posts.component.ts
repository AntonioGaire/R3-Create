import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {HomeService} from "../home.service";
import {PostPage} from "../../interfaces/post-page";


@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})

export class PostsComponent implements OnInit{


  postPage!: PostPage;
  posts: Post[] = [];

  constructor(public homeService: HomeService) { }

  ngOnInit(): void {
    // this.homeService.getAll().subscribe((data: Post[])=>{
    //   this.posts = data;
    //   console.log("Posts", data);
    // })

  //   this.homeService.getPaginatedPosts(0).subscribe((data: PostPage)=>{
  //     this.postPage = data;
  //     console.log("Posts", data.posts);
  //     this.posts= data.posts;
  //   })
  // }
  // onScroll():void{
  //   console.log("SCROLL")
  //   this.homeService.getPaginatedPosts(this.postPage.currentPage+1).subscribe((data: PostPage)=>{
  //     this.postPage = data;
  //     this.posts.push(...data.posts);
  //     console.log("scrolledposts", this.posts);
  //   })
 }

}
