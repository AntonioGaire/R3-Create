import {Post} from "./post";

export interface PostPage {
  totalItems: number;
  totalPages: number;
  currentPage: number;
  posts: Post[];
}
