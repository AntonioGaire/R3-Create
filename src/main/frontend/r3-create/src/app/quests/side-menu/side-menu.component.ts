import {Component, OnInit} from '@angular/core';
import {Category} from "../../interfaces/category";
import {QuestsService} from "../quests.service";
import {PostType} from "../../interfaces/post-type";
import {CategoryDTO} from "../../interfaces/category-dto";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {

  categoriesDTO: CategoryDTO[] = [];
  types: PostType[] = [];
  kudos: number = 0;

  constructor(public questsService: QuestsService, private router: Router, private route : ActivatedRoute) {
  }

  ngOnInit(): void {
    this.questsService.getCategoriesWNofPosts().subscribe((data:CategoryDTO[]) => {
      this.categoriesDTO = data;
      console.log("DTO", data);
    })

    this.questsService.getAllTypes().subscribe((data: PostType[]) => {
      this.types = data;
      console.log("Types", data);
    })
  }

  addKudos(e:any){
    const queryParams: Params = { kudos: e };

    this.router.navigate(
      [],
      {
        relativeTo: this.route,
        queryParams: queryParams,
        queryParamsHandling: 'merge', // remove to replace all query params by provided
      });
  }
}
