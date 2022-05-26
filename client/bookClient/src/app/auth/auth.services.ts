import {
    HttpClient
  } from "@angular/common/http";
  import {
    Injectable
  } from "@angular/core";
import { NgForm } from "@angular/forms";
  import { Book } from "../book.model";
  @Injectable({
    providedIn: 'root'
  })
  
  export class AuthServices {
  
  baseurl="http://localhost:8080/user"   
    constructor(private http: HttpClient) {
    }
  login(form:NgForm){
      this.http.post(this.baseurl,form.value)
  }



}