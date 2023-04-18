import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Post} from "../../interfaces/post";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PostDetailService {

  private apiURL = "http://localhost:8080/posts/";
  constructor(private httpClient: HttpClient) { }

  find(id: number): Observable<Post> {
    return this.httpClient.get<Post>(this.apiURL + id)
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
