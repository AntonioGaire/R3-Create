import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {ForumService} from "../forum.service";
@Component({
  selector: 'app-forum-list',
  templateUrl: './forum-list.component.html',
  styleUrls: ['./forum-list.component.css']
})
export class ForumListComponent  implements OnInit {

  posts: Post[] = [];

  constructor(public forumService: ForumService) {
  }

  ngOnInit(): void {
    this.forumService.getAllForumPosts().subscribe((data: Post[]) => {
      this.posts = data;
      console.log("Posts", data);
    })
  }
}
