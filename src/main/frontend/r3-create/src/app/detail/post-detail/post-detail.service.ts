import {Injectable} from '@angular/core';
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Post} from "../../interfaces/post";
import {catchError} from "rxjs/operators";
import {LocalStorageService} from "../../local-storage.service";
import {DecodedToken} from "../../interfaces/token";
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class PostDetailService{

  private apiURL = "http://localhost:8080/posts/";
  private commentURl = "http://localhost:8080/comments/create";
  private username = "";
  constructor(private httpClient: HttpClient, private storage: LocalStorageService) { }



  find(id: number): Observable<Post> {
    return this.httpClient.get<Post>(this.apiURL + id)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  decodeUser():string{
    let jwtToken = this.storage.get("access_token");

    if(jwtToken !== null) {

      let decodedToken: DecodedToken = jwt_decode(jwtToken);

      if(decodedToken.exp*1000 > new Date().valueOf()) {
        this.username = decodedToken.sub
        return decodedToken.sub
      }
    }
    return ""
  }

  getToken():string|null{
    return this.storage.get("access_token")
  }

  postComment(data:any){
    let token = this.getToken();
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${token}`)
      .set('Content-Type', 'application/json');



    this.httpClient.post(this.commentURl, data, {headers}).subscribe(  response => {
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

    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(() => errorMessage);
  }
}
