import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Category} from "../interfaces/category";
import {catchError} from "rxjs/operators";
import {Post} from "../interfaces/post";
import {CategoryDTO} from "../interfaces/category-dto";

@Injectable({
  providedIn: 'root'
})
export class PapercraftService {
  private apiURLCat = "http://localhost:8080/categories";
  private apiURLQue = "http://localhost:8080/posts/papercraft";

  constructor(private httpClient: HttpClient) {
  }

  getCategoriesWNofPosts(): Observable<CategoryDTO[]> {
    this.httpClient.get<CategoryDTO[]>(this.apiURLCat.concat("/dto")).subscribe(res => console.log("RC", res))
    return this.httpClient.get<CategoryDTO[]>(this.apiURLCat.concat("/dto"))
      .pipe(
        catchError(this.errorHandler)
      )
  }
  getAllPapercraft(): Observable<Post[]> {
    this.httpClient.get<Post[]>(this.apiURLQue).subscribe(res => console.log("RP", res))
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
