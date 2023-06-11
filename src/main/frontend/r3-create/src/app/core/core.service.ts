import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {PostPage} from "../interfaces/post-page";
import {LocalStorageService} from "../local-storage.service";
import jwt_decode from 'jwt-decode';
import {DecodedToken} from "../interfaces/token";
import {User} from "../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class CoreService {
  private apiURL = "http://localhost:8080/posts";
  public username = '';
  public role: string|null = "";

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) {
  }

  getPostsVariable(page:number, type:string, categories:string[], kudos:number): Observable<PostPage>{
    console.log("page:", page, " type: ", " categories: ", categories, " kudos: ", kudos)
    if(type == ""){
      if (categories.length == 0){
        console.log("p")
        return this.getPosts(this.apiURL.concat("?page="+page))
      }else {
        console.log("pc")
        return this.getPosts(this.apiURL.concat("?page="+page+"&categories="+categories+"&kudos="+kudos))
      }
    }else{
      if(categories.length == 0){
        console.log("pt")
        return this.getPosts(this.apiURL.concat("?page="+page+"&type="+type+"&kudos="+kudos))
      }else{
        console.log("ptc")
        return this.getPosts(this.apiURL.concat("?page="+page+"&type="+type+"&categories="+categories+"&kudos="+kudos))
      }
    }
  }

  getSearchResults(page:number, searchTerm:string): Observable<PostPage>{
    return this.getPosts(this.apiURL.concat("/search?page="+page+"&search="+searchTerm))
  }

  getPosts(url: string): Observable<PostPage> {
    this.httpClient.get<PostPage>(url).subscribe(res => console.log("R", res))
    return this.httpClient.get<PostPage>(url)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getUser(url: string): Observable<User> {
    this.httpClient.get<User>(url).subscribe(res => console.log("U", res))
    return this.httpClient.get<User>(url)
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

  decodeUser(){
    let jwtToken = this.localStorageService.get("access_token");
    this.role= this.localStorageService.get("role");

    if(jwtToken == null) {
      return
    }else{
      let decodedToken: DecodedToken = jwt_decode(jwtToken);

      if(decodedToken.exp*1000 > new Date().valueOf()) {
        this.username = decodedToken.sub
      }
    }
  }
}
