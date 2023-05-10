import {Component, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {PapercraftService} from "../papercraft.service";

@Component({
  selector: 'app-paperfraft-list',
  templateUrl: './paperfraft-list.component.html',
  styleUrls: ['./paperfraft-list.component.css']
})
export class PaperfraftListComponent implements OnInit{

  posts: Post[] = [];
  constructor(public papercraftService: PapercraftService) {
  }

  ngOnInit(): void {
    this.papercraftService.getAllPapercraft().subscribe((data: Post[]) => {
      this.posts = data;
      console.log("Posts", data);
    })
  }

}
