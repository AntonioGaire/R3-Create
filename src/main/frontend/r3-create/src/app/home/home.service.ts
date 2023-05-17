import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import { catchError } from 'rxjs/operators';

import {Post} from "../interfaces/post";
import {Category} from "../interfaces/category";
import {PostPage} from "../interfaces/post-page";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private apiURL = "http://localhost:8080/posts";
  private apiURLCat = "http://localhost:8080/categories";
  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Post[]> {
    this.httpClient.get<Post[]>(this.apiURL).subscribe(res => console.log("R", res))
    return this.httpClient.get<Post[]>(this.apiURL)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getPaginatedPosts(page: number): Observable<PostPage> {
    this.httpClient.get<PostPage>(this.apiURL.concat("?page="+page)).subscribe(res => console.log("R", res))
    return this.httpClient.get<PostPage>(this.apiURL.concat("?page="+page))
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getAllCategories(): Observable<Category[]> {
    this.httpClient.get<Category[]>(this.apiURLCat).subscribe(res => console.log("RC", res))
    return this.httpClient.get<Category[]>(this.apiURLCat)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getTopCategories(): Observable<Category[]> {
    this.httpClient.get<Category[]>(this.apiURLCat.concat("?top=10") ).subscribe(res => console.log("RCT", res))
    return this.httpClient.get<Category[]>(this.apiURLCat.concat("?top=10") )
      .pipe(
        catchError(this.errorHandler)
      )
  }

  errorHandler(error: any) {

    let errorMessage = '';

    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(() => errorMessage);
  }

}
