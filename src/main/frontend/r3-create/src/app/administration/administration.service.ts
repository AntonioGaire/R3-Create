import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {PostType} from "../interfaces/post-type";
import {catchError} from "rxjs/operators";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Category} from "../interfaces/category";
import {DecodedToken} from "../interfaces/token";
import jwt_decode from "jwt-decode";
import {LocalStorageService} from "../local-storage.service";
import {Post} from "../interfaces/post";
import {Comment} from "../interfaces/comment";
import {User} from "../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class AdministrationService {

  private apiURLCat = "http://localhost:8080/categories";
  private apiURLTyp = "http://localhost:8080/types";
  private apiURLPos = "http://localhost:8080/posts";
  private apiURLCom = "http://localhost:8080/comments";
  private apiURLUse = "http://localhost:8080/users";

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) { }

  getAllTypes(): Observable<PostType[]> {
    return this.httpClient.get<PostType[]>(this.apiURLTyp)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getAllCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.apiURLCat)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getAllPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.apiURLPos)
      .pipe(
        catchError(this.errorHandler)
      )
  }
  getAllComments(): Observable<Comment[]> {

    let token = this.getToken();
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)

    return this.httpClient.get<Comment[]>(this.apiURLCom, {headers})
      .pipe(
        catchError(this.errorHandler)
      )
  }
  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.apiURLUse)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  createPost(data:any){
    let token = this.getToken();
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${token}`)
      .set('Content-Type', 'application/json');

    this.httpClient.post(this.apiURLPos.concat('/create'), data, {headers}).subscribe(  response => {
        // Handle the response
        console.log(response);
      },
      error => {
        // Handle any errors
        console.error(error);
      });
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

  decodeUser():string{
    let jwtToken = this.localStorageService.get("access_token");

    if(jwtToken == null) {
    }else{
      let decodedToken: DecodedToken = jwt_decode(jwtToken);
      if(decodedToken.exp*1000 > new Date().valueOf()) {
        return decodedToken.sub
      }
    }
    return ""
  }

  getToken():string|null{
    return this.localStorageService.get("access_token")
  }

  delete(type:string, data:any){

    let token = this.getToken();
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${token}`)
      .set('Content-Type', 'application/json');

    let variableURL = "";

    if(type=="comment"){
      variableURL = this.apiURLCom;
    }else if(type=="user"){
      variableURL = this.apiURLUse;
    }else if(type=="post"){
      variableURL = this.apiURLPos;
    }else if(type=="category"){
      //variableURL = this.apiURLCat;
    }

    console.log(variableURL, data)

    this.httpClient.post(variableURL.concat('/delete'), data, {headers}).subscribe(  response => {
        // Handle the response
        console.log(response);
      },
      error => {
        // Handle any errors
        console.error(error);
      });
  }

}
