import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {HomeService} from "../home.service";
import {Category} from "../../interfaces/category";

@Component({
  selector: 'app-tag-selector',
  templateUrl: './tag-selector.component.html',
  styleUrls: ['./tag-selector.component.css']
})
export class TagSelectorComponent implements OnInit {

  categories: Category[] = [];

  constructor(public homeService: HomeService) {
  }

  ngOnInit(): void {
    this.homeService.getAllCategories().subscribe((data: Category[]) => {
      this.categories = data;
      console.log("Categories", data);
    })

    this.homeService.getTopCategories().subscribe((data: Category[]) => {
      this.categories = data;
      console.log("Top categories", data);
    })
  }
}
