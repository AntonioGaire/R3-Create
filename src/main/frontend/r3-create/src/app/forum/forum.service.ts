import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Category} from "../interfaces/category";
import {catchError} from "rxjs/operators";
import {PostType} from "../interfaces/post-type";
import {Post} from "../interfaces/post";
import {User} from "../interfaces/user";
import {CategoryDTO} from "../interfaces/category-dto";

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  private apiURLCat = "http://localhost:8080/categories";
  private apiURLFor = "http://localhost:8080/posts/forum_posts";
  private apiURLUse = "http://localhost:8080/users";

  constructor(private httpClient: HttpClient) {
  }

  getAllCategories(): Observable<Category[]> {
    this.httpClient.get<Category[]>(this.apiURLCat).subscribe(res => console.log("RC", res))
    return this.httpClient.get<Category[]>(this.apiURLCat)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getCategoriesWNofPosts(): Observable<CategoryDTO[]> {
    this.httpClient.get<CategoryDTO[]>(this.apiURLCat.concat("/dto")).subscribe(res => console.log("RC", res))
    return this.httpClient.get<CategoryDTO[]>(this.apiURLCat.concat("/dto"))
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getTopPosters(): Observable<User[]>{
    this.httpClient.get<User[]>(this.apiURLUse.concat("?top=5") ).subscribe( res => console.log("RU", res))
    return this.httpClient.get<User[]>(this.apiURLUse.concat("?top=5") )
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getTopCategories(): Observable<Category[]> {
    this.httpClient.get<Category[]>(this.apiURLCat.concat("?top=5") ).subscribe(res => console.log("RCT", res))
    return this.httpClient.get<Category[]>(this.apiURLCat.concat("?top=5") )
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getAllForumPosts(): Observable<Post[]> {
    this.httpClient.get<Post[]>(this.apiURLFor).subscribe(res => console.log("RF", res))
    return this.httpClient.get<Post[]>(this.apiURLFor)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  errorHandler(error: any) {

    let errorMessage = '';

    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(() => errorMessage);
  }
}
