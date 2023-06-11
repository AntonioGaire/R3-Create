import {Component, OnInit} from '@angular/core';
import {AdministrationService} from "../administration.service";
import {PostType} from "../../interfaces/post-type";
import {Category} from "../../interfaces/category";
import {Image} from "../../interfaces/image";
import {FormControl, FormBuilder, Validators} from "@angular/forms";
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-creation',
  templateUrl: './post-creation.component.html',
  styleUrls: ['./post-creation.component.css']
})
export class PostCreationComponent implements OnInit{

  types : PostType[] = [];
  categories : Category[] = [];
  username : string = "";
  images: Image[] = [];

  selectedType : string ="";
  selectedCategories: Category[] = [];
  dropdownSettings : IDropdownSettings = {};

  postCreationForm = this.formBuilder.group({
    title : new FormControl('', [
      Validators.required,
      Validators.minLength(5)
    ]),
    description : new FormControl('', Validators.compose([
      Validators.minLength(50),
      Validators.required,
    ])),
    type:  new FormControl('', Validators.required),
    reward : '',
    categories : []
  })

  imageCreationForm = this.formBuilder.group({
    title : new FormControl('', [
      Validators.required,
      Validators.minLength(5)
    ]),
    alt : new FormControl('', Validators.compose([
      Validators.minLength(5),
      Validators.required,
    ])),
    src:  new FormControl('', Validators.required)
  })

  changeType(e: any) {
    console.log( e.target.value.substring(3))
    this.selectedType = e.target.value.substring(3)
  }

  onItemSelect(e: any) {
    console.log( e.idCategory )
    this.selectedCategories.push( this.categories[e.idCategory -1] );
  }

  constructor(private administrationService : AdministrationService, private formBuilder : FormBuilder, private router : Router) {
  }

  addImage(){
    let image :Image =  new class implements Image {
      alt: string = "";
      idImage: number = -1;
      src: string = "";
      title: string = "";
    }

    image.title = this.imageCreationForm.value.title == null ? "":this.imageCreationForm.value.title
    image.alt = this.imageCreationForm.value.alt == null ? "":this.imageCreationForm.value.alt
    image.src = this.imageCreationForm.value.src == null ? "":this.imageCreationForm.value.src

    this.imageCreationForm.reset();

    this.images.push(image);
  }

  ngOnInit(): void {
    this.administrationService.getAllCategories().subscribe((data:Category[]) => {
      this.categories = data;
      console.log("cat", data);
    })

    this.administrationService.getAllTypes().subscribe((data: PostType[]) => {
      this.types = data;
      console.log("Types", data);
    })

    this.dropdownSettings = {
      singleSelection: false,
      idField: "idCategory",
      textField: 'name',
      itemsShowLimit: 3,
      allowSearchFilter: true,
      enableCheckAll: false
    };

    this.username = this.administrationService.decodeUser();
  }

  createPost(){
    let postRequest= {
      title : this.postCreationForm.value.title,
      postType : this.selectedType,
      reward: this.postCreationForm.value.reward,
      categories: this.selectedCategories,
      description: this.postCreationForm.value.description,
      images: this.images,
      user: this.username,
    }

    console.log(postRequest);
    this.administrationService.createPost(postRequest);

    this.router.navigate(['/home'])

  }

}
