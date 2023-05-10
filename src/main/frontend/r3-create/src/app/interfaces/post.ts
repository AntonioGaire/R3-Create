import {PostType} from "./post-type";
import {Category} from "./category";
import {User} from "./user";
import {Image} from "./image";
import {Comment} from "./comment";

export interface Post {

  idPost: number;
  title: string;
  description: string;
  recompensa: number;
  kudos: number;

  postType: PostType;
  categories: Category[];
  user: User;
  images : Image[];
  comments: Comment[];
}
