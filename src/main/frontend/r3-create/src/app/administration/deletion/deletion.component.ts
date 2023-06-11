import {Component, OnInit} from '@angular/core';
import {Category} from "../../interfaces/category";
import {Post} from "../../interfaces/post";
import {User} from "../../interfaces/user";
import {Comment} from "../../interfaces/comment";
import {AdministrationService} from "../administration.service";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-deletion',
  templateUrl: './deletion.component.html',
  styleUrls: ['./deletion.component.css']
})
export class DeletionComponent implements OnInit{

  constructor(private administrationService : AdministrationService, private formBuilder: FormBuilder) {
  }
  posts:Post[] = [];
  categories:Category[] = [];
  users: User[] = [];
  comments:Comment[] = [];

  toDelete :any;

  admin:number = 0;

  ngOnInit() {
    this.administrationService.getAllPosts().subscribe((data: Post[])=>{
      this.posts = data;
    })
    this.administrationService.getAllCategories().subscribe((data: Category[])=>{
      this.categories = data;
    })
    this.administrationService.getAllComments().subscribe((data: Comment[])=>{
      this.comments = data;
    })
    this.administrationService.getAllUsers().subscribe((data: User[])=>{
      this.users = data;
    })
    this.admin=0;
  }

  changeAdmin(e:any){
    this.admin = e.target.value.substring(3).valueOf()
  }

  setDelete(e:any){
    console.log(e);
    this.toDelete = e;
  }

  delete(){

    let data = {
      toDelete : 0,
      user : this.administrationService.decodeUser()
    }

    if(this.toDelete!==null){
      if (this.comments.includes(this.toDelete)){
        console.log("comment")
        data.toDelete = this.toDelete.idComment;
        this.administrationService.delete("comment", data);
      }else if( this.users.includes(this.toDelete) ){
        console.log("user")
        data.toDelete = this.toDelete.idUser;
        this.administrationService.delete("user", data);
      }else if( this.posts.includes(this.toDelete) ){
        console.log("post")
        data.toDelete = this.toDelete.idPost;
        this.administrationService.delete("post", data);
      }else if( this.categories.includes(this.toDelete) ){
        console.log("category")
        data.toDelete = this.toDelete.idCategory;
        this.administrationService.delete("category", data);
      }
    }
  }
}
