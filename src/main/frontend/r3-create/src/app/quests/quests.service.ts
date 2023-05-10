import {Injectable, Type} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {Category} from "../interfaces/category";
import {PostType} from "../interfaces/post-type";
import {Post} from "../interfaces/post";
import {CategoryDTO} from "../interfaces/category-dto";

@Injectable({
  providedIn: 'root'
})
export class QuestsService {
  private apiURLCat = "http://localhost:8080/categories";
  private apiURLTyp = "http://localhost:8080/types";
  private apiURLQue = "http://localhost:8080/posts/quests";

  constructor(private httpClient: HttpClient) {
  }

  getCategoriesWNofPosts(): Observable<CategoryDTO[]> {
    this.httpClient.get<CategoryDTO[]>(this.apiURLCat.concat("/dto")).subscribe(res => console.log("RC", res))
    return this.httpClient.get<CategoryDTO[]>(this.apiURLCat.concat("/dto"))
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getAllTypes(): Observable<PostType[]> {
    this.httpClient.get<PostType[]>(this.apiURLTyp).subscribe(res => console.log("RT", res))
    return this.httpClient.get<PostType[]>(this.apiURLTyp)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getAllQuests(): Observable<Post[]> {
    this.httpClient.get<Post[]>(this.apiURLQue).subscribe(res => console.log("RQ", res))
    return this.httpClient.get<Post[]>(this.apiURLQue)
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
