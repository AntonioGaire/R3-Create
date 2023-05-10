import {Component, OnInit} from '@angular/core';
import {Category} from "../../interfaces/category";
import {PostType} from "../../interfaces/post-type";
import {ForumService} from "../forum.service";
import {User} from "../../interfaces/user";
import {CategoryDTO} from "../../interfaces/category-dto";

@Component({
  selector: 'app-forum-side-menu',
  templateUrl: './forum-side-menu.component.html',
  styleUrls: ['./forum-side-menu.component.css']
})
export class ForumSideMenuComponent implements OnInit {

  categoriesDTO : CategoryDTO[] = [];
  topCategories: Category[] = [];
  topUsers: User[] = [];


  constructor(public forumService: ForumService) {
  }

  ngOnInit(): void {

    this.forumService.getTopCategories().subscribe((data: Category[]) => {
      this.topCategories = data;
      console.log("Top categories", data);
    })

    this.forumService.getTopPosters().subscribe((data: User[]) => {
      this.topUsers = data;
      console.log("Top users", data);
    })

    this.forumService.getCategoriesWNofPosts().subscribe((data:CategoryDTO[]) => {
      this.categoriesDTO = data;
      console.log("DTO", data);
    })
  }
}
