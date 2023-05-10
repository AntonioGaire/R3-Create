import {Component, OnInit} from '@angular/core';
import {QuestsService} from "../quests.service";
import {Post} from "../../interfaces/post";

@Component({
  selector: 'app-quest-list',
  templateUrl: './quest-list.component.html',
  styleUrls: ['./quest-list.component.css']
})
export class QuestListComponent implements OnInit{

  posts: Post[] = [];
  constructor(public questsService: QuestsService) {
  }

  ngOnInit(): void {
    this.questsService.getAllQuests().subscribe((data: Post[]) => {
      this.posts = data;
      console.log("Posts", data);
    })
  }

}
