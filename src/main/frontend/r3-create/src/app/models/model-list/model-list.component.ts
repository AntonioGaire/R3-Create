import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {ModelService} from "../model.service";

@Component({
  selector: 'app-model-list',
  templateUrl: './model-list.component.html',
  styleUrls: ['./model-list.component.css']
})
export class ModelListComponent implements OnInit {

  posts: Post[] = [];
  constructor(public modelService: ModelService) {
  }

  ngOnInit(): void {
    this.modelService.getAllModels().subscribe((data: Post[]) => {
      this.posts = data;
      console.log("Posts", data);
    })
  }

}
