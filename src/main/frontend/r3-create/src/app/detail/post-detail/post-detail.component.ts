import { Component, OnInit } from '@angular/core';
import {Post} from "../../interfaces/post";
import {Comment} from "../../interfaces/comment";
import {PostDetailService} from "./post-detail.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Renderer2} from "@angular/core";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit{
  post !: Post;
  responseIds :number[] = [];
  username = "";
  responseCommentId :number = -1;

  constructor(public postDetailService: PostDetailService, private route: ActivatedRoute, private formBuilder:FormBuilder, private render:Renderer2) {  }

  postCommentForm = this.formBuilder.group({
    text : new FormControl('', [
      Validators.required,
      Validators.minLength(5)
    ])
  })

  ngOnInit(): void {
    this.postDetailService.find(Number(this.route.snapshot.paramMap.get('idPost'))).subscribe((data: Post)=>{

      this.post = data;
      console.log(this.post.comments)

      for(let c of data.comments){
        for(let r of c.comments){
          this.responseIds.push(r.idComment);
        }
      }

      this.responseIds.forEach( i => this.removeComment(i))

      this.username = this.postDetailService.decodeUser();
    })
  }

  removeComment(element: number) {
    this.post.comments.forEach((value,index)=>{
      if(value.idComment==element) this.post.comments.splice(index,1);
    });
  }

  setResponseCommentId(id:number){
    this.responseCommentId = id;
  }

  postComment(){
    const token = this.postDetailService.getToken();
    if(token !== null && this.postCommentForm.value.text !== "") {

      interface requestData {
        text: string;
        username:string;
        idPost:number;
        idParentComment:number;
      }

      const data : requestData={
        text: (this.postCommentForm.value.text == null ? "" : this.postCommentForm.value.text),
        username: this.username,
        idPost: this.post.idPost,
        idParentComment: this.responseCommentId
      }

      console.log(data)

      this.responseCommentId = -1;
      this.postDetailService.postComment(data);

    }
  }

  setPostDisable(e :any){

    if(!e.target.classList.contains("text-danger")){
      this.post.kudos ++
      this.render.addClass(e.target, "text-danger")
    }
  }

  setDisable(e :any, comment:Comment){

    if(!e.target.classList.contains("text-danger")){
      comment.kudos ++
      this.render.addClass(e.target, "text-danger")
    }
  }
}
