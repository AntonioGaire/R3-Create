import {Component, OnInit} from '@angular/core';
import {Category} from "../../interfaces/category";
import {ModelService} from "../model.service";
import {CategoryDTO} from "../../interfaces/category-dto";

@Component({
  selector: 'app-model-side-menu',
  templateUrl: './model-side-menu.component.html',
  styleUrls: ['./model-side-menu.component.css']
})
export class ModelSideMenuComponent implements OnInit {
  categoriesDTO: CategoryDTO[] = [];

  constructor(public modelService: ModelService) {
  }

  ngOnInit(): void {
    this.modelService.getCategoriesWNofPosts().subscribe((data:CategoryDTO[]) => {
      this.categoriesDTO = data;
      console.log("DTO", data);
    })
  }
}
