import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {LocalStorageService} from "../../local-storage.service";
import {Token} from "../../interfaces/token";
import {CoreService} from "../core.service";
import {Router} from "@angular/router";
import {RegisterRequest} from "../../interfaces/register-request";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})



export class HeaderComponent implements OnInit{
  constructor(private formBuilder: FormBuilder, private http: HttpClient, private localStorageService: LocalStorageService, public coreService: CoreService, private router: Router) {
  }

  url="http://localhost:8080/auth"


  searchForm = this.formBuilder.group({
    searchTerm :''
  })

  loginForm = this.formBuilder.group({
    username : '',
    password : ''
  })

  registerForm = this.formBuilder.group({
    username : new FormControl('', [
      Validators.required,
      Validators.minLength(5)
    ]),
    password : new FormControl('', Validators.compose([
      Validators.minLength(5),
      Validators.required,
      Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$') //this is for the letters (both uppercase and lowercase) and numbers validation
    ])),
    passwordConfirmation:  new FormControl('', Validators.required),
    pfp : ''
  })



  onSubmit(): void {
    console.log('Login start', this.loginForm.value);
    this.http.post<Token>(this.url.concat("/authenticate"), this.loginForm.value).subscribe((r)=>{
      console.log(r.access_token, r.role)
      this.localStorageService.set("access_token", r.access_token);
      this.localStorageService.set("role", r.role);
    },(error) => {
      this.coreService.username='';
      this.localStorageService.remove("access_token");
      alert("Usuario o contraseña incorrectos")
    } )

    this.coreService.decodeUser();
  }

  onRegisterSubmit(): void {
    console.log('Register start', this.registerForm.value);

    let req : RegisterRequest = new class implements RegisterRequest {
      password: string = "";
      pfp: string = "";
      role: string = "";
      username: string = "";
    };

    if(this.registerForm.value.password !== this.registerForm.value.passwordConfirmation){
      alert("contraseña incorrecta");
      return
    }

    req.username = this.registerForm.value.username+"";
    req.password = this.registerForm.value.password+"";
    req.pfp = this.registerForm.value.pfp+"";
    req.role = "standard";

    this.http.post<Token>(this.url.concat("/register"), req).subscribe((r)=>{
      console.log(r.access_token)
      this.localStorageService.set("access_token", r.access_token);
    },(error) => {
      this.coreService.username='';
      this.localStorageService.remove("access_token");
      alert("registro incorrecto")
    } )

    this.coreService.decodeUser();
  }

  ngOnInit(){
    this.coreService.decodeUser()
  }

  logout(): void{
    this.coreService.username='';
    this.localStorageService.remove("access_token")
    this.localStorageService.remove("role")
  }

  search(){
    this.router.navigate(['/search'], {queryParams : {search:this.searchForm.value.searchTerm}})
  }

}
