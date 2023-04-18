import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {HomeService} from "../home.service";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit{

  posts: Post[] = [];

  constructor(public homeService: HomeService) { }

  ngOnInit(): void {
    this.homeService.getAll().subscribe((data: Post[])=>{
      this.posts = data;
      console.log("Posts", data);
    })
  }

}
