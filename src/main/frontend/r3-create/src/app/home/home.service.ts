import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import { catchError } from 'rxjs/operators';

import {Post} from "../interfaces/post";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private apiURL = "http://localhost:8080/posts";
  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Post[]> {
    this.httpClient.get<Post[]>(this.apiURL).subscribe(res => console.log("R", res))
    return this.httpClient.get<Post[]>(this.apiURL)
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
