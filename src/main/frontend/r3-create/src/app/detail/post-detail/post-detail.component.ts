import { Component, OnInit } from '@angular/core';
import {Post} from "../../interfaces/post";
import {PostDetailService} from "./post-detail.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit{
  post !: Post;
  responseIds :number[] = [];

  private apiURL = "http://localhost:8080/posts";

  constructor(public postDetailService: PostDetailService, private route: ActivatedRoute) {  }

  ngOnInit(): void {
    this.postDetailService.find(Number(this.route.snapshot.paramMap.get('idPost'))).subscribe((data: Post)=>{
      this.post = data;

      for(let c of data.comments){
        for(let r of c.comments){
          this.responseIds.push(r.idComment);
        }
      }

      console.log(this.responseIds);
      console.log("Post detallado: ", data);
    })
  }
}
