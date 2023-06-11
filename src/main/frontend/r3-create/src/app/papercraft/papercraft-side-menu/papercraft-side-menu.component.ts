import {Component, OnInit} from '@angular/core';
import {Category} from "../../interfaces/category";
import {PapercraftService} from "../papercraft.service";
import {CategoryDTO} from "../../interfaces/category-dto";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-papercraft-side-menu',
  templateUrl: './papercraft-side-menu.component.html',
  styleUrls: ['./papercraft-side-menu.component.css']
})
export class PapercraftSideMenuComponent implements OnInit {
  categoriesDTO: CategoryDTO[] = [];
  kudos:number=0;

  constructor(public papercraftService: PapercraftService, private router: Router, private route : ActivatedRoute){}

  ngOnInit(): void {
    this.papercraftService.getCategoriesWNofPosts().subscribe((data:CategoryDTO[]) => {
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
