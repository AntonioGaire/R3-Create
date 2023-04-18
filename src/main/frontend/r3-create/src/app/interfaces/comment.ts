import {User} from "./user";

export interface Comment {
  idComment: number;
  text: string;
  kudos: number;

  user: User;

  parentComment: Comment;
  comments: Comment[];

  createdDate: Date;
  modifiedDate: Date;
}
