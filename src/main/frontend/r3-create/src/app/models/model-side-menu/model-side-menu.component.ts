import {Component, OnInit} from '@angular/core';
import {Category} from "../../interfaces/category";
import {ModelService} from "../model.service";
import {CategoryDTO} from "../../interfaces/category-dto";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-model-side-menu',
  templateUrl: './model-side-menu.component.html',
  styleUrls: ['./model-side-menu.component.css']
})
export class ModelSideMenuComponent implements OnInit {
  categoriesDTO: CategoryDTO[] = [];
  kudos:number=0;

  constructor(public modelService: ModelService, private route:ActivatedRoute, private router:Router) {
  }

  ngOnInit(): void {
    this.modelService.getCategoriesWNofPosts().subscribe((data:CategoryDTO[]) => {
      this.categoriesDTO = data;
      console.log("DTO", data);
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
