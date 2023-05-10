import {Component, OnInit} from '@angular/core';
import {Category} from "../../interfaces/category";
import {PapercraftService} from "../papercraft.service";
import {CategoryDTO} from "../../interfaces/category-dto";

@Component({
  selector: 'app-papercraft-side-menu',
  templateUrl: './papercraft-side-menu.component.html',
  styleUrls: ['./papercraft-side-menu.component.css']
})
export class PapercraftSideMenuComponent implements OnInit {
  categoriesDTO: CategoryDTO[] = [];

  constructor(public papercraftService: PapercraftService){}

  ngOnInit(): void {
    this.papercraftService.getCategoriesWNofPosts().subscribe((data:CategoryDTO[]) => {
      this.categoriesDTO = data;
      console.log("DTO", data);
    })
  }
}
